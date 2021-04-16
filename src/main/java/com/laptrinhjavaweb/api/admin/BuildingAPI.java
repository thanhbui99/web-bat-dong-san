package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.response.StaffResponse;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService iBuildingService;

    @Autowired
    private IUserService iUserService;

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody BuildingDTO buildingDTO){
      iBuildingService.save(buildingDTO);
      return buildingDTO;
    }
    @RequestMapping(value = "/building-edit/{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        getMapStaff(mav);
        BuildingDTO dto = iBuildingService.getById(id);
        mav.addObject("model",dto);
        return mav;
    }
    private void getMapStaff(ModelAndView mav) {
        Map<Long,String> staffMaps = iUserService.getStaffMaps();

        Map<DistrictsEnum,String> districtsEnumStringMap = new HashMap<>();
        Arrays.asList(DistrictsEnum.values())
                .forEach(districtsEnum ->districtsEnumStringMap.put(districtsEnum,districtsEnum.getDistrictValue()));

        Map<BuildingTypesEnum,String> typesEnumStringMap = new HashMap<>();
        Arrays.asList(BuildingTypesEnum.values())
                .forEach(buildingTypesEnum -> typesEnumStringMap.put(buildingTypesEnum,buildingTypesEnum.getBuildingTypeValue()));

        mav.addObject("staffMaps",staffMaps);
        mav.addObject("typesEnumStringMap",typesEnumStringMap);
        mav.addObject("districtsEnumMap",districtsEnumStringMap);
    }


    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO staffResponse(@PathVariable Long buildingId){

        ResponseDTO result = new ResponseDTO();
        result.setData(iUserService.getStaffByBuildingId(buildingId));
        return result;
    }

    @PostMapping("/assignStaff")
    public ResponseDTO assignStaff(@RequestBody StaffRequest staffRequest){
        iBuildingService.assignBuilding(staffRequest);
        staffResponse(staffRequest.getBuildingId());
        ResponseDTO result = new ResponseDTO();
        result.setMessage("Thêm thành công");
        return result;
    }

}
