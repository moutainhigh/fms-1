package org.fms.cim.webapp.archives.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterMpedRelDomain extends ManagerParamEntity implements MybatisEntity{
	
	
	@TablePrimaryKey
	private Long id; //ID	ID	bigint
	private Long meterId;//计费点ID	METER_ID	bigint
	private Long mpedId;//计量点ID	MPED_ID	bigint
	private Byte phaseSeq;//相序	PHASE_SEQ	smallint
	private Byte functionCode;//功能代码	FUNCTION_CODE	smallint
	private Byte powerDirection;//功率方向	POWER_DIRECTION	smallint
	private Byte tsFlag;//分时标识	TS_FLAG	smallint
	private Date createDate;//创建时间	CREATE_DATE	datetime
	private Byte status;//状态	STATUS	smallint
	private BigDecimal factorNum;//综合倍率	FACTOR_NUM	decimal
	private Long writeSn;//抄表序号	WRITE_SN	bigint
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMeterId() {
		return meterId;
	}
	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}
	public Long getMpedId() {
		return mpedId;
	}
	public void setMpedId(Long mpedId) {
		this.mpedId = mpedId;
	}
	public Byte getPhaseSeq() {
		return phaseSeq;
	}
	public void setPhaseSeq(Byte phaseSeq) {
		this.phaseSeq = phaseSeq;
	}
	public Byte getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(Byte functionCode) {
		this.functionCode = functionCode;
	}
	public Byte getPowerDirection() {
		return powerDirection;
	}
	public void setPowerDirection(Byte powerDirection) {
		this.powerDirection = powerDirection;
	}
	public Byte getTsFlag() {
		return tsFlag;
	}
	public void setTsFlag(Byte tsFlag) {
		this.tsFlag = tsFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public BigDecimal getFactorNum() {
		return factorNum;
	}
	public void setFactorNum(BigDecimal factorNum) {
		this.factorNum = factorNum;
	}
	public Long getWriteSn() {
		return writeSn;
	}
	public void setWriteSn(Long writeSn) {
		this.writeSn = writeSn;
	}

	
	
	
	
	
	
}
