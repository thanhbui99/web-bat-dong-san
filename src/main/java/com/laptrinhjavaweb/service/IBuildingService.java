package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.StaffRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> findAll();
    void save(BuildingDTO buildingDTO);
    List<BuildingDTO> findAllMap(Map<String,String> requestParams,String[] types);
    BuildingDTO getById(Long id);
    void assignBuilding(StaffRequest staffRequest);
    void assignBuildingUsingJDBC(StaffRequest staffRequest) throws SQLException;
}
