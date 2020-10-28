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
 * 线路	LINE_INFO
 *
 * @author riozenc
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private String lineCode; //线路编号
	private String lineName; //线路名称
	private Byte voltType; //电压等级
	private Byte lineType; //线路类型
	private Date runDate; //投运日期
	private String switchNo; //开关号
	private String lineModel; //线路型号
	private BigDecimal lineLenght; //线路长度
	private Byte ratingCurnt; //额定电流
	private Byte ratingVol; //额定电压
	private Date createDate; //创建时间
	private String remark; //备注
	private Byte status; //状态
    private Long businessPlaceCode;  //BUSINESS_PLACE_CODE
    
    private Long pId; //上级线路标识
    private Long stationId; //首端厂站
    private Long eStationId; //末端厂站
    private Byte voltCode; //电压等级
    private String address; //地理位置
    private String guid; //接口标识
    private Integer weight; //显示顺序
    private Byte runStatusCode; //状态
    private Long creatorId; //创建者
    private Long lastModifierId; //最后修改者
    private String lastModifierTime; //最后修改时间
    
	private Long beginSubsId;
	private String beginSubsName; // 厂站名称
	private Long endSubsId;
	private String endSubsName; // 厂站名称
	private String subsName; // 厂站名称
	private List<Long> lineIds;

	public List<Long> getLineIds() {
		return lineIds;
	}

	public void setLineIds(List<Long> lineIds) {
		this.lineIds = lineIds;
	}

	public String getSubsName() {
		return subsName;
	}

	public void setSubsName(String subsName) {
		this.subsName = subsName;
	}

	public Long getBeginSubsId() {
		return beginSubsId;
	}
	public void setBeginSubsId(Long beginSubsId) {
		this.beginSubsId = beginSubsId;
	}
	public Long getEndSubsId() {
		return endSubsId;
	}
	public void setEndSubsId(Long endSubsId) {
		this.endSubsId = endSubsId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public Byte getVoltType() {
		return voltType;
	}
	public void setVoltType(Byte voltType) {
		this.voltType = voltType;
	}
	public Byte getLineType() {
		return lineType;
	}
	public void setLineType(Byte lineType) {
		this.lineType = lineType;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	public String getSwitchNo() {
		return switchNo;
	}
	public void setSwitchNo(String switchNo) {
		this.switchNo = switchNo;
	}
	public String getLineModel() {
		return lineModel;
	}
	public void setLineModel(String lineModel) {
		this.lineModel = lineModel;
	}
	public BigDecimal getLineLenght() {
		return lineLenght;
	}
	public void setLineLenght(BigDecimal lineLenght) {
		this.lineLenght = lineLenght;
	}
	public Byte getRatingCurnt() {
		return ratingCurnt;
	}
	public void setRatingCurnt(Byte ratingCurnt) {
		this.ratingCurnt = ratingCurnt;
	}
	public Byte getRatingVol() {
		return ratingVol;
	}
	public void setRatingVol(Byte ratingVol) {
		this.ratingVol = ratingVol;
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

	public String getBeginSubsName() {
		return beginSubsName;
	}

	public void setBeginSubsName(String beginSubsName) {
		this.beginSubsName = beginSubsName;
	}

	public String getEndSubsName() {
		return endSubsName;
	}

	public void setEndSubsName(String endSubsName) {
		this.endSubsName = endSubsName;
	}

	public Long getBusinessPlaceCode() {
		return businessPlaceCode;
	}

	public void setBusinessPlaceCode(Long businessPlaceCode) {
		this.businessPlaceCode = businessPlaceCode;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long geteStationId() {
		return eStationId;
	}

	public void seteStationId(Long eStationId) {
		this.eStationId = eStationId;
	}

	public Byte getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(Byte voltCode) {
		this.voltCode = voltCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Byte getRunStatusCode() {
		return runStatusCode;
	}

	public void setRunStatusCode(Byte runStatusCode) {
		this.runStatusCode = runStatusCode;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getLastModifierId() {
		return lastModifierId;
	}

	public void setLastModifierId(Long lastModifierId) {
		this.lastModifierId = lastModifierId;
	}

	public String getLastModifierTime() {
		return lastModifierTime;
	}

	public void setLastModifierTime(String lastModifierTime) {
		this.lastModifierTime = lastModifierTime;
	}






}
