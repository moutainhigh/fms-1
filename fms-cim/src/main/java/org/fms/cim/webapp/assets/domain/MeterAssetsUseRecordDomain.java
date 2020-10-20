/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.cim.webapp.archives.domain.ManagerParamEntity;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

/**
 * 电能表资产领用记录表 METER_ASSETS_USE_RECORD
 *
 * @author riozenc
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterAssetsUseRecordDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;
	private Long meterAssetsId; 	// 资产id
	private String meterAssetsNo; 	// 资产编号
	private Long requisitionMan;	// 领用人	REQUISITIONING_MAN	bigint		
	private Long requisitionDept;	//	领用单位	REQUISITIONING_DEPT	bigint		
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date requisitionDate;	//	领用时间	REQUISITIONING_DATE	datetime		
	private String installSite;		//	安装位置	INSTALL_SITE	varchar(256)
	private Long issuer;			//	发放人	ISSUER	bigint	
	private Date createDate;	 	// 创建时间
	private String remark; 			// 备注
	private Byte status; 			// 状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMeterAssetsId() {
		return meterAssetsId;
	}
	public void setMeterAssetsId(Long meterAssetsId) {
		this.meterAssetsId = meterAssetsId;
	}
	public String getMeterAssetsNo() {
		return meterAssetsNo;
	}
	public void setMeterAssetsNo(String meterAssetsNo) {
		this.meterAssetsNo = meterAssetsNo;
	}
	public Long getRequisitionMan() {
		return requisitionMan;
	}
	public void setRequisitionMan(Long requisitionMan) {
		this.requisitionMan = requisitionMan;
	}
	public Long getRequisitionDept() {
		return requisitionDept;
	}
	public void setRequisitionDept(Long requisitionDept) {
		this.requisitionDept = requisitionDept;
	}
	public Date getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(Date requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getInstallSite() {
		return installSite;
	}
	public void setInstallSite(String installSite) {
		this.installSite = installSite;
	}
	public Long getIssuer() {
		return issuer;
	}
	public void setIssuer(Long issuer) {
		this.issuer = issuer;
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
 
	
}
