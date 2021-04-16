package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {

//    List<BuildingEntity> findAll(String name, Integer floorArea, String ward, String street, Integer numberofbasement,
//                                 String direction, String level, String managerName, String managerPhone, String districtCode,
//                                 Integer rentPriceFrom, Integer rentPriceTo, Long staffId, Integer rentAreaFrom, Integer rentAreaTo,
//                                 String[] types);

    List<BuildingEntity> findAll(BuildingSearchBuilder builder);
    List<BuildingEntity> getAllStaffByBuildingId(Long buildingId);
}
