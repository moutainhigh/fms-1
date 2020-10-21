/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.math.BigDecimal;

import org.fms.cim.webapp.archives.domain.ManagerParamEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

/**
 *  电能表资产检定明细记录表_单相电能表
 *  METER_ASSETS_TEST_RECORD_DETAIL_UP 
 *
 * @author yhx
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterAssetsTestRecordDetailUpDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private Long recordId; 			//	检定报告ID	RECORD_ID	bigint			FALSE	FALSE	FALSE
	private Long meterAssetsId;  			//	资产ID	INDUCTOR_ASSETS_ID	bigint			FALSE	FALSE	FALSE
	private BigDecimal upAa; 				//	单相电能表AA	UP_AA	decimal(6.2)
	private BigDecimal upAb;				//	单相电能表AB	UP_AB	decimal(6.2)
	private BigDecimal upAc;				//	单相电能表AC	UP_AC	decimal(6.2)
	private BigDecimal upAd;				//	单相电能表AD	UP_AD	decimal(6.2)
	private BigDecimal upAe;				//	单相电能表AE	UP_AE	decimal(6.2)
	private BigDecimal upAf;				//	单相电能表AF	UP_AF	decimal(6.2)
	private BigDecimal upAg;				//	单相电能表AG	UP_AG	decimal(6.2)
	private BigDecimal upBa;				//	单相电能表BA	UP_BA	decimal(6.2)
	private BigDecimal upBb;				//	单相电能表BB	UP_BB	decimal(6.2)
	private BigDecimal upBc;				//	单相电能表BC	UP_BC	decimal(6.2)
	private BigDecimal upBd;				//	单相电能表BD	UP_BD	decimal(6.2)
	private BigDecimal upBe;				//	单相电能表BE	UP_BE	decimal(6.2)
	private BigDecimal upBf;				//	单相电能表BF	UP_BF	decimal(6.2)
	private BigDecimal upBg;				//	单相电能表BG	UP_BG	decimal(6.2)
	private BigDecimal upCa;				//	单相电能表CA	UP_CA	decimal(6.2)
	private BigDecimal upCb;				//	单相电能表CB	UP_CB	decimal(6.2)
	private BigDecimal upCc;				//	单相电能表CC	UP_CC	decimal(6.2)
	private BigDecimal upCd;				//	单相电能表CD	UP_CD	decimal(6.2)
	private BigDecimal upCe;				//	单相电能表CE	UP_CE	decimal(6.2)
	private BigDecimal upCf;				//	单相电能表CF	UP_CF	decimal(6.2)
	private BigDecimal upCg;				//	单相电能表CG	UP_CG	decimal(6.2)
	private String remark;
	
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
	
	public Long getMeterAssetsId() {
		return meterAssetsId;
	}
	public void setMeterAssetsId(Long meterAssetsId) {
		this.meterAssetsId = meterAssetsId;
	}
	public BigDecimal getUpAa() {
		return upAa;
	}
	public void setUpAa(BigDecimal upAa) {
		this.upAa = upAa;
	}
	public BigDecimal getUpAb() {
		return upAb;
	}
	public void setUpAb(BigDecimal upAb) {
		this.upAb = upAb;
	}
	public BigDecimal getUpAc() {
		return upAc;
	}
	public void setUpAc(BigDecimal upAc) {
		this.upAc = upAc;
	}
	public BigDecimal getUpAd() {
		return upAd;
	}
	public void setUpAd(BigDecimal upAd) {
		this.upAd = upAd;
	}
	public BigDecimal getUpAe() {
		return upAe;
	}
	public void setUpAe(BigDecimal upAe) {
		this.upAe = upAe;
	}
	public BigDecimal getUpAf() {
		return upAf;
	}
	public void setUpAf(BigDecimal upAf) {
		this.upAf = upAf;
	}
	public BigDecimal getUpAg() {
		return upAg;
	}
	public void setUpAg(BigDecimal upAg) {
		this.upAg = upAg;
	}
	public BigDecimal getUpBa() {
		return upBa;
	}
	public void setUpBa(BigDecimal upBa) {
		this.upBa = upBa;
	}
	public BigDecimal getUpBb() {
		return upBb;
	}
	public void setUpBb(BigDecimal upBb) {
		this.upBb = upBb;
	}
	public BigDecimal getUpBc() {
		return upBc;
	}
	public void setUpBc(BigDecimal upBc) {
		this.upBc = upBc;
	}
	public BigDecimal getUpBd() {
		return upBd;
	}
	public void setUpBd(BigDecimal upBd) {
		this.upBd = upBd;
	}
	public BigDecimal getUpBe() {
		return upBe;
	}
	public void setUpBe(BigDecimal upBe) {
		this.upBe = upBe;
	}
	public BigDecimal getUpBf() {
		return upBf;
	}
	public void setUpBf(BigDecimal upBf) {
		this.upBf = upBf;
	}
	public BigDecimal getUpBg() {
		return upBg;
	}
	public void setUpBg(BigDecimal upBg) {
		this.upBg = upBg;
	}
	public BigDecimal getUpCa() {
		return upCa;
	}
	public void setUpCa(BigDecimal upCa) {
		this.upCa = upCa;
	}
	public BigDecimal getUpCb() {
		return upCb;
	}
	public void setUpCb(BigDecimal upCb) {
		this.upCb = upCb;
	}
	public BigDecimal getUpCc() {
		return upCc;
	}
	public void setUpCc(BigDecimal upCc) {
		this.upCc = upCc;
	}
	public BigDecimal getUpCd() {
		return upCd;
	}
	public void setUpCd(BigDecimal upCd) {
		this.upCd = upCd;
	}
	public BigDecimal getUpCe() {
		return upCe;
	}
	public void setUpCe(BigDecimal upCe) {
		this.upCe = upCe;
	}
	public BigDecimal getUpCf() {
		return upCf;
	}
	public void setUpCf(BigDecimal upCf) {
		this.upCf = upCf;
	}
	public BigDecimal getUpCg() {
		return upCg;
	}
	public void setUpCg(BigDecimal upCg) {
		this.upCg = upCg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
										


}
