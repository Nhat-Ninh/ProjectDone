package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
public class UserDTO extends AbstractDTO {
    @NotBlank(message = "User name can not be blank")
    private String userName;
    @NotBlank(message = "Full name can not be blank")
    private String fullName;
    @NotBlank(message = "Password can not be blank")
    private String password;
    @NotBlank(message = "Retype password can not be blank")
    private String retypePassword;
    private Integer status;
    private List<RoleDTO> roles = new ArrayList<>();
    private Long roleId;
    private String roleName;
    private String roleCode;
    private Map<String,String> roleDTOs = new HashMap<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Map<String, String> getRoleDTOs() {
        return roleDTOs;
    }

    public void setRoleDTOs(Map<String, String> roleDTOs) {
        this.roleDTOs = roleDTOs;
    }
}
