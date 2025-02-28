package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserServiceImpl;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController(value="buildingControllerOfAdmin")

public class BuildingController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BuildingService buildingService;
   @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
   public ModelAndView getBuildings(@ModelAttribute("modelSearch") BuildingSearchRequest params, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView( "admin/building/list");
        DisplayTagUtils.of(request, params);
        mav.addObject("staffs", userService.getListStaff());
        mav.addObject("district", District.getDistrict());
        mav.addObject("typeCode", TypeCode.getType());
        // xuong service va repo de xu ly
       if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)){
           Long staffId = SecurityUtils.getPrincipal().getId();
           params.setStaffId(staffId);
       }
        List<BuildingSearchResponse> results = buildingService.findAll(params, PageRequest.of(params.getPage()-1, params.getMaxPageItems() )) ;
        BuildingSearchResponse buildingList = new BuildingSearchResponse();
        buildingList.setTotalItems(buildingService.countBuilding(params));
        buildingList.setListResult(results);
        mav.addObject("buildingList", buildingList);
        return mav;
    }


    @GetMapping("/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("building") BuildingDTO buildingDTO){ //them moi toa nha
        ModelAndView mav = new ModelAndView( "/admin/building/edit");
        mav.addObject("district", District.getDistrict());
        mav.addObject("typeCode", TypeCode.getType());
        return mav;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable Long id){ //sua toa nha
        ModelAndView mav = new ModelAndView( "/admin/building/edit");
        mav.addObject("district", District.getDistrict());
        mav.addObject("typeCode", TypeCode.getType());
        //Hien thi toa nha theo nhan vien quan ly
        if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            if(buildingService.isStaffOfBuilding(staffId, id)==false){
                mav.setViewName("error/notfound");
                return mav;
            }
        }
        //findbuidlingbyid=>buildingDTO\
        BuildingDTO dto = new BuildingDTO();
        try{
            dto = buildingService.findBuildingById(id);
        }
        catch (Exception e){
            e.getMessage();
            return null;
        }
        mav.addObject("building", dto);
        return mav;
    }
}
