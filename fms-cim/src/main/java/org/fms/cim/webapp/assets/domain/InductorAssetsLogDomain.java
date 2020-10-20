/**
 *    Auth:riozenc
 *    Date:2019年3月21日 下午4:40:07
 *    Title:com.riozenc.cim.webapp.assets.domain.InductorAssetsLogDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.util.Date;

import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;
import com.riozenc.titanTool.mybatis.pagination.Page;

public class InductorAssetsLogDomain extends Page implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private String assetsNo; // 资产编号
	private Byte inductorType; // 互感器类型
	private String ctCurntCode; // CT电流
	private String ctVoltCode; // CT电压
	private String ptCurntCode; // PT电流
	private String ptVoltCode; // PT电压
	private String ratedCtCode; // CT变比
	private String ratedPtCode; // PT变比
	private String accuLevelCode; // CT准确等级
	private String ptAccuLevelCode; // PT准确等级
	private Long deptId;
	private Date createDate; // 创建时间
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

	public String getAssetsNo() {
		return assetsNo;
	}

	public void setAssetsNo(String assetsNo) {
		this.assetsNo = assetsNo;
	}

	public Byte getInductorType() {
		return inductorType;
	}

	public void setInductorType(Byte inductorType) {
		this.inductorType = inductorType;
	}

	public String getCtCurntCode() {
		return ctCurntCode;
	}

	public void setCtCurntCode(String ctCurntCode) {
		this.ctCurntCode = ctCurntCode;
	}

	public String getCtVoltCode() {
		return ctVoltCode;
	}

	public void setCtVoltCode(String ctVoltCode) {
		this.ctVoltCode = ctVoltCode;
	}

	public String getPtCurntCode() {
		return ptCurntCode;
	}

	public void setPtCurntCode(String ptCurntCode) {
		this.ptCurntCode = ptCurntCode;
	}

	public String getPtVoltCode() {
		return ptVoltCode;
	}

	public void setPtVoltCode(String ptVoltCode) {
		this.ptVoltCode = ptVoltCode;
	}

	public String getRatedCtCode() {
		return ratedCtCode;
	}

	public void setRatedCtCode(String ratedCtCode) {
		this.ratedCtCode = ratedCtCode;
	}

	public String getRatedPtCode() {
		return ratedPtCode;
	}

	public void setRatedPtCode(String ratedPtCode) {
		this.ratedPtCode = ratedPtCode;
	}

	public String getAccuLevelCode() {
		return accuLevelCode;
	}

	public void setAccuLevelCode(String accuLevelCode) {
		this.accuLevelCode = accuLevelCode;
	}

	public String getPtAccuLevelCode() {
		return ptAccuLevelCode;
	}

	public void setPtAccuLevelCode(String ptAccuLevelCode) {
		this.ptAccuLevelCode = ptAccuLevelCode;
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
