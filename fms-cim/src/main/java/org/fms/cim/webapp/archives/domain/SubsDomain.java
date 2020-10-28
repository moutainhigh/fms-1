/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.archives.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

/**
 * 变电站	SUBS_INFO
 * 
 * @author riozenc
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubsDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private String subsNo; //变电站编码
	private String subsName; //名称
	private String shortName; //拼音简写
	private Byte voltType; //电压等级
	private String address; //变电站地址
	private Byte subsType; //厂站类型
	private Byte subsProp; //电厂性质
	private Byte mtNum; //主变台数
	private BigDecimal mtCap; //主变容量
	private Byte status; //运行状态
	private Date createDate;
    private Long businessPlaceCode;  //BUSINESS_PLACE_CODE
	private List<Long> businessPlaceCodes;
	//qt
	private int fOrgId;//发电集团	F_ORG_ID	NUMBER (16)
	private String khFlag;//是否参与考核	KH_FLAG	VARCHAR2(8)
	private   String runStatusCode;//运行状态	RUN_STATUS_CODE	VARCHAR2(8)
	private  String dateSrc;//接口	DATA_SRC	VARCHAR2(8)
	private  String dateId;//接口方标识	DATA_ID	VARCHAR2(64)
	private  String areaCode;//区域代码	AREA_CODE	VARCHAR2(8)
	private  int weight;//排序	WEIGHT	NUMBER(5)
	private  String guid;//接口标识	GUID	VARCHAR2(64)
	private  String remark;//备注	REMARK	VARCHAR2(256)
	private long creatorId;//创建者	CREATOR_ID	BIGINT (20)
	private  Date creteDate;//创建时间	CREATE_DATE	DATE
	private  long lastModifierId;//最后修改者	LAST_MODIFIER_ID	BIGINT (20)
	private  String lastModifierTime;//最后修改时间	LAST_MODIFIER_TIME	VARCHAR(20)
	private Byte voltCode; //电压等级	VOLT_CODE	smallint
	
	
	public int getfOrgId() {
		return fOrgId;
	}

	public void setfOrgId(int fOrgId) {
		this.fOrgId = fOrgId;
	}

	public String getKhFlag() {
		return khFlag;
	}

	public void setKhFlag(String khFlag) {
		this.khFlag = khFlag;
	}

	public String getRunStatusCode() {
		return runStatusCode;
	}

	public void setRunStatusCode(String runStatusCode) {
		this.runStatusCode = runStatusCode;
	}

	public String getDateSrc() {
		return dateSrc;
	}

	public void setDateSrc(String dateSrc) {
		this.dateSrc = dateSrc;
	}

	public String getDateId() {
		return dateId;
	}

	public void setDateId(String dateId) {
		this.dateId = dateId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreteDate() {
		return creteDate;
	}

	public void setCreteDate(Date creteDate) {
		this.creteDate = creteDate;
	}

	public long getLastModifierId() {
		return lastModifierId;
	}

	public void setLastModifierId(long lastModifierId) {
		this.lastModifierId = lastModifierId;
	}

	public String getLastModifierTime() {
		return lastModifierTime;
	}

	public void setLastModifierTime(String lastModifierTime) {
		this.lastModifierTime = lastModifierTime;
	}

	public Byte getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(Byte voltCode) {
		this.voltCode = voltCode;
	}

	public List<Long> getBusinessPlaceCodes() {
		return businessPlaceCodes;
	}

	public void setBusinessPlaceCodes(List<Long> businessPlaceCodes) {
		this.businessPlaceCodes = businessPlaceCodes;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubsNo() {
		return subsNo;
	}
	public void setSubsNo(String subsNo) {
		this.subsNo = subsNo;
	}
	public String getSubsName() {
		return subsName;
	}
	public void setSubsName(String subsName) {
		this.subsName = subsName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Byte getVoltType() {
		return voltType;
	}
	public void setVoltType(Byte voltType) {
		this.voltType = voltType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Byte getSubsType() {
		return subsType;
	}
	public void setSubsType(Byte subsType) {
		this.subsType = subsType;
	}
	public Byte getSubsProp() {
		return subsProp;
	}
	public void setSubsProp(Byte subsProp) {
		this.subsProp = subsProp;
	}
	public Byte getMtNum() {
		return mtNum;
	}
	public void setMtNum(Byte mtNum) {
		this.mtNum = mtNum;
	}
	public BigDecimal getMtCap() {
		return mtCap;
	}
	public void setMtCap(BigDecimal mtCap) {
		this.mtCap = mtCap;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getBusinessPlaceCode() {
		return businessPlaceCode;
	}
	public void setBusinessPlaceCode(Long businessPlaceCode) {
		this.businessPlaceCode = businessPlaceCode;
	}





}
