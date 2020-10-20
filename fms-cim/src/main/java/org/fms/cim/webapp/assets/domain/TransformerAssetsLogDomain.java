/**
 *    Auth:riozenc
 *    Date:2019年3月21日 下午4:42:03
 *    Title:com.riozenc.cim.webapp.assets.domain.TransformerAssetsLogDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;
import com.riozenc.titanTool.mybatis.pagination.Page;

public class TransformerAssetsLogDomain extends Page implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private String transformerAssetsNo; // 变压器资产编号
	private String voltageRatio; // 电压比
	private String currentRatio; // 电流比
	private Byte transformerModelType; // 型号
	private Byte factoryType; // 制造厂家
	private String madeNo; // 出厂编号
	private Date madeDate; // 出厂日期
	private BigDecimal ratedVoltage; // 额定电压
	private BigDecimal ratedCurrent; // 额定电流
	private BigDecimal shortVoltage; // 短路电压
	private String connectType; // 接线方式
	private Byte numPhaseType; // 相数
	private BigDecimal emptyLoose; // 空载损耗
	private BigDecimal emptyCurrent; // 空载电流
	private BigDecimal shortLoose; // 短路损耗
	private BigDecimal rt; // 阻抗电压
	private String oilNo; // 油号
	private Byte voltAttach; // 调压接头数
	private BigDecimal hvLimit; // 高压熔丝限额(A)
	private BigDecimal theroem; // 额定温升
	private BigDecimal firstVolt; // 一次侧电压
	private BigDecimal secondVolt; // 二次侧电压
	private Long deptId;
	private Date createDate; // 创建日期
	private String remark; // 备注
	private Byte status; // 状态
	private String operation;// 操作 OPERATION varchar(32) 32 FALSE FALSE FALSE
	private Long operator;// 操作人 OPERATOR bigint FALSE FALSE FALSE
	private Date operationDate;// 操作时间 OPERATION_DATE datetime FALSE FALSE FALSE

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransformerAssetsNo() {
		return transformerAssetsNo;
	}

	public void setTransformerAssetsNo(String transformerAssetsNo) {
		this.transformerAssetsNo = transformerAssetsNo;
	}

	public String getVoltageRatio() {
		return voltageRatio;
	}

	public void setVoltageRatio(String voltageRatio) {
		this.voltageRatio = voltageRatio;
	}

	public String getCurrentRatio() {
		return currentRatio;
	}

	public void setCurrentRatio(String currentRatio) {
		this.currentRatio = currentRatio;
	}

	public Byte getTransformerModelType() {
		return transformerModelType;
	}

	public void setTransformerModelType(Byte transformerModelType) {
		this.transformerModelType = transformerModelType;
	}

	public Byte getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(Byte factoryType) {
		this.factoryType = factoryType;
	}

	public String getMadeNo() {
		return madeNo;
	}

	public void setMadeNo(String madeNo) {
		this.madeNo = madeNo;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public BigDecimal getRatedVoltage() {
		return ratedVoltage;
	}

	public void setRatedVoltage(BigDecimal ratedVoltage) {
		this.ratedVoltage = ratedVoltage;
	}

	public BigDecimal getRatedCurrent() {
		return ratedCurrent;
	}

	public void setRatedCurrent(BigDecimal ratedCurrent) {
		this.ratedCurrent = ratedCurrent;
	}

	public BigDecimal getShortVoltage() {
		return shortVoltage;
	}

	public void setShortVoltage(BigDecimal shortVoltage) {
		this.shortVoltage = shortVoltage;
	}

	public String getConnectType() {
		return connectType;
	}

	public void setConnectType(String connectType) {
		this.connectType = connectType;
	}

	public Byte getNumPhaseType() {
		return numPhaseType;
	}

	public void setNumPhaseType(Byte numPhaseType) {
		this.numPhaseType = numPhaseType;
	}

	public BigDecimal getEmptyLoose() {
		return emptyLoose;
	}

	public void setEmptyLoose(BigDecimal emptyLoose) {
		this.emptyLoose = emptyLoose;
	}

	public BigDecimal getEmptyCurrent() {
		return emptyCurrent;
	}

	public void setEmptyCurrent(BigDecimal emptyCurrent) {
		this.emptyCurrent = emptyCurrent;
	}

	public BigDecimal getShortLoose() {
		return shortLoose;
	}

	public void setShortLoose(BigDecimal shortLoose) {
		this.shortLoose = shortLoose;
	}

	public BigDecimal getRt() {
		return rt;
	}

	public void setRt(BigDecimal rt) {
		this.rt = rt;
	}

	public String getOilNo() {
		return oilNo;
	}

	public void setOilNo(String oilNo) {
		this.oilNo = oilNo;
	}

	public Byte getVoltAttach() {
		return voltAttach;
	}

	public void setVoltAttach(Byte voltAttach) {
		this.voltAttach = voltAttach;
	}

	public BigDecimal getHvLimit() {
		return hvLimit;
	}

	public void setHvLimit(BigDecimal hvLimit) {
		this.hvLimit = hvLimit;
	}

	public BigDecimal getTheroem() {
		return theroem;
	}

	public void setTheroem(BigDecimal theroem) {
		this.theroem = theroem;
	}

	public BigDecimal getFirstVolt() {
		return firstVolt;
	}

	public void setFirstVolt(BigDecimal firstVolt) {
		this.firstVolt = firstVolt;
	}

	public BigDecimal getSecondVolt() {
		return secondVolt;
	}

	public void setSecondVolt(BigDecimal secondVolt) {
		this.secondVolt = secondVolt;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

}
