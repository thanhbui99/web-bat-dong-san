package com.laptrinhjavaweb.controller.admin;



import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IBuildingService iBuildingService;

    @Autowired
    private IUserService iUserService;


    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        List<BuildingDTO> dtos = iBuildingService.findAll();
        BuildingDTO dto = new BuildingDTO();
        getMapStaff(mav);
        mav.addObject("modelSearch",dto);
        mav.addObject("list",dtos);
        return mav;
    }

    @RequestMapping(value = "/admin/handle-search", method = RequestMethod.POST)
    public ModelAndView handleSearch(@RequestParam Map<String,String> requestParams,
                                     @RequestParam(value = "buildingTypes",required = false) String[] buildingTypes) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        List<BuildingDTO> dtos = iBuildingService.findAllMap(requestParams, buildingTypes);
        BuildingDTO dto = new BuildingDTO();
        BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
                .setDistrict(requestParams.get("district"))
                .setName(requestParams.get("name"))
                .setFloorArea(requestParams.get("floorArea")==null || requestParams.get("floorArea")==""? null : Integer.parseInt(requestParams.get("floorArea")))
                .setBuildingTypes(buildingTypes)
                .setRentAreaFrom(requestParams.get("numberOfBasement")==null  || requestParams.get("numberOfBasement")==""? null : Integer.parseInt(requestParams.get("numberOfBasement")))
                .setRentAreaFrom(requestParams.get("rentAreaFrom")==null  || requestParams.get("rentAreaFrom")==""? null : Integer.parseInt(requestParams.get("rentAreaFrom")))
                .setRentAreaTo(requestParams.get("rentAreaTo")==null || requestParams.get("rentAreaTo")==""? null : Integer.parseInt(requestParams.get("rentAreaTo")))
                .setRentPriceFrom(requestParams.get("rentPriceFrom")==null  || requestParams.get("rentPriceFrom")=="" ? null : Integer.parseInt(requestParams.get("rentPriceFrom")))
                .setRentPriceTo(requestParams.get("rentPriceTo")==null || requestParams.get("rentPriceTo")==""? null : Integer.parseInt( requestParams.get("rentPriceTo")))
                .setWard(requestParams.get("ward"))
                .setStreet(requestParams.get("street"))
                .setStaffId(requestParams.get("staffId")==null || requestParams.get("staffId")==""? null : Integer.parseInt(requestParams.get("staffId")))
                .setDirection(requestParams.get("direction"))
                .setManagerName(requestParams.get("managerName"))
                .setManagerPhone(requestParams.get("managerPhone"))
                .setLevel(requestParams.get("level"))
                .build();
        getMapStaff(mav);
        mav.addObject("modelSearch",builder);
        mav.addObject("list",dtos);
        return mav;
    }


    @RequestMapping(value = "/admin/search", method = RequestMethod.POST)
    public ModelAndView handleSearch() {
        ModelAndView mav = new ModelAndView("admin/building/list");
        BuildingDTO dto = new BuildingDTO();
        mav.addObject("modelSearch",dto);
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit() {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        getMapStaff(mav);
        BuildingDTO dto = new BuildingDTO();
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
}
