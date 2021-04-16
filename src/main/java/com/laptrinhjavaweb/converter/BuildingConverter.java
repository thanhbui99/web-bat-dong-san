package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO convertToDTO(BuildingEntity entity) {
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
        DistrictsEnum de = DistrictsEnum.valueOf(dto.getDistrict());
        dto.setAddress(dto.getStreet() + ", " + dto.getWard() + ", " + de.getDistrictValue());
        return dto;
    }

    public BuildingEntity convertToEntity(BuildingDTO dto) {
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
        StringBuffer sb = new StringBuffer("");
        int length = dto.getBuildingTypes().length;
        for (int i = 0; i < length; i++) {
            if (i == (length - 1)) {
                sb.append(dto.getBuildingTypes()[i]);
            } else {
                sb.append(dto.getBuildingTypes()[i] + ",");
            }
        }
        result.setType(sb.toString());
        return result;
    }
}
