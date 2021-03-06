package org.fms.auth.provider.mapper.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "DEPT_INFO")
public class DeptInfo implements Serializable,Cloneable {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PARENT_ID")
    private Integer parentId;

    @Column(name = "DEPT_ID")
    private String deptId;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "DEPT_TYPE")
    private Integer deptType;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "CLOSE_DATE")
    private Date closeDate;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "STATUS")
    private Short status;

    @Column(name = "SORT_NO")
    private Integer sortNo;
    /**
     * 下级部门
     */
    private List<DeptInfo> children;

    private List<String> deptIdList;

    @Transient
    private String deptIdLike;

    @Transient
    private Integer roleId;

    @Transient
    private boolean  checked;

    @Transient
    private boolean  expand;

    @Transient
    private String title;

    private static final long serialVersionUID = 1L;

    /**
     * @return the value of DEPT_INFO.ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the value for DEPT_INFO.ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the value of DEPT_INFO.PARENT_ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId the value for DEPT_INFO.PARENT_ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the value of DEPT_INFO.DEPT_ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId the value for DEPT_INFO.DEPT_ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }


    /**
     * @return the value of DEPT_INFO.DEPT_TYPE
     */
    public Integer getDeptType() {
        return deptType;
    }

    /**
     * @param deptType the value for DEPT_INFO.DEPT_TYPE
     */
    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }

    /**
     * @return the value of DEPT_INFO.CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the value for DEPT_INFO.CREATE_DATE
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the value of DEPT_INFO.CLOSE_DATE
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate the value for DEPT_INFO.CLOSE_DATE
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the value of DEPT_INFO.REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the value for DEPT_INFO.REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return the value of DEPT_INFO.STATUS
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status the value for DEPT_INFO.STATUS
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public List<DeptInfo> getChildren() {
        return children;
    }

    public void setChildren(List<DeptInfo> children) {
        this.children = children;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(List<String> deptIdList) {
        this.deptIdList = deptIdList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeptIdLike() {
        return deptIdLike;
    }

    public void setDeptIdLike(String deptIdLike) {
        this.deptIdLike = deptIdLike;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", deptId=").append(deptId);
        sb.append(", deptName=").append(deptName);
        sb.append(", title=").append(title);
        sb.append(", deptType=").append(deptType);
        sb.append(", createDate=").append(createDate);
        sb.append(", closeDate=").append(closeDate);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Object clone(){
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
}