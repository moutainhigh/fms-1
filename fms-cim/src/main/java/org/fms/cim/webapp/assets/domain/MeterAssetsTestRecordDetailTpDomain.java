/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.cim.webapp.archives.domain.ManagerParamEntity;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

/**
 *  电能表资产检定明细记录表_三相平衡、负载
 *  METER_ASSETS_TEST_RECORD_DETAIL_TP 
 *
 * @author yhx
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterAssetsTestRecordDetailTpDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private Long recordId; 			//	检定报告ID	RECORD_ID	bigint			FALSE	FALSE	FALSE
	private Long meterAssetsId;  			//	资产ID	INDUCTOR_ASSETS_ID	bigint			FALSE	FALSE	FALSE
	private BigDecimal tpLoadAa;	//	三相平衡负载AA	TP_LOAD_AA	decimal(6.2)
	private BigDecimal tpLoadAb;	//	三相平衡负载AB	TP_LOAD_AB	decimal(6.2)
	private BigDecimal tpLoadAc;	//	三相平衡负载AC	TP_LOAD_AC	decimal(6.2)
	private BigDecimal tpLoadAd;	//	三相平衡负载AD	TP_LOAD_AD	decimal(6.2)
	private BigDecimal tpLoadAe;	//	三相平衡负载AE	TP_LOAD_AE	decimal(6.2)
	private BigDecimal tpLoadAf;	//	三相平衡负载AF	TP_LOAD_AF	decimal(6.2)
	private BigDecimal tpLoadAg;	//	三相平衡负载AG	TP_LOAD_AG	decimal(6.2)
	private BigDecimal tpLoadAh;	//	三相平衡负载AH	TP_LOAD_AH	decimal(6.2)
	private BigDecimal tpLoadBa;	//	三相平衡负载BA	TP_LOAD_BA	decimal(6.2)
	private BigDecimal tpLoadBb;	//	三相平衡负载BB	TP_LOAD_BB	decimal(6.2)
	private BigDecimal tpLoadBc;	//	三相平衡负载BC	TP_LOAD_BC	decimal(6.2)
	private BigDecimal tpLoadBd;	//	三相平衡负载BD	TP_LOAD_BD	decimal(6.2)
	private BigDecimal tpLoadBe;	//	三相平衡负载BE	TP_LOAD_BE	decimal(6.2)
	private BigDecimal tpLoadBf;	//	三相平衡负载BF	TP_LOAD_BF	decimal(6.2)
	private BigDecimal tpLoadBg;	//	三相平衡负载BG	TP_LOAD_BG	decimal(6.2)
	private BigDecimal tpLoadBh;	//	三相平衡负载BH	TP_LOAD_BH	decimal(6.2)
	private BigDecimal tpLoadCa;	//	三相平衡负载CA	TP_LOAD_CA	decimal(6.2)
	private BigDecimal tpLoadCb;	//	三相平衡负载CB	TP_LOAD_CB	decimal(6.2)
	private BigDecimal tpLoadCc;	//	三相平衡负载CC	TP_LOAD_CC	decimal(6.2)
	private BigDecimal tpLoadCd;	//	三相平衡负载CD	TP_LOAD_CD	decimal(6.2)
	private BigDecimal tpLoadCe;	//	三相平衡负载CE	TP_LOAD_CE	decimal(6.2)
	private BigDecimal tpLoadCf;	//	三相平衡负载CF	TP_LOAD_CF	decimal(6.2)
	private BigDecimal tpLoadCg;	//	三相平衡负载CG	TP_LOAD_CG	decimal(6.2)
	private BigDecimal tpLoadCh;	//	三相平衡负载CH	TP_LOAD_CH	decimal(6.2)
	private BigDecimal tpLoadDa;	//	三相平衡负载DA	TP_LOAD_DA	decimal(6.2)
	private BigDecimal tpLoadDb;	//	三相平衡负载DB	TP_LOAD_DB	decimal(6.2)
	private BigDecimal tpLoadDc;	//	三相平衡负载DC	TP_LOAD_DC	decimal(6.2)
	private BigDecimal tpLoadDd;	//	三相平衡负载DD	TP_LOAD_DD	decimal(6.2)
	private BigDecimal tpLoadDe;	//	三相平衡负载DE	TP_LOAD_DE	decimal(6.2)
	private BigDecimal tpLoadDf;	//	三相平衡负载DF	TP_LOAD_DF	decimal(6.2)
	private BigDecimal tpLoadDg;	//	三相平衡负载DG	TP_LOAD_DG	decimal(6.2)
	private BigDecimal tpLoadDh;	//	三相平衡负载DH	TP_LOAD_DH	decimal(6.2)
	private BigDecimal tpGroupAa;	//	三相分组试验AA	TP_GROUP_AA	decimal(6.2)
	private BigDecimal tpGroupAb;	//	三相分组试验AB	TP_GROUP_AB	decimal(6.2)
	private BigDecimal tpGroupAc;	//	三相分组试验AC	TP_GROUP_AC	decimal(6.2)
	private BigDecimal tpGroupAd;	//	三相分组试验AD	TP_GROUP_AD	decimal(6.2)
	private BigDecimal tpGroupAe;	//	三相分组试验AE	TP_GROUP_AE	decimal(6.2)
	private BigDecimal tpGroupAf;	//	三相分组试验AF	TP_GROUP_AF	decimal(6.2)
	private BigDecimal tpGroupAg;	//	三相分组试验AG	TP_GROUP_AG	decimal(6.2)
	private BigDecimal tpGroupAh;	//	三相分组试验AH	TP_GROUP_AH	decimal(6.2)
	private BigDecimal tpGroupAi;	//	三相分组试验AI	TP_GROUP_AI	decimal(6.2)
	private BigDecimal tpGroupAj;	//	三相分组试验AJ	TP_GROUP_AJ	decimal(6.2)
	private BigDecimal tpGroupAk;	//	三相分组试验AK	TP_GROUP_AK	decimal(6.2)
	private BigDecimal tpGroupAl;	//	三相分组试验AL	TP_GROUP_AL	decimal(6.2)
	private BigDecimal tpGroupBa;	//	三相分组试验BA	TP_GROUP_BA	decimal(6.2)
	private BigDecimal tpGroupBb;	//	三相分组试验BB	TP_GROUP_BB	decimal(6.2)
	private BigDecimal tpGroupBc;	//	三相分组试验BC	TP_GROUP_BC	decimal(6.2)
	private BigDecimal tpGroupBd;	//	三相分组试验BD	TP_GROUP_BD	decimal(6.2)
	private BigDecimal tpGroupBe;	//	三相分组试验BE	TP_GROUP_BE	decimal(6.2)
	private BigDecimal tpGroupBf;	//	三相分组试验BF	TP_GROUP_BF	decimal(6.2)
	private BigDecimal tpGroupBg;	//	三相分组试验BG	TP_GROUP_BG	decimal(6.2)
	private BigDecimal tpGroupBh;	//	三相分组试验BH	TP_GROUP_BH	decimal(6.2)
	private BigDecimal tpGroupBi;	//	三相分组试验BI	TP_GROUP_BI	decimal(6.2)
	private BigDecimal tpGroupBj;	//	三相分组试验BJ	TP_GROUP_BJ	decimal(6.2)
	private BigDecimal tpGroupBk;	//	三相分组试验BK	TP_GROUP_BK	decimal(6.2)
	private BigDecimal tpGroupBl;	//	三相分组试验BL	TP_GROUP_BL	decimal(6.2)
	private BigDecimal tpGroupCa;	//	三相分组试验CA	TP_GROUP_CA	decimal(6.2)
	private BigDecimal tpGroupCb;	//	三相分组试验CB	TP_GROUP_CB	decimal(6.2)
	private BigDecimal tpGroupCc;	//	三相分组试验CC	TP_GROUP_CC	decimal(6.2)
	private BigDecimal tpGroupCd;	//	三相分组试验CD	TP_GROUP_CD	decimal(6.2)
	private BigDecimal tpGroupCe;	//	三相分组试验CE	TP_GROUP_CE	decimal(6.2)
	private BigDecimal tpGroupCf;	//	三相分组试验CF	TP_GROUP_CF	decimal(6.2)
	private BigDecimal tpGroupCg;	//	三相分组试验CG	TP_GROUP_CG	decimal(6.2)
	private BigDecimal tpGroupCh;	//	三相分组试验CH	TP_GROUP_CH	decimal(6.2)
	private BigDecimal tpGroupCi;	//	三相分组试验CI	TP_GROUP_CI	decimal(6.2)
	private BigDecimal tpGroupCj;	//	三相分组试验CJ	TP_GROUP_CJ	decimal(6.2)
	private BigDecimal tpGroupCk;	//	三相分组试验CK	TP_GROUP_CK	decimal(6.2)
	private BigDecimal tpGroupCl;	//	三相分组试验CL	TP_GROUP_CL	decimal(6.2)
	private BigDecimal tpGroupDa;	//	三相分组试验DA	TP_GROUP_DA	decimal(6.2)
	private BigDecimal tpGroupDb;	//	三相分组试验DB	TP_GROUP_DB	decimal(6.2)
	private BigDecimal tpGroupDc;	//	三相分组试验DC	TP_GROUP_DC	decimal(6.2)
	private BigDecimal tpGroupDd;	//	三相分组试验DD	TP_GROUP_DD	decimal(6.2)
	private BigDecimal tpGroupDe;	//	三相分组试验DE	TP_GROUP_DE	decimal(6.2)
	private BigDecimal tpGroupDf;	//	三相分组试验DF	TP_GROUP_DF	decimal(6.2)
	private BigDecimal tpGroupDg;	//	三相分组试验DG	TP_GROUP_DG	decimal(6.2)
	private BigDecimal tpGroupDh;	//	三相分组试验DH	TP_GROUP_DH	decimal(6.2)
	private BigDecimal tpGroupDi;	//	三相分组试验DI	TP_GROUP_DI	decimal(6.2)
	private BigDecimal tpGroupDj;	//	三相分组试验DJ	TP_GROUP_DJ	decimal(6.2)
	private BigDecimal tpGroupDk;	//	三相分组试验DK	TP_GROUP_DK	decimal(6.2)
	private BigDecimal tpGroupDl;	//	三相分组试验DL	TP_GROUP_DL	decimal(6.2)
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
	public BigDecimal getTpLoadAa() {
		return tpLoadAa;
	}
	public void setTpLoadAa(BigDecimal tpLoadAa) {
		this.tpLoadAa = tpLoadAa;
	}
	public BigDecimal getTpLoadAb() {
		return tpLoadAb;
	}
	public void setTpLoadAb(BigDecimal tpLoadAb) {
		this.tpLoadAb = tpLoadAb;
	}
	public BigDecimal getTpLoadAc() {
		return tpLoadAc;
	}
	public void setTpLoadAc(BigDecimal tpLoadAc) {
		this.tpLoadAc = tpLoadAc;
	}
	public BigDecimal getTpLoadAd() {
		return tpLoadAd;
	}
	public void setTpLoadAd(BigDecimal tpLoadAd) {
		this.tpLoadAd = tpLoadAd;
	}
	public BigDecimal getTpLoadAe() {
		return tpLoadAe;
	}
	public void setTpLoadAe(BigDecimal tpLoadAe) {
		this.tpLoadAe = tpLoadAe;
	}
	public BigDecimal getTpLoadAf() {
		return tpLoadAf;
	}
	public void setTpLoadAf(BigDecimal tpLoadAf) {
		this.tpLoadAf = tpLoadAf;
	}
	public BigDecimal getTpLoadAg() {
		return tpLoadAg;
	}
	public void setTpLoadAg(BigDecimal tpLoadAg) {
		this.tpLoadAg = tpLoadAg;
	}
	public BigDecimal getTpLoadAh() {
		return tpLoadAh;
	}
	public void setTpLoadAh(BigDecimal tpLoadAh) {
		this.tpLoadAh = tpLoadAh;
	}
	public BigDecimal getTpLoadBa() {
		return tpLoadBa;
	}
	public void setTpLoadBa(BigDecimal tpLoadBa) {
		this.tpLoadBa = tpLoadBa;
	}
	public BigDecimal getTpLoadBb() {
		return tpLoadBb;
	}
	public void setTpLoadBb(BigDecimal tpLoadBb) {
		this.tpLoadBb = tpLoadBb;
	}
	public BigDecimal getTpLoadBc() {
		return tpLoadBc;
	}
	public void setTpLoadBc(BigDecimal tpLoadBc) {
		this.tpLoadBc = tpLoadBc;
	}
	public BigDecimal getTpLoadBd() {
		return tpLoadBd;
	}
	public void setTpLoadBd(BigDecimal tpLoadBd) {
		this.tpLoadBd = tpLoadBd;
	}
	public BigDecimal getTpLoadBe() {
		return tpLoadBe;
	}
	public void setTpLoadBe(BigDecimal tpLoadBe) {
		this.tpLoadBe = tpLoadBe;
	}
	public BigDecimal getTpLoadBf() {
		return tpLoadBf;
	}
	public void setTpLoadBf(BigDecimal tpLoadBf) {
		this.tpLoadBf = tpLoadBf;
	}
	public BigDecimal getTpLoadBg() {
		return tpLoadBg;
	}
	public void setTpLoadBg(BigDecimal tpLoadBg) {
		this.tpLoadBg = tpLoadBg;
	}
	public BigDecimal getTpLoadBh() {
		return tpLoadBh;
	}
	public void setTpLoadBh(BigDecimal tpLoadBh) {
		this.tpLoadBh = tpLoadBh;
	}
	public BigDecimal getTpLoadCa() {
		return tpLoadCa;
	}
	public void setTpLoadCa(BigDecimal tpLoadCa) {
		this.tpLoadCa = tpLoadCa;
	}
	public BigDecimal getTpLoadCb() {
		return tpLoadCb;
	}
	public void setTpLoadCb(BigDecimal tpLoadCb) {
		this.tpLoadCb = tpLoadCb;
	}
	public BigDecimal getTpLoadCc() {
		return tpLoadCc;
	}
	public void setTpLoadCc(BigDecimal tpLoadCc) {
		this.tpLoadCc = tpLoadCc;
	}
	public BigDecimal getTpLoadCd() {
		return tpLoadCd;
	}
	public void setTpLoadCd(BigDecimal tpLoadCd) {
		this.tpLoadCd = tpLoadCd;
	}
	public BigDecimal getTpLoadCe() {
		return tpLoadCe;
	}
	public void setTpLoadCe(BigDecimal tpLoadCe) {
		this.tpLoadCe = tpLoadCe;
	}
	public BigDecimal getTpLoadCf() {
		return tpLoadCf;
	}
	public void setTpLoadCf(BigDecimal tpLoadCf) {
		this.tpLoadCf = tpLoadCf;
	}
	public BigDecimal getTpLoadCg() {
		return tpLoadCg;
	}
	public void setTpLoadCg(BigDecimal tpLoadCg) {
		this.tpLoadCg = tpLoadCg;
	}
	public BigDecimal getTpLoadCh() {
		return tpLoadCh;
	}
	public void setTpLoadCh(BigDecimal tpLoadCh) {
		this.tpLoadCh = tpLoadCh;
	}
	public BigDecimal getTpLoadDa() {
		return tpLoadDa;
	}
	public void setTpLoadDa(BigDecimal tpLoadDa) {
		this.tpLoadDa = tpLoadDa;
	}
	public BigDecimal getTpLoadDb() {
		return tpLoadDb;
	}
	public void setTpLoadDb(BigDecimal tpLoadDb) {
		this.tpLoadDb = tpLoadDb;
	}
	public BigDecimal getTpLoadDc() {
		return tpLoadDc;
	}
	public void setTpLoadDc(BigDecimal tpLoadDc) {
		this.tpLoadDc = tpLoadDc;
	}
	public BigDecimal getTpLoadDd() {
		return tpLoadDd;
	}
	public void setTpLoadDd(BigDecimal tpLoadDd) {
		this.tpLoadDd = tpLoadDd;
	}
	public BigDecimal getTpLoadDe() {
		return tpLoadDe;
	}
	public void setTpLoadDe(BigDecimal tpLoadDe) {
		this.tpLoadDe = tpLoadDe;
	}
	public BigDecimal getTpLoadDf() {
		return tpLoadDf;
	}
	public void setTpLoadDf(BigDecimal tpLoadDf) {
		this.tpLoadDf = tpLoadDf;
	}
	public BigDecimal getTpLoadDg() {
		return tpLoadDg;
	}
	public void setTpLoadDg(BigDecimal tpLoadDg) {
		this.tpLoadDg = tpLoadDg;
	}
	public BigDecimal getTpLoadDh() {
		return tpLoadDh;
	}
	public void setTpLoadDh(BigDecimal tpLoadDh) {
		this.tpLoadDh = tpLoadDh;
	}
	public BigDecimal getTpGroupAa() {
		return tpGroupAa;
	}
	public void setTpGroupAa(BigDecimal tpGroupAa) {
		this.tpGroupAa = tpGroupAa;
	}
	public BigDecimal getTpGroupAb() {
		return tpGroupAb;
	}
	public void setTpGroupAb(BigDecimal tpGroupAb) {
		this.tpGroupAb = tpGroupAb;
	}
	public BigDecimal getTpGroupAc() {
		return tpGroupAc;
	}
	public void setTpGroupAc(BigDecimal tpGroupAc) {
		this.tpGroupAc = tpGroupAc;
	}
	public BigDecimal getTpGroupAd() {
		return tpGroupAd;
	}
	public void setTpGroupAd(BigDecimal tpGroupAd) {
		this.tpGroupAd = tpGroupAd;
	}
	public BigDecimal getTpGroupAe() {
		return tpGroupAe;
	}
	public void setTpGroupAe(BigDecimal tpGroupAe) {
		this.tpGroupAe = tpGroupAe;
	}
	public BigDecimal getTpGroupAf() {
		return tpGroupAf;
	}
	public void setTpGroupAf(BigDecimal tpGroupAf) {
		this.tpGroupAf = tpGroupAf;
	}
	public BigDecimal getTpGroupAg() {
		return tpGroupAg;
	}
	public void setTpGroupAg(BigDecimal tpGroupAg) {
		this.tpGroupAg = tpGroupAg;
	}
	public BigDecimal getTpGroupAh() {
		return tpGroupAh;
	}
	public void setTpGroupAh(BigDecimal tpGroupAh) {
		this.tpGroupAh = tpGroupAh;
	}
	public BigDecimal getTpGroupAi() {
		return tpGroupAi;
	}
	public void setTpGroupAi(BigDecimal tpGroupAi) {
		this.tpGroupAi = tpGroupAi;
	}
	public BigDecimal getTpGroupAj() {
		return tpGroupAj;
	}
	public void setTpGroupAj(BigDecimal tpGroupAj) {
		this.tpGroupAj = tpGroupAj;
	}
	public BigDecimal getTpGroupAk() {
		return tpGroupAk;
	}
	public void setTpGroupAk(BigDecimal tpGroupAk) {
		this.tpGroupAk = tpGroupAk;
	}
	public BigDecimal getTpGroupAl() {
		return tpGroupAl;
	}
	public void setTpGroupAl(BigDecimal tpGroupAl) {
		this.tpGroupAl = tpGroupAl;
	}
	public BigDecimal getTpGroupBa() {
		return tpGroupBa;
	}
	public void setTpGroupBa(BigDecimal tpGroupBa) {
		this.tpGroupBa = tpGroupBa;
	}
	public BigDecimal getTpGroupBb() {
		return tpGroupBb;
	}
	public void setTpGroupBb(BigDecimal tpGroupBb) {
		this.tpGroupBb = tpGroupBb;
	}
	public BigDecimal getTpGroupBc() {
		return tpGroupBc;
	}
	public void setTpGroupBc(BigDecimal tpGroupBc) {
		this.tpGroupBc = tpGroupBc;
	}
	public BigDecimal getTpGroupBd() {
		return tpGroupBd;
	}
	public void setTpGroupBd(BigDecimal tpGroupBd) {
		this.tpGroupBd = tpGroupBd;
	}
	public BigDecimal getTpGroupBe() {
		return tpGroupBe;
	}
	public void setTpGroupBe(BigDecimal tpGroupBe) {
		this.tpGroupBe = tpGroupBe;
	}
	public BigDecimal getTpGroupBf() {
		return tpGroupBf;
	}
	public void setTpGroupBf(BigDecimal tpGroupBf) {
		this.tpGroupBf = tpGroupBf;
	}
	public BigDecimal getTpGroupBg() {
		return tpGroupBg;
	}
	public void setTpGroupBg(BigDecimal tpGroupBg) {
		this.tpGroupBg = tpGroupBg;
	}
	public BigDecimal getTpGroupBh() {
		return tpGroupBh;
	}
	public void setTpGroupBh(BigDecimal tpGroupBh) {
		this.tpGroupBh = tpGroupBh;
	}
	public BigDecimal getTpGroupBi() {
		return tpGroupBi;
	}
	public void setTpGroupBi(BigDecimal tpGroupBi) {
		this.tpGroupBi = tpGroupBi;
	}
	public BigDecimal getTpGroupBj() {
		return tpGroupBj;
	}
	public void setTpGroupBj(BigDecimal tpGroupBj) {
		this.tpGroupBj = tpGroupBj;
	}
	public BigDecimal getTpGroupBk() {
		return tpGroupBk;
	}
	public void setTpGroupBk(BigDecimal tpGroupBk) {
		this.tpGroupBk = tpGroupBk;
	}
	public BigDecimal getTpGroupBl() {
		return tpGroupBl;
	}
	public void setTpGroupBl(BigDecimal tpGroupBl) {
		this.tpGroupBl = tpGroupBl;
	}
	public BigDecimal getTpGroupCa() {
		return tpGroupCa;
	}
	public void setTpGroupCa(BigDecimal tpGroupCa) {
		this.tpGroupCa = tpGroupCa;
	}
	public BigDecimal getTpGroupCb() {
		return tpGroupCb;
	}
	public void setTpGroupCb(BigDecimal tpGroupCb) {
		this.tpGroupCb = tpGroupCb;
	}
	public BigDecimal getTpGroupCc() {
		return tpGroupCc;
	}
	public void setTpGroupCc(BigDecimal tpGroupCc) {
		this.tpGroupCc = tpGroupCc;
	}
	public BigDecimal getTpGroupCd() {
		return tpGroupCd;
	}
	public void setTpGroupCd(BigDecimal tpGroupCd) {
		this.tpGroupCd = tpGroupCd;
	}
	public BigDecimal getTpGroupCe() {
		return tpGroupCe;
	}
	public void setTpGroupCe(BigDecimal tpGroupCe) {
		this.tpGroupCe = tpGroupCe;
	}
	public BigDecimal getTpGroupCf() {
		return tpGroupCf;
	}
	public void setTpGroupCf(BigDecimal tpGroupCf) {
		this.tpGroupCf = tpGroupCf;
	}
	public BigDecimal getTpGroupCg() {
		return tpGroupCg;
	}
	public void setTpGroupCg(BigDecimal tpGroupCg) {
		this.tpGroupCg = tpGroupCg;
	}
	public BigDecimal getTpGroupCh() {
		return tpGroupCh;
	}
	public void setTpGroupCh(BigDecimal tpGroupCh) {
		this.tpGroupCh = tpGroupCh;
	}
	public BigDecimal getTpGroupCi() {
		return tpGroupCi;
	}
	public void setTpGroupCi(BigDecimal tpGroupCi) {
		this.tpGroupCi = tpGroupCi;
	}
	public BigDecimal getTpGroupCj() {
		return tpGroupCj;
	}
	public void setTpGroupCj(BigDecimal tpGroupCj) {
		this.tpGroupCj = tpGroupCj;
	}
	public BigDecimal getTpGroupCk() {
		return tpGroupCk;
	}
	public void setTpGroupCk(BigDecimal tpGroupCk) {
		this.tpGroupCk = tpGroupCk;
	}
	public BigDecimal getTpGroupCl() {
		return tpGroupCl;
	}
	public void setTpGroupCl(BigDecimal tpGroupCl) {
		this.tpGroupCl = tpGroupCl;
	}
	public BigDecimal getTpGroupDa() {
		return tpGroupDa;
	}
	public void setTpGroupDa(BigDecimal tpGroupDa) {
		this.tpGroupDa = tpGroupDa;
	}
	public BigDecimal getTpGroupDb() {
		return tpGroupDb;
	}
	public void setTpGroupDb(BigDecimal tpGroupDb) {
		this.tpGroupDb = tpGroupDb;
	}
	public BigDecimal getTpGroupDc() {
		return tpGroupDc;
	}
	public void setTpGroupDc(BigDecimal tpGroupDc) {
		this.tpGroupDc = tpGroupDc;
	}
	public BigDecimal getTpGroupDd() {
		return tpGroupDd;
	}
	public void setTpGroupDd(BigDecimal tpGroupDd) {
		this.tpGroupDd = tpGroupDd;
	}
	public BigDecimal getTpGroupDe() {
		return tpGroupDe;
	}
	public void setTpGroupDe(BigDecimal tpGroupDe) {
		this.tpGroupDe = tpGroupDe;
	}
	public BigDecimal getTpGroupDf() {
		return tpGroupDf;
	}
	public void setTpGroupDf(BigDecimal tpGroupDf) {
		this.tpGroupDf = tpGroupDf;
	}
	public BigDecimal getTpGroupDg() {
		return tpGroupDg;
	}
	public void setTpGroupDg(BigDecimal tpGroupDg) {
		this.tpGroupDg = tpGroupDg;
	}
	public BigDecimal getTpGroupDh() {
		return tpGroupDh;
	}
	public void setTpGroupDh(BigDecimal tpGroupDh) {
		this.tpGroupDh = tpGroupDh;
	}
	public BigDecimal getTpGroupDi() {
		return tpGroupDi;
	}
	public void setTpGroupDi(BigDecimal tpGroupDi) {
		this.tpGroupDi = tpGroupDi;
	}
	public BigDecimal getTpGroupDj() {
		return tpGroupDj;
	}
	public void setTpGroupDj(BigDecimal tpGroupDj) {
		this.tpGroupDj = tpGroupDj;
	}
	public BigDecimal getTpGroupDk() {
		return tpGroupDk;
	}
	public void setTpGroupDk(BigDecimal tpGroupDk) {
		this.tpGroupDk = tpGroupDk;
	}
	public BigDecimal getTpGroupDl() {
		return tpGroupDl;
	}
	public void setTpGroupDl(BigDecimal tpGroupDl) {
		this.tpGroupDl = tpGroupDl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
										


}
