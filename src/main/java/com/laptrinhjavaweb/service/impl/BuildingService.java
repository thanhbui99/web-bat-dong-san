package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.JDBC.IStaffJDBC;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private IStaffJDBC iStaffJDBC;

    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> result = new ArrayList<>();
        List<BuildingEntity> entities = buildingRepository.findAll();
        for (BuildingEntity entity :
                entities) {
            BuildingDTO dto = buildingConverter.convertToDTO(entity);
            result.add(dto);
        }
        return result;
    }

    @Override
    @Transactional
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for (String item : buildingDTO.getAreaRent().split(",")) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Integer.parseInt(item));
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntities.add(rentAreaEntity);
        }
        buildingEntity.setAreas(rentAreaEntities);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public List<BuildingDTO> findAllMap(Map<String, String> requestParams, String[] types) {
        BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
                .setDistrict(requestParams.get("district"))
                .setName(requestParams.get("name"))
                .setFloorArea(requestParams.get("floorArea") == null || requestParams.get("floorArea") == "" ? null : Integer.parseInt(requestParams.get("floorArea")))
                .setBuildingTypes(types)
                .setRentAreaFrom(requestParams.get("numberOfBasement") == null || requestParams.get("numberOfBasement") == "" ? null : Integer.parseInt(requestParams.get("numberOfBasement")))
                .setRentAreaFrom(requestParams.get("rentAreaFrom") == null || requestParams.get("rentAreaFrom") == "" ? null : Integer.parseInt(requestParams.get("rentAreaFrom")))
                .setRentAreaTo(requestParams.get("rentAreaTo") == null || requestParams.get("rentAreaTo") == "" ? null : Integer.parseInt(requestParams.get("rentAreaTo")))
                .setRentPriceFrom(requestParams.get("rentPriceFrom") == null || requestParams.get("rentPriceFrom") == "" ? null : Integer.parseInt(requestParams.get("rentPriceFrom")))
                .setRentPriceTo(requestParams.get("rentPriceTo") == null || requestParams.get("rentPriceTo") == "" ? null : Integer.parseInt(requestParams.get("rentPriceTo")))
                .setWard(requestParams.get("ward"))
                .setStreet(requestParams.get("street"))
                .setStaffId(requestParams.get("staffId") == null || requestParams.get("staffId") == "" ? null : Integer.parseInt(requestParams.get("staffId")))
                .setDirection(requestParams.get("direction"))
                .setManagerName(requestParams.get("managerName"))
                .setManagerPhone(requestParams.get("managerPhone"))
                .setLevel(requestParams.get("level"))
                .build();
        System.out.println(builder);
        List<BuildingDTO> results = new ArrayList<>();
        List<BuildingEntity> entities = buildingRepository.findAll(builder);
        for (BuildingEntity entity : entities) {
            BuildingDTO buildingDto = buildingConverter.convertToDTO(entity);
            results.add(buildingDto);
        }
        return results;
    }

    @Override
    public BuildingDTO getById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findOne(id);

        return buildingConverter.convertToDTO(buildingEntity);
    }

    @Override
    public void assignBuilding(StaffRequest staffRequest) {
        BuildingEntity building = buildingRepository.findOne(staffRequest.getBuildingId());

        List<UserEntity> userEntities = new ArrayList<>();
        for (int i = 0; i < staffRequest.getIds().length; i++) {
            userEntities.add(userRepository.findOne(staffRequest.getIds()[i]));
        }
        building.setUsersBuilding(userEntities);

        buildingRepository.save(building);
    }

    @Override
    public void assignBuildingUsingJDBC(StaffRequest staffRequest) throws SQLException {

        List<UserEntity> staffByBuildingID = iStaffJDBC.getStaffByBuildingID(staffRequest.getBuildingId());
        List<Long> staffOld = new ArrayList<>();
        List<Long> staffDelete = new ArrayList<>();
        List<Long> staffUpdate = new ArrayList<>();
        List<Long> staffNewAdd = Arrays.asList(staffRequest.getIds());
        if (staffByBuildingID.size() != 0) {
            for (UserEntity userEntity :
                    staffByBuildingID) {
                staffOld.add(userEntity.getId());
            }
        }
        if (staffByBuildingID.size() != 0) {
            for (UserEntity userEntity : staffByBuildingID) {
                Long staffId = userEntity.getId();
                if (!staffNewAdd.contains(staffId)) {
                    staffDelete.add(staffId);
                }
            }
        }
        for (Long staffId : staffNewAdd) {
            if (!staffOld.contains(staffId)) {
                staffUpdate.add(staffId);
            }
        }
        if (staffDelete.size() != 0) {
            for (Long staffId :
                    staffDelete) {
                iStaffJDBC.deleteStaffById(staffId, staffRequest.getBuildingId());
            }
        }
        if (staffUpdate.size() != 0) {
            for (Long staffId :
                    staffUpdate) {
                System.out.println(staffId + " " + staffRequest.getBuildingId());
                iStaffJDBC.saveStaffById(staffId, staffRequest.getBuildingId());
            }
        }
    }
}


