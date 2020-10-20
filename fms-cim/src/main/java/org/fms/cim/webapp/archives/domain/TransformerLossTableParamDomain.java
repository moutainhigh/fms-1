package org.fms.cim.webapp.archives.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformerLossTableParamDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private Byte transformerType;// 变压器型号 TRANSFORMER_TYPE smallint FALSE FALSE FALSE
	private BigDecimal capacity;// 容量 CAPACITY decimal(8,2) 8 2 FALSE FALSE FALSE
	private Byte voltLevelType; // 电压等级
	private BigDecimal powerLowerLimit;// 电量下限
	private BigDecimal powerUpperLimit;// 电量上限
	private BigDecimal activeTransformerLoss;// 有功变损
	private BigDecimal reactiveTransformerLoss;// 无功变损
	private Byte status;//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getTransformerType() {
		return transformerType;
	}

	public void setTransformerType(Byte transformerType) {
		this.transformerType = transformerType;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public Byte getVoltLevelType() {
		return voltLevelType;
	}

	public void setVoltLevelType(Byte voltLevelType) {
		this.voltLevelType = voltLevelType;
	}

	public BigDecimal getPowerLowerLimit() {
		return powerLowerLimit;
	}

	public void setPowerLowerLimit(BigDecimal powerLowerLimit) {
		this.powerLowerLimit = powerLowerLimit;
	}

	public BigDecimal getPowerUpperLimit() {
		return powerUpperLimit;
	}

	public void setPowerUpperLimit(BigDecimal powerUpperLimit) {
		this.powerUpperLimit = powerUpperLimit;
	}

	public BigDecimal getActiveTransformerLoss() {
		return activeTransformerLoss;
	}

	public void setActiveTransformerLoss(BigDecimal activeTransformerLoss) {
		this.activeTransformerLoss = activeTransformerLoss;
	}

	public BigDecimal getReactiveTransformerLoss() {
		return reactiveTransformerLoss;
	}

	public void setReactiveTransformerLoss(BigDecimal reactiveTransformerLoss) {
		this.reactiveTransformerLoss = reactiveTransformerLoss;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
