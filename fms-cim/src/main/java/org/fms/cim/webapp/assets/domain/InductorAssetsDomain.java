/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.util.Date;

import org.fms.cim.webapp.archives.domain.ManagerParamEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

/**
 * 互感器资产 INDUCTOR_ASSETS_INFO
 *
 * @author riozenc
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InductorAssetsDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private String inductorAssetsNo; // 资产编号
	private Byte inductorType; // 互感器类型
	private Long turns;	// 穿心匝数
	private Byte ctCurntCode; // CT电流
	private Byte ctVoltCode; // CT电压
	private Byte ptCurntCode; // PT电流
	private Byte ptVoltCode; // PT电压
	private Long ratedCtCode; // CT变比
	private Long ratedPtCode; // PT变比
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date madeDate; // 出厂日期
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date lastDetDate; // 上次检定日期
	private Integer detPeriod; // 检定周期(月)
	private Integer lifeSpan; // 使用寿命(月)
	private Long accuLevelCode; // CT准确等级
	private Long ptAccuLevelCode; // PT准确等级
	private Long deptId;	//部门id
	private Date createDate; // 创建时间
	private String remark; // 备注
	private Byte status; // 状态
	private String batchNo; // BATCH_NO 批次
	private Byte rightAttach; // 产权归属 RIGHT_ATTACH 
	private String manID; // 持有人MAN_ID 
	private String madeNo; //出厂编号 
	private Byte modelCode; // 型号 MODEL_CODE


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getInductorType() {
		return inductorType;
	}

	public void setInductorType(Byte inductorType) {
		this.inductorType = inductorType;
	}

	public Long getTurns() {
		return turns;
	}

	public void setTurns(Long turns) {
		this.turns = turns;
	}

	public Byte getCtCurntCode() {
		return ctCurntCode;
	}

	public void setCtCurntCode(Byte ctCurntCode) {
		this.ctCurntCode = ctCurntCode;
	}

	public Byte getCtVoltCode() {
		return ctVoltCode;
	}

	public void setCtVoltCode(Byte ctVoltCode) {
		this.ctVoltCode = ctVoltCode;
	}

	public Byte getPtCurntCode() {
		return ptCurntCode;
	}

	public void setPtCurntCode(Byte ptCurntCode) {
		this.ptCurntCode = ptCurntCode;
	}

	public Byte getPtVoltCode() {
		return ptVoltCode;
	}

	public void setPtVoltCode(Byte ptVoltCode) {
		this.ptVoltCode = ptVoltCode;
	}

	public Long getRatedCtCode() {
		return ratedCtCode;
	}

	public void setRatedCtCode(Long ratedCtCode) {
		this.ratedCtCode = ratedCtCode;
	}

	public Long getRatedPtCode() {
		return ratedPtCode;
	}

	public void setRatedPtCode(Long ratedPtCode) {
		this.ratedPtCode = ratedPtCode;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public Integer getLifeSpan() {
		return lifeSpan;
	}

	public void setLifeSpan(Integer lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	public Integer getDetPeriod() {
		return detPeriod;
	}

	public void setDetPeriod(Integer detPeriod) {
		this.detPeriod = detPeriod;
	}

	public Date getLastDetDate() {
		return lastDetDate;
	}

	public void setLastDetDate(Date lastDetDate) {
		this.lastDetDate = lastDetDate;
	}

	public Long getAccuLevelCode() {
		return accuLevelCode;
	}

	public void setAccuLevelCode(Long accuLevelCode) {
		this.accuLevelCode = accuLevelCode;
	}

	public Long getPtAccuLevelCode() {
		return ptAccuLevelCode;
	}

	public void setPtAccuLevelCode(Long ptAccuLevelCode) {
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

	public String getInductorAssetsNo() {
		return inductorAssetsNo;
	}

	public void setInductorAssetsNo(String inductorAssetsNo) {
		this.inductorAssetsNo = inductorAssetsNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Byte getRightAttach() {
		return rightAttach;
	}

	public void setRightAttach(Byte rightAttach) {
		this.rightAttach = rightAttach;
	}

	public String getManID() {
		return manID;
	}

	public void setManID(String manID) {
		this.manID = manID;
	}

	public String getMadeNo() {
		return madeNo;
	}

	public void setMadeNo(String madeNo) {
		this.madeNo = madeNo;
	}

	public Byte getModelCode() {
		return modelCode;
	}

	public void setModelCode(Byte modelCode) {
		this.modelCode = modelCode;
	}

}
