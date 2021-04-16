package com.laptrinhjavaweb.repository.JDBC.impl;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.JDBC.DBConnection;
import com.laptrinhjavaweb.repository.JDBC.IStaffJDBC;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffJDBC implements IStaffJDBC {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/springbootweb122020";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    Connection connection = null;

    public StaffJDBC() {
        this.connection = DBConnection.getConnection(DB_URL, USER, PASSWORD);
    }

    @Override
    public List<UserEntity> getStaffByBuildingID(Long buildingId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from users u right join assignmentbuilding ab on u.id = ab.staffid where buildingid=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, buildingId);
            resultSet = preparedStatement.executeQuery();
            List<UserEntity> userEntities = new ArrayList<>();
            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setFullName(resultSet.getString("fullname"));
                userEntity.setId(resultSet.getLong("id"));
                userEntities.add(userEntity);
            }
            return userEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    @Override
    public void saveStaffById(Long staffId, Long buildingId) throws SQLException {
        PreparedStatement preparedStatement = null;
        connection.setAutoCommit(false);
        String sql = "insert into assignmentbuilding(staffid,buildingid)  values("+staffId+","+buildingId+");";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    @Override
    public int deleteStaffById(Long staffId, Long buildingId) throws SQLException {
        PreparedStatement  preparedStatement = null;
        connection.setAutoCommit(false);
        String sql = "delete from assignmentbuilding where staffid = "+staffId+" and buildingid = "+buildingId+";";
        try {
            preparedStatement = connection.prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            connection.commit();
            return i;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return 0;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void close() throws SQLException {
        if (connection!= null){
            connection.close();
        }
    }
}

