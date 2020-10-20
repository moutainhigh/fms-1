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
 *  互感器资产检定结果记录表 INDUCTOR_ASSETS_TEST_RECORD
 *
 * @author yhx
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InductorAssetsTestRecordDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private String reportNo;			//	检定报告编号	REPORT_NO	varchar(32)	32	
	private Long inductorAssetsID1;		//	资产ID3	INDUCTOR_ASSETS_ID3	bigint
	private Long inductorAssetsID2;		//	资产ID2	INDUCTOR_ASSETS_ID2	bigint
	private Long inductorAssetsID3;		//	资产ID1	INDUCTOR_ASSETS_ID1	bigint	
	private String inspectionDept;		//	送检单位	INSPECTION_DEPT	varchar(32)	32	
	private String deviceName;			//计量器具名称
	private String specs;				//规格
	private String madeNo;				//出厂编号
	private String madeName;			//制造单位
	private Long ratifier;				//	批准人	RATIFIER	bigint		
	private Long verifier;				//	检定员	VERIFIER	bigint		
	private Long checker;				//	核验员	CHECKER	bigint		
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date testDate; 				//	检定日期	TEST_TIME	datetime	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date validity; 				//	有效期	VALIDITY	datetime			
	private BigDecimal temperature;		//	温度	TEMPERATURE	decimal(6.2)
	private BigDecimal humidity;		//	湿度	HUMIDITY	decimal(6.2)	
	private BigDecimal primaryCurrent;
	private BigDecimal secondaryCurrent;
	private BigDecimal primaryVolt;
	private BigDecimal secondaryVolt;
	private BigDecimal cos;				//	额定功率因数	COS	decimal(6.2)	
	private Byte polarity;				//	极性	POLARITY	smallint		
	private Byte insulation;			//	绝缘电阻试验结果	INSULATION	smallint	
	private Byte pfvwt;	 				//	工频耐压试验结果	PFVWT	smallint		
	private Byte verificationConclusion;//	检定结论	VERIFICATION_CONCLUSION	smallint	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createDate;			//	创建日期	CREATE_DATE	datetime
	private String remark;				//	备注	REMARK	varchar(256)
	private Byte status;
	//关联字段
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public Long getInductorAssetsID1() {
		return inductorAssetsID1;
	}
	public void setInductorAssetsID1(Long inductorAssetsID1) {
		this.inductorAssetsID1 = inductorAssetsID1;
	}
	public Long getInductorAssetsID2() {
		return inductorAssetsID2;
	}
	public void setInductorAssetsID2(Long inductorAssetsID2) {
		this.inductorAssetsID2 = inductorAssetsID2;
	}
	public Long getInductorAssetsID3() {
		return inductorAssetsID3;
	}
	public void setInductorAssetsID3(Long inductorAssetsID3) {
		this.inductorAssetsID3 = inductorAssetsID3;
	}
	public String getInspectionDept() {
		return inspectionDept;
	}
	public void setInspectionDept(String inspectionDept) {
		this.inspectionDept = inspectionDept;
	}
	public Long getRatifier() {
		return ratifier;
	}
	public void setRatifier(Long ratifier) {
		this.ratifier = ratifier;
	}
	public Long getVerifier() {
		return verifier;
	}
	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}
	public Long getChecker() {
		return checker;
	}
	public void setChecker(Long checker) {
		this.checker = checker;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public Date getValidity() {
		return validity;
	}
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	public BigDecimal getTemperature() {
		return temperature;
	}
	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}
	public BigDecimal getHumidity() {
		return humidity;
	}
	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}
	public BigDecimal getCos() {
		return cos;
	}
	public void setCos(BigDecimal cos) {
		this.cos = cos;
	}
	public Byte getPolarity() {
		return polarity;
	}
	public void setPolarity(Byte polarity) {
		this.polarity = polarity;
	}
	public Byte getInsulation() {
		return insulation;
	}
	public void setInsulation(Byte insulation) {
		this.insulation = insulation;
	}
	public Byte getPfvwt() {
		return pfvwt;
	}
	public void setPfvwt(Byte pfvwt) {
		this.pfvwt = pfvwt;
	}
	public Byte getVerificationConclusion() {
		return verificationConclusion;
	}
	public void setVerificationConclusion(Byte verificationConclusion) {
		this.verificationConclusion = verificationConclusion;
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getMadeName() {
		return madeName;
	}
	public void setMadeName(String madeName) {
		this.madeName = madeName;
	}
	public BigDecimal getPrimaryCurrent() {
		return primaryCurrent;
	}
	public void setPrimaryCurrent(BigDecimal primaryCurrent) {
		this.primaryCurrent = primaryCurrent;
	}
	public BigDecimal getSecondaryCurrent() {
		return secondaryCurrent;
	}
	public void setSecondaryCurrent(BigDecimal secondaryCurrent) {
		this.secondaryCurrent = secondaryCurrent;
	}
	public BigDecimal getPrimaryVolt() {
		return primaryVolt;
	}
	public void setPrimaryVolt(BigDecimal primaryVolt) {
		this.primaryVolt = primaryVolt;
	}
	public BigDecimal getSecondaryVolt() {
		return secondaryVolt;
	}
	public void setSecondaryVolt(BigDecimal secondaryVolt) {
		this.secondaryVolt = secondaryVolt;
	}
	public String getMadeNo() {
		return madeNo;
	}
	public void setMadeNo(String madeNo) {
		this.madeNo = madeNo;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
											


}
