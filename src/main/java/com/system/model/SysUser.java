package com.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用戶信息
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-10-17
 */
@ApiModel(value = "SysUser",description = "用户信息")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

      /**
     * 用戶id
     */
        @TableId(type = IdType.AUTO)
        @ApiModelProperty(value = "用户ID",name = "id")
      private Integer id;

      /**
     * 用戶名
     */
      @ApiModelProperty(value = "用户名",name = "username")
      private String username;

      /**
     * 密碼
     */
      @ApiModelProperty(value = "密碼",name = "password")
      private String password;

      /**
     * 用戶昵稱
     */
      @ApiModelProperty(value = "用戶昵稱",name = "name")
      private String name;

      /**
     * 簡碼
     */
      @ApiModelProperty(value = "電話號碼",name = "phone")
      private String phone;

      /**
     * 部門
     */
      @ApiModelProperty(value = "部門",name = "department")
      private String department;

      /**
     * 郵箱
     */
      @ApiModelProperty(value = "郵箱",name = "email")
      private String email;

      /**
     * 權限
     */
      @ApiModelProperty(value = "權限",name = "role")
      private String role;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getPhone() {
        return phone;
    }

      public void setPhone(String phone) {
          this.phone = phone;
      }
    
    public String getDepartment() {
        return department;
    }

      public void setDepartment(String department) {
          this.department = department;
      }
    
    public String getEmail() {
        return email;
    }

      public void setEmail(String email) {
          this.email = email;
      }
    
    public String getRole() {
        return role;
    }

      public void setRole(String role) {
          this.role = role;
      }

      public static final String ID = "id";

      public static final String USERNAME = "username";

      public static final String PASSWORD = "password";

      public static final String NAME = "name";

      public static final String PHONE = "phone";

      public static final String DEPARTMENT = "department";

      public static final String EMAIL = "email";

      public static final String ROLE = "role";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "SysUser{" +
              "id=" + id +
                  ", username=" + username +
                  ", password=" + password +
                  ", name=" + name +
                  ", phone=" + phone +
                  ", department=" + department +
                  ", email=" + email +
                  ", role=" + role +
              "}";
    }
}
