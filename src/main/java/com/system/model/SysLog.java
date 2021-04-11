package com.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 日志信息
 * </p>
 *
 * @author Ablett_Chen
 * @since 2020-12-17
 */
@ApiModel(value="SysLog对象", description="日志信息")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

      /**
     * 日志ID
     */
        @TableId(type = IdType.AUTO)
  	@ApiModelProperty(value = "日志ID",name = "id")
    private Integer id;

      /**
     * 被操作的对象
     */
  	@ApiModelProperty(value = "被操作的对象",name = "operation")
    private String operation;

      /**
     * 方法名
     */
  	@ApiModelProperty(value = "方法名",name = "way")
    private String way;

      /**
     * 参数
     */
  	@ApiModelProperty(value = "参数",name = "argument")
    private String argument;

      /**
     * 操作人id
     */
  	@ApiModelProperty(value = "操作人id",name = "userid")
    private String userid;

      /**
     * 操作人
     */
  	@ApiModelProperty(value = "操作人",name = "name")
    private String name;

      /**
     * 日志描述
     */
  	@ApiModelProperty(value = "日志描述",name = "description")
    private String description;

      /**
     * 操作类型
     */
  	@ApiModelProperty(value = "操作类型",name = "operatetype")
    private String operatetype;

      /**
     * 创建时间
     */
  	@ApiModelProperty(value = "创建时间",name = "createtime")
    private Date createtime;

      /**
     * 用戶IP
     */
  	@ApiModelProperty(value = "用戶IP",name = "ip")
    private String ip;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getOperation() {
        return operation;
    }

      public void setOperation(String operation) {
          this.operation = operation;
      }
    
    public String getWay() {
        return way;
    }

      public void setWay(String way) {
          this.way = way;
      }
    
    public String getArgument() {
        return argument;
    }

      public void setArgument(String argument) {
          this.argument = argument;
      }
    
    public String getUserid() {
        return userid;
    }

      public void setUserid(String userid) {
          this.userid = userid;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
          this.description = description;
      }
    
    public String getOperatetype() {
        return operatetype;
    }

      public void setOperatetype(String operatetype) {
          this.operatetype = operatetype;
      }
    
    public Date getCreatetime() {
        return createtime;
    }

      public void setCreatetime(Date createtime) {
          this.createtime = createtime;
      }
    
    public String getIp() {
        return ip;
    }

      public void setIp(String ip) {
          this.ip = ip;
      }

      public static final String ID = "id";

      public static final String OPERATION = "operation";

      public static final String WAY = "way";

      public static final String ARGUMENT = "argument";

      public static final String USERID = "userid";

      public static final String NAME = "name";

      public static final String DESCRIPTION = "description";

      public static final String OPERATETYPE = "operatetype";

      public static final String CREATETIME = "createtime";

      public static final String IP = "ip";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "SysLog{" +
              "id=" + id +
                  ", operation=" + operation +
                  ", way=" + way +
                  ", argument=" + argument +
                  ", userid=" + userid +
                  ", name=" + name +
                  ", description=" + description +
                  ", operatetype=" + operatetype +
                  ", createtime=" + createtime +
                  ", ip=" + ip +
              "}";
    }
}
