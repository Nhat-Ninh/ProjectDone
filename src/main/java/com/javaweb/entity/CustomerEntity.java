package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "fullname")
  private String fullName;
  @Column(name = "phone")
    private String phone;
  @Column(name = "email")
    private String email;
  @Column(name = "companyname")
    private String companyName;
  @Column(name = "demand")
    private String demand;
  @Column(name = "status")
    private String status;
  @Column(name = "is_active")
    private Integer isActive;

  @OneToMany(mappedBy = "customerEntity" ,fetch = FetchType.LAZY)
  private List<AssignmentCustomerEntity> assignmentCustomers = new ArrayList<>();


}
