package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.District;
import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.TypeCode;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.impl.TransactionServiceImpl;
import com.javaweb.service.impl.UserServiceImpl;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

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

    @GetMapping("/admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerDTO")CustomerDTO customerDTO){ //them moi toa nha
        ModelAndView mav = new ModelAndView( "/admin/customer/edit");
        mav.addObject("status", Status.getStatus());
        return mav;
    }

    @GetMapping("/admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable Long id){ //sua khach hang
        ModelAndView mav = new ModelAndView( "/admin/customer/edit");
        mav.addObject("status", Status.getStatus());
        mav.addObject("transactionType",TransactionType.getTransactionType());
        //Hien thi toa nha theo nhan vien quan ly
        if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            if(customerService.isStaffOfCustomer(staffId, id)==false){
                mav.setViewName("error/notfound");
                return mav;
            }
        }
        //findbuidlingbyid=>buildingDTO\
        CustomerDTO dto = new CustomerDTO();
        try{
            dto = customerService.findCustomerById(id);
        }
        catch (ServiceException e){
            e.getMessage();
            return null;
        }
        List<TransactionDTO> customerDdx = transactionServiceImpl.customerDdx(id);
        List<TransactionDTO> customerCskh = transactionServiceImpl.customerCskh(id);

        mav.addObject("customerDTO", dto);
        mav.addObject("DDX", customerDdx);
        mav.addObject("CSKH", customerCskh);
        return mav;
    }
}
