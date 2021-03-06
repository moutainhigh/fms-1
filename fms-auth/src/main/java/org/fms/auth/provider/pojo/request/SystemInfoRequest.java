package org.fms.auth.provider.pojo.request;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.fms.auth.provider.common.pojo.BaseRequestPojo;

@Table(name = "SYSTEM_INFO")
public class SystemInfoRequest extends BaseRequestPojo implements Serializable {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SYSTEM_NAME")
    private String systemName;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    /**
     * 0 禁用、1 启用
     */
    @Column(name = "ACTIVE")
    private Integer active;

    @Column(name = "SORT")
    private Integer sort;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    private static final long serialVersionUID = 1L;


    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return SYSTEM_NAME
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * @param systemName
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * @return PROJECT_NAME
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取0 禁用、1 启用
     *
     * @return ACTIVE - 0 禁用、1 启用
     */
    public Integer getActive() {
        return active;
    }

    /**
     * 设置0 禁用、1 启用
     *
     * @param active 0 禁用、1 启用
     */
    public void setActive(Integer active) {
        this.active = active;
    }

    /**
     * @return SORT
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return UPDATE_DATE
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}