package org.fms.cim.webapp.archives.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformerMeterRelationDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private Long transformerId;// 变压器ID TRANSFORMER_ID bigint FALSE FALSE FALSE
	private Long meterId;// 计量点ID METER_ID bigint FALSE FALSE FALSE
	private Byte msType;// 计量方式 MS_TYPE smallint(2) 2 FALSE FALSE FALSE
	private String transformerGroupNo;// 变压器组号 TRANSFORMER_GROUP_HP smallint FALSE FALSE FALSE
	private Date createDate;// 创建时间 CREATE_DATE datetime FALSE FALSE FALSE
	private String remark;// 备注 REMARK varchar(256) 256 FALSE FALSE FALSE
	private Byte status;// 状态 STATUS smallint FALSE FALSE FALSE
	private Integer loadChangeSign;
	
	private Byte transLostType; // 变损分摊方式
	private BigDecimal transLostNum; // 变损率or变损值
	
	private String meterNo;
	private String meterName;
	private String userName;
	
	private String transformerNo;
	private String deskName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransformerId() {
		return transformerId;
	}

	public void setTransformerId(Long transformerId) {
		this.transformerId = transformerId;
	}

	public Long getMeterId() {
		return meterId;
	}

	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}

	public Byte getMsType() {
		return msType;
	}

	public void setMsType(Byte msType) {
		this.msType = msType;
	}

	public String getTransformerGroupNo() {
		return transformerGroupNo;
	}

	public void setTransformerGroupNo(String transformerGroupNo) {
		this.transformerGroupNo = transformerGroupNo;
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

	public Integer getLoadChangeSign() {
		return loadChangeSign;
	}

	public void setLoadChangeSign(Integer loadChangeSign) {
		this.loadChangeSign = loadChangeSign;
	}

	public BigDecimal getTransLostNum() {
		return transLostNum;
	}

	public void setTransLostNum(BigDecimal transLostNum) {
		this.transLostNum = transLostNum;
	}

	public Byte getTransLostType() {
		return transLostType;
	}

	public void setTransLostType(Byte transLostType) {
		this.transLostType = transLostType;
	}

	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}

	public String getTransformerNo() {
		return transformerNo;
	}

	public void setTransformerNo(String transformerNo) {
		this.transformerNo = transformerNo;
	}

	public String getDeskName() {
		return deskName;
	}

	public void setDeskName(String deskName) {
		this.deskName = deskName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
