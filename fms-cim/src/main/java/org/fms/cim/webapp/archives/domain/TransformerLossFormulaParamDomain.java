package org.fms.cim.webapp.archives.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformerLossFormulaParamDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private Integer transformerType;// 变压器型号
	private BigDecimal capacity;// 容量
	private BigDecimal emptyLose;// 空载损耗
	private BigDecimal emptyCurrent;// 空载电流
	private BigDecimal loadVoltage;// 负载电压
	private BigDecimal loadLoss;// 负载损耗
	private String remark;// 备注
	private Byte status;//
	private Date operationDate;// 操作日期
	private String operator;// 操作人

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTransformerType() {
		return transformerType;
	}

	public void setTransformerType(Integer transformerType) {
		this.transformerType = transformerType;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getEmptyLose() {
		return emptyLose;
	}

	public void setEmptyLose(BigDecimal emptyLose) {
		this.emptyLose = emptyLose;
	}

	public BigDecimal getEmptyCurrent() {
		return emptyCurrent;
	}

	public void setEmptyCurrent(BigDecimal emptyCurrent) {
		this.emptyCurrent = emptyCurrent;
	}

	public BigDecimal getLoadVoltage() {
		return loadVoltage;
	}

	public void setLoadVoltage(BigDecimal loadVoltage) {
		this.loadVoltage = loadVoltage;
	}

	public BigDecimal getLoadLoss() {
		return loadLoss;
	}

	public void setLoadLoss(BigDecimal loadLoss) {
		this.loadLoss = loadLoss;
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

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
