/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.cim.webapp.archives.domain.ManagerParamEntity;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

/**
 *  互感器资产检定结果记录表 INDUCTOR_ASSETS_TEST_RECORD_DETAIL
 *
 * @author yhx
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InductorAssetsTestRecordDetailDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private Long recordId;				//	检定报告ID	RECORD_ID	bigint			FALSE	FALSE	FALSE
	private Long inductorAssetsID;			//	资产ID	INDUCTOR_ASSETS_ID	bigint			FALSE	FALSE	FALSE
	private BigDecimal volRatio80;			//	电压比值差80	VOL_RATIO80	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal volRatio100;			//	电压比值差100	VOL_RATIO100	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal volRatio120;			//	电压比值差120	VOL_RATIO120	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal volPhase80;			//	电压相位差80	VOL_PHASE80	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal volPhase100;			//	电压相位差100	VOL_PHASE100	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal volPhase120;			//	电压相位差120	VOL_PHASE120	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentRatio5;		//	电流比值差5	CURRENT_RATIO5	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentRatio20;		//	电流比值差20	CURRENT_RATIO20	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentRatio100;		//	电流比值差100	CURRENT_RATIO100	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentRatio120;		//	电流比值差120	CURRENT_RATIO120	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentPhase5;		//	电流相位差5	CURRENT_PHASE5	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentPhase20;		//	电流相位差20	CURRENT_PHASE20	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentPhase100;		//	电流相位差100	CURRENT_PHASE100	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	private BigDecimal currentPhase120;		//	电流相位差120	CURRENT_PHASE120	decimal(6.2)	6	2	FALSE	FALSE	FALSE
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate;				//	创建日期	CREATE_DATE	datetime
	private String remark;					//	备注	REMARK	varchar(256)
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Long getInductorAssetsID() {
		return inductorAssetsID;
	}
	public void setInductorAssetsID(Long inductorAssetsID) {
		this.inductorAssetsID = inductorAssetsID;
	}
	public BigDecimal getVolRatio80() {
		return volRatio80;
	}
	public void setVolRatio80(BigDecimal volRatio80) {
		this.volRatio80 = volRatio80;
	}
	public BigDecimal getVolRatio100() {
		return volRatio100;
	}
	public void setVolRatio100(BigDecimal volRatio100) {
		this.volRatio100 = volRatio100;
	}
	public BigDecimal getVolRatio120() {
		return volRatio120;
	}
	public void setVolRatio120(BigDecimal volRatio120) {
		this.volRatio120 = volRatio120;
	}
	public BigDecimal getVolPhase80() {
		return volPhase80;
	}
	public void setVolPhase80(BigDecimal volPhase80) {
		this.volPhase80 = volPhase80;
	}
	public BigDecimal getVolPhase100() {
		return volPhase100;
	}
	public void setVolPhase100(BigDecimal volPhase100) {
		this.volPhase100 = volPhase100;
	}
	public BigDecimal getVolPhase120() {
		return volPhase120;
	}
	public void setVolPhase120(BigDecimal volPhase120) {
		this.volPhase120 = volPhase120;
	}
	public BigDecimal getCurrentRatio5() {
		return currentRatio5;
	}
	public void setCurrentRatio5(BigDecimal currentRatio5) {
		this.currentRatio5 = currentRatio5;
	}
	public BigDecimal getCurrentRatio20() {
		return currentRatio20;
	}
	public void setCurrentRatio20(BigDecimal currentRatio20) {
		this.currentRatio20 = currentRatio20;
	}
	public BigDecimal getCurrentRatio100() {
		return currentRatio100;
	}
	public void setCurrentRatio100(BigDecimal currentRatio100) {
		this.currentRatio100 = currentRatio100;
	}
	public BigDecimal getCurrentRatio120() {
		return currentRatio120;
	}
	public void setCurrentRatio120(BigDecimal currentRatio120) {
		this.currentRatio120 = currentRatio120;
	}
	public BigDecimal getCurrentPhase5() {
		return currentPhase5;
	}
	public void setCurrentPhase5(BigDecimal currentPhase5) {
		this.currentPhase5 = currentPhase5;
	}
	public BigDecimal getCurrentPhase20() {
		return currentPhase20;
	}
	public void setCurrentPhase20(BigDecimal currentPhase20) {
		this.currentPhase20 = currentPhase20;
	}
	public BigDecimal getCurrentPhase100() {
		return currentPhase100;
	}
	public void setCurrentPhase100(BigDecimal currentPhase100) {
		this.currentPhase100 = currentPhase100;
	}
	public BigDecimal getCurrentPhase120() {
		return currentPhase120;
	}
	public void setCurrentPhase120(BigDecimal currentPhase120) {
		this.currentPhase120 = currentPhase120;
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
	
	

}
