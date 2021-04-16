package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.IUserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryCustom  {
//    @Autowired
//    private EntityManager entityManager;
//    @Override
//    public List<UserEntity> getAllStaff() {
//        try {
//            String sql = "select * from assignmentbuilding";
//            Query query = entityManager.createNativeQuery(sql,UserEntity.class);
//            return query.getResultList();
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }

//    @Override
//    public List<UserEntity> getStaffByBuildingId(Long buildingId) {
//        try {
//            String sql = "select * from assignmentbuilding where buildingid = ?";
//            Query query = entityManager.createNativeQuery(sql,UserEntity.class);
//            query.setParameter(1,buildingId);
//            return query.getResultList();
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
}
