package apihome.modules.apitest.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 接口自动化测试的核心表
 *
 *  Created by zyanycall@gmail.com on 2019-07-27 17:50.
 */
@Data
@TableName("api_test_request")
public class ApiTestRequestEntity {

    /**
     * 主键id
     */
    @TableId
    private Long requestId;

    /**
     * 项目id，关联ID
     */
    private Long projectId;

    /**
     * host_id，关联ID
     */
    private Long hostId;

    /**
     * host_ip，host写的url
     */
    private String hostIp;

    /**
     * 请求名称
     */
    @NotBlank(message="请求名称不能为空")
    private String requestName;

    /**
     * 请求方法，如GET,POST
     */
    private String method;

    /**
     * url
     */
    private String url;

    /**
     * 请求协议，如HTTP,HTTPS
     */
    private String protocol;

    /**
     * headers集合，使用JSON格式
     */
    private String headers;

    /**
     * 请求参数
     */
    private String queryParams;

    /**
     * body体的类型
     */
    private String bodyMode;

    /**
     * body体的数据，使用JSON格式
     */
    private String bodyData;

    /**
     * 断言，使用JSON格式
     */
    private String validate;

    /**
     * 状态  0：正常状态（默认状态）  1：禁止关闭
     */
    private Integer status = 0;

    /**
     * 描述
     */
    private String remark;

    /**
     * 提交的人
     */
    private String createBy;

    /**
     * 修改的人
     */
    private String modifyBy;

    /**
     * 提交的时间
     */
    private Date gmtCreate;

    /**
     * 更新的时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModify;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public String getBodyMode() {
        return bodyMode;
    }

    public void setBodyMode(String bodyMode) {
        this.bodyMode = bodyMode;
    }

    public String getBodyData() {
        return bodyData;
    }

    public void setBodyData(String bodyData) {
        this.bodyData = bodyData;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }
}
