package org.fms.cim.webapp.archives.domain;

import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;
import java.util.Date;

/**
 * 台区	TG_INFO
 *
 * @author riozenc
 *
 */
public class TgInfoDomain extends ManagerParamEntity implements MybatisEntity {
 
	@TablePrimaryKey
    private Long id;// ID ID bigint TRUE FALSE TRUE
    private String tgNo; //台区编号
    private String tgName; //台区名称
    private Integer tgType; //公变专变标志
    private String address; //台区位置
    private Date createDate; //创建时间
    private String remark; //备注
    private Byte status; //状态
    private Byte commonTransClass; //公变分类 （楼房/平房/集抄/混合） COMMON_TRANS_CLASS
    private Long businessPlaceCode;  //BUSINESS_PLACE_CODE
    

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTgNo() {
		return tgNo;
	}
	public void setTgNo(String tgNo) {
		this.tgNo = tgNo;
	}
	public String getTgName() {
		return tgName;
	}
	public void setTgName(String tgName) {
		this.tgName = tgName;
	}
	public Integer getTgType() {
		return tgType;
	}
	public void setTgType(Integer tgType) {
		this.tgType = tgType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getCommonTransClass() {
		return commonTransClass;
	}
	public void setCommonTransClass(Byte commonTransClass) {
		this.commonTransClass = commonTransClass;
	}
	public Long getBusinessPlaceCode() {
		return businessPlaceCode;
	}
	public void setBusinessPlaceCode(Long businessPlaceCode) {
		this.businessPlaceCode = businessPlaceCode;
	}

}
