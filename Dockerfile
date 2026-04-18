# Stage 1: Build
FROM amazoncorretto:18 AS build
WORKDIR /app

# Cài đặt Maven cùng với gzip
RUN yum install -y wget tar gzip && \
    wget https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
    tar -xzf apache-maven-3.9.6-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.9.6 /opt/maven && \
    rm apache-maven-3.9.6-bin.tar.gz

# Thiết lập biến môi trường cho Maven
ENV MAVEN_HOME=/opt/maven
ENV PATH=${MAVEN_HOME}/bin:${PATH}

# Sao chép file Maven và build dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Sao chép mã nguồn và build
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run
FROM amazoncorretto:18
WORKDIR /app
COPY --from=build /app/target/spring-boot-1.0.jar .
EXPOSE 8080
CMD ["java", "-jar", "spring-boot-1.0.jar"]