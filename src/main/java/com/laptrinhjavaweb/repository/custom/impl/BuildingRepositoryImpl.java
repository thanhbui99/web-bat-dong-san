package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @Autowired
    EntityManager entitymanager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("select * from building as b ");
        try {

            builderCommon(builder, sql);
            specialSql(builder,sql);
            System.out.println(sql);
            Query query = entitymanager.createNativeQuery(sql.toString(), BuildingEntity.class);

            List<BuildingEntity> result = query.getResultList();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<BuildingEntity> getAllStaffByBuildingId(Long buildingId) {
        try {
            String sql = "select * from building b right join assignmentbuilding ab on b.id = ab.buildingid where buildingid=?; ";
            Query query = entitymanager.createNativeQuery(sql,BuildingEntity.class);
            query.setParameter(1,buildingId);
            return query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private StringBuilder specialSql(BuildingSearchBuilder builder, StringBuilder sql) {
        if(builder.getBuildingTypes()!= null && builder.getBuildingTypes().length>0) {
            sql.append(" and (");
            String sqlType =  Arrays.stream(builder.getBuildingTypes())
                    .map(item ->"b.type like '%"+item+"%'")
                    .collect(Collectors.joining(" OR "));
            sql.append(sqlType+" )");
        }
        if (builder.getRentAreaFrom() != null || builder.getRentAreaTo() != null) {
            sql.append("and exists(select *from rentarea ra where ra.buildingid = b.id ");
            if (builder.getRentAreaFrom() != null) {
                sql.append(" and ra.value >= " + builder.getRentAreaFrom() + "");
            }
            if (builder.getRentAreaTo() != null) {
                sql.append(" and  ra.value <= " + builder.getRentAreaTo() + "");
            }
            sql.append(")");
        }
        if (builder.getRentPriceFrom() != null || builder.getRentPriceTo() != null) {
            if (builder.getRentPriceFrom() != null) {
                sql.append(" and b.rentprice >= " + builder.getRentPriceFrom() + "");
            }
            if (builder.getRentPriceTo() != null) {
                sql.append(" and  b.rentprice <= " + builder.getRentPriceTo() + "");
            }
        }
        sql.append(" group by b.id");
        return sql;
    }

    private StringBuilder builderCommon(BuildingSearchBuilder builder, StringBuilder sql) {
        if(builder.getStaffId() != null){
            sql.append("join assignmentbuilding ab on ab.buildingid =  b.id ");
        }
        sql.append("where 1 = 1 ");
        try {
            Field[] field = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field f :
                    field) {
                f.setAccessible(true);

                if (f.getType().getSimpleName().equals("Integer")) {
                    if (f.get(builder) != null) {
                        if(f.getName().equals("staffId")){
                            sql.append("and ab." + f.getName().toLowerCase() + " = " + f.get(builder) + " ");
                        }
                        else if (!(f.getName().startsWith("rentArea")
                                || f.getName().startsWith("rentPrice")
                                || f.getName().startsWith("staffId"))) {
                            sql.append("and b." + f.getName().toLowerCase() + " = " + f.get(builder) + " ");
                        }
                    }
                }
                else if (f.getType().getSimpleName().equals("String")) {
                    if (f.get(builder) != null && f.get(builder) != "") {
                        if (!f.getName().equals("buildingTypes")) {
                            sql.append("and b." + f.getName().toLowerCase() + " like '%" + f.get(builder) + "%' ");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

}
