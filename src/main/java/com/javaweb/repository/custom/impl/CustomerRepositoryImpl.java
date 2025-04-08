package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.DataUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private void sqlJoin(CustomerSearchRequest builder, StringBuilder join) {
        Long staffId = builder.getStaffId();
        if (DataUtil.checkData(staffId)) {
            join.append(" INNER JOIN assignmentcustomer ac ON c.id = ac.customerid ");
        }
    }

    private void sqlWhereNormal(CustomerSearchRequest builder, StringBuilder where) {
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for (Field it : fields) {
                it.setAccessible(true);
                String fieldName = it.getName();
                if (!fieldName.equals("staffId")) {
                    Object value = it.get(builder);
                    if (value != null && !"".equals(value.toString())) {
                        if (it.getType().getName().equals("java.lang.String")) {
                            where.append(" AND c." + fieldName + " LIKE '%" + value + "%' ");
                        }
                        else if (it.getType().getName().equals("java.lang.Long")
                                || it.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND c." + fieldName + " = " + value);
                        }
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        where.append(" AND c.is_active = 1 ");
    }
    private void sqlWhereSpecial(CustomerSearchRequest builder, StringBuilder where) {
        Long staffId = builder.getStaffId();
        if (DataUtil.checkData(staffId)) {
            where.append(" AND staffid = " + staffId);
        }
    }

    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder(" SELECT c.* FROM customer c ");
        sqlJoin(builder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        sqlWhereNormal(builder, where);
        sqlWhereSpecial(builder, where);
        sql.append(where).append(" GROUP BY c.id ");
        sql.append(" ORDER BY c.createddate DESC ");
        sql.append(" LIMIT ").append(pageable.getPageSize()).append(" OFFSET ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);

        return query.getResultList();
    }

    @Override
    public int countCustomer(CustomerSearchRequest builder) {
        StringBuilder sql = new StringBuilder(" SELECT c.* FROM customer c ");
        sqlJoin(builder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        sqlWhereNormal(builder, where);
        sqlWhereSpecial(builder, where);
        sql.append(where).append(" GROUP BY c.id ");
        Query query = entityManager.createNativeQuery(sql.toString(),CustomerEntity.class);
        return query.getResultList().size();
    }
}
