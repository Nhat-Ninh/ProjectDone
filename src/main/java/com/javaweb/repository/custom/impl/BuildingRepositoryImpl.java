package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.DataUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    private void sqlJoin(BuildingSearchRequest builder, StringBuilder join) {
        Long staffId = builder.getStaffId();
        if (DataUtil.checkData(staffId)) {
            join.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
        }
        Long rentAreaFrom = builder.getRentAreaFrom();
        Long rentAreaTo = builder.getRentAreaTo();
        if (DataUtil.checkData(rentAreaFrom) || DataUtil.checkData(rentAreaTo)) {
            join.append(" INNER JOIN rentarea rt ON b.id = rt.buildingid ");
        }
    }

    private void sqlWhereNormal(BuildingSearchRequest builder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for (Field it : fields) {
                it.setAccessible(true);
                String fieldName = it.getName();
                if (!fieldName.equals("staffId") && !fieldName.startsWith("rentArea")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = it.get(builder);
                    if (value != null && !"".equals(value.toString())) {
                        if (it.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        } else if (it.getType().getName().equals("java.lang.Long")
                                || it.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + fieldName + " = " + value);
                        }
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sqlWhereSpecial(BuildingSearchRequest builder, StringBuilder where) {
        Long staffId = builder.getStaffId();
        if (DataUtil.checkData(staffId)) {
            where.append(" AND staffid = " + staffId);
        }
        Long rentAreaFrom = builder.getRentAreaFrom();
        Long rentAreaTo = builder.getRentAreaTo();
        if (DataUtil.checkData(rentAreaFrom)) {
            where.append(" AND value >= " + rentAreaFrom);
        }
        if (DataUtil.checkData(rentAreaTo)) {
            where.append(" AND value <= " + rentAreaTo);
        }
        Long rentPriceFrom = builder.getRentPriceFrom();
        Long rentPriceTo = builder.getRentPriceTo();
        if (DataUtil.checkData(rentPriceFrom)) {
            where.append(" AND rentprice >= " + rentPriceFrom);
        }
        if (DataUtil.checkData(rentPriceTo)) {
            where.append(" AND rentprice <= " + rentPriceTo);
        }
        // java8
        List<String> typeCode = builder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
//            where.append(" AND type IN( "
//                    + typeCode.stream().map(i -> "'" + i + "'").collect(Collectors.joining(",")) + ")");
            where.append(" AND (");
            String conditions = typeCode.stream().map(i -> "type LIKE '%" + i + "%'")
                    .collect(Collectors.joining(" OR "));
            where.append(conditions).append(")");
        }
    }

    @Override
    public List<BuildingEntity> findAll (BuildingSearchRequest builder, Pageable pageable){
        StringBuilder sql = new StringBuilder(" SELECT b.* FROM building b ");
        sqlJoin(builder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        sqlWhereNormal(builder, where);
        sqlWhereSpecial(builder, where);
        sql.append(where).append(" GROUP BY b.id ");
        sql.append(" LIMIT ").append(pageable.getPageSize()).append(" OFFSET ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);

        return query.getResultList();
    }

    @Override
    public int countBuilding (BuildingSearchRequest builder){
        StringBuilder sql = new StringBuilder(" SELECT b.* FROM building b ");
        sqlJoin(builder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        sqlWhereNormal(builder, where);
        sqlWhereSpecial(builder, where);
        sql.append(where).append(" GROUP BY b.id ");
        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
        return query.getResultList().size();
    }

}
