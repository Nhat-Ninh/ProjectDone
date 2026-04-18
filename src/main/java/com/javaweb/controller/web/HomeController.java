package com.javaweb.controller.web;

import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.ProvinceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

  @Autowired
  private BuildingService buildingService;

  @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
  public ModelAndView homePage(BuildingSearchRequest buildingSearchRequest) {
		ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("modelSearch", buildingSearchRequest);
        addFilterOptions(mav);
        mav.addObject("selectedProvince", "TP_HCM");
    mav.addObject("buildings", loadBuildings(buildingSearchRequest, 3));
		return mav;
	}

    @GetMapping(value="/gioi-thieu")
    public ModelAndView introducceBuiding(){
        return new ModelAndView("web/introduce");
    }

    @GetMapping(value="/san-pham")
    public ModelAndView buidingList(BuildingSearchRequest buildingSearchRequest,
                                    @RequestParam(value = "province", required = false, defaultValue = "TP_HCM") String province){
        ModelAndView mav = new ModelAndView("/web/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        addFilterOptions(mav);
        mav.addObject("selectedProvince", province);
        mav.addObject("buildings", loadBuildings(buildingSearchRequest, 12));
        return mav;
    }

    @GetMapping(value="/tin-tuc")
    public ModelAndView news(){
        return new ModelAndView("/web/news");
    }

    @GetMapping(value="/lien-he")
    public ModelAndView contact(){
        return new ModelAndView("/web/contact");
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
    return new ModelAndView("login");
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
		return new ModelAndView("register");
    }

      private List<BuildingSearchResponse> loadBuildings(BuildingSearchRequest buildingSearchRequest, int size) {
        try {
          Pageable pageable = PageRequest.of(0, size);
          List<BuildingSearchResponse> buildings = buildingService.findAll(buildingSearchRequest, pageable);
          return buildings != null ? buildings : Collections.emptyList();
        } catch (Exception e) {
          return Collections.emptyList();
        }
      }

      private void addFilterOptions(ModelAndView mav) {
        mav.addObject("provinces", ProvinceCode.getProvince());
        mav.addObject("districts", District.getDistrict());
        mav.addObject("typeCodes", TypeCode.getType());
      }


}
