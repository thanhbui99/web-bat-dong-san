package com.laptrinhjavaweb.repository.JDBC;

import com.laptrinhjavaweb.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface IStaffJDBC {
    List<UserEntity> getStaffByBuildingID(Long id) throws SQLException;
    void saveStaffById(Long id, Long buildingId) throws SQLException;
    int deleteStaffById(Long staffId,Long buildingId) throws SQLException;
    void close() throws SQLException;
}
