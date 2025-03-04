package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.Status;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.impl.UserServiceImpl;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView getCustomerList(@ModelAttribute("modelSearch") CustomerSearchRequest params, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        DisplayTagUtils.of(request, params);
        mav.addObject("staffs", userService.getListStaff());
        mav.addObject("status", Status.getStatus());

        if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            params.setStaffId(staffId);
        }
        //xuong service
        List<CustomerSearchResponse> results = customerService.findAll(params, PageRequest.of(params.getPage()-1, params.getMaxPageItems() ));
        CustomerSearchResponse customerList = new CustomerSearchResponse();
        customerList.setTotalItems(customerService.countCustomer(params));
        customerList.setListResult(results);

        mav.addObject("customerList",customerList);

        return mav;
    }
}
