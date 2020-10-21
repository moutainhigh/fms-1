/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.fms.cim.webapp.archives.domain.ManagerParamEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

/**
 *  电能表资产检定记录表   METER_ASSETS_TEST_RECORD 
 *
 * @author yhx
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterAssetsTestRecordDomain extends ManagerParamEntity implements MybatisEntity {

	@TablePrimaryKey
	private Long id;// ID ID bigint TRUE FALSE TRUE
	private Long meterAssetsId;
	private String reportNo;
	private String inspectionDept;		//	送检单位	INSPECTION_DEPT	varchar(32)	32	
	private String deviceName;			//计量器具名称
	private String specs;				//规格
	private String madeNo;				//出厂编号
	private String madeName;			//制造单位
	private Byte testBasis;				//	检测依据	TEST_BASIS	smallint	
	private Long ratifier;				//	批准人	RATIFIER	bigint		
	private Long verifier;				//	检定员	VERIFIER	bigint		
	private Long checker;				//	核验员	CHECKER	bigint		
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date testDate; 				//	检定日期	TEST_TIME	datetime	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date validity; 				//	有效期	VALIDITY	datetime			
	private BigDecimal temperature;		//	温度	TEMPERATURE	decimal(6.2)
	private BigDecimal humidity;		//	湿度	HUMIDITY	decimal(6.2)	
	private String 	detectionDeviceName;					//	检定装置名称	DETECTION_DEVICE_NAME	varchar(32)	32	
	private String	detectionDeviceModel;					//	检定装置型号	DETECTION_DEVICE_MODEL	varchar(32)	32	
	private String	detectionDeviceAccLevel;				//	检定装置准确度等级	DETECTION_DEVICE_ACC_LEVEL	varchar(32)	32
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date detectionDeviceValidity; 							//	检定装置有效期	DETECTION_DEVICE_VALIDITY	date			
	private BigDecimal calibrationConstant;						//	检定常数	CALIBRATION_CONSTANT	decimal(10.2)	10	2	
	private Byte startingTest;							//	起动	STARTING_TEST	smallint		
	private Byte creepingTest;							//	潜动	CREEPING_TEST	smallint
	private Byte pfvwt;							//	工频耐压	PFWV	smallint		
	private BigDecimal p1r0s;								//	正向有功总起	P1R0S	decimal(10.2)	
	private BigDecimal p1r0e;							//	正向有功总止	P1R0E	decimal(10.2)	
	private BigDecimal p1r1s;						//	正向有功尖起	P1R1S	decimal(10.2)	
	private BigDecimal p1r1e;						//	正向有功尖止	P1R1E	decimal(10.2)	
	private BigDecimal p1r2s;						//	正向有功峰起	P1R2S	decimal(10.2)	
	private BigDecimal p1r2e;						//	正向有功峰止	P1R2E	decimal(10.2)	
	private BigDecimal p1r3s;						//	正向有功平起	P1R3S	decimal(10.2)	
	private BigDecimal p1r3e;						//	正向有功平止	P1R3E	decimal(10.2)	
	private BigDecimal p1r4s;						//	正向有功谷起	P1R4S	decimal(10.2)	
	private BigDecimal p1r4e;						//	正向有功谷止	P1R4E	decimal(10.2)
	private BigDecimal p3r0s;						//	正向无功总起	P3R0S	decimal(10.2)	
	private BigDecimal p3r0e;						//	正向无功总止	P3R0E	decimal(10.2)	
	private BigDecimal checkConstant;						//	校核常数	CHECK_CONSTANT	decimal(10.2)	
	private BigDecimal maxStandardDev;				//	最大标准差	MAX_STANDARD_DEV	decimal(6.2)
	private Byte verificationConclusion;//	检定结论	VERIFICATION_CONCLUSION	smallint	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createDate;			//	创建日期	CREATE_DATE	datetime
	private String remark;				//	备注	REMARK	varchar(256)
	private Byte status;
	
	
	
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

	public Long getMeterAssetsId() {
		return meterAssetsId;
	}
	public void setMeterAssetsId(Long meterAssetsId) {
		this.meterAssetsId = meterAssetsId;
	}
	public String getInspectionDept() {
		return inspectionDept;
	}
	public void setInspectionDept(String inspectionDept) {
		this.inspectionDept = inspectionDept;
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
	public String getMadeNo() {
		return madeNo;
	}
	public void setMadeNo(String madeNo) {
		this.madeNo = madeNo;
	}
	public String getMadeName() {
		return madeName;
	}
	public void setMadeName(String madeName) {
		this.madeName = madeName;
	}
	public Byte getTestBasis() {
		return testBasis;
	}
	public void setTestBasis(Byte testBasis) {
		this.testBasis = testBasis;
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
	public String getDetectionDeviceName() {
		return detectionDeviceName;
	}
	public void setDetectionDeviceName(String detectionDeviceName) {
		this.detectionDeviceName = detectionDeviceName;
	}
	public String getDetectionDeviceModel() {
		return detectionDeviceModel;
	}
	public void setDetectionDeviceModel(String detectionDeviceModel) {
		this.detectionDeviceModel = detectionDeviceModel;
	}
	public String getDetectionDeviceAccLevel() {
		return detectionDeviceAccLevel;
	}
	public void setDetectionDeviceAccLevel(String detectionDeviceAccLevel) {
		this.detectionDeviceAccLevel = detectionDeviceAccLevel;
	}
	public Date getDetectionDeviceValidity() {
		return detectionDeviceValidity;
	}
	public void setDetectionDeviceValidity(Date detectionDeviceValidity) {
		this.detectionDeviceValidity = detectionDeviceValidity;
	}
	public BigDecimal getCalibrationConstant() {
		return calibrationConstant;
	}
	public void setCalibrationConstant(BigDecimal calibrationConstant) {
		this.calibrationConstant = calibrationConstant;
	}
	public Byte getStartingTest() {
		return startingTest;
	}
	public void setStartingTest(Byte startingTest) {
		this.startingTest = startingTest;
	}
	public Byte getCreepingTest() {
		return creepingTest;
	}
	public void setCreepingTest(Byte creepingTest) {
		this.creepingTest = creepingTest;
	}
	public Byte getPfvwt() {
		return pfvwt;
	}
	public void setPfvwt(Byte pfvwt) {
		this.pfvwt = pfvwt;
	}
	public BigDecimal getP1r0s() {
		return p1r0s;
	}
	public void setP1r0s(BigDecimal p1r0s) {
		this.p1r0s = p1r0s;
	}
	public BigDecimal getP1r0e() {
		return p1r0e;
	}
	public void setP1r0e(BigDecimal p1r0e) {
		this.p1r0e = p1r0e;
	}
	public BigDecimal getP1r1s() {
		return p1r1s;
	}
	public void setP1r1s(BigDecimal p1r1s) {
		this.p1r1s = p1r1s;
	}
	public BigDecimal getP1r1e() {
		return p1r1e;
	}
	public void setP1r1e(BigDecimal p1r1e) {
		this.p1r1e = p1r1e;
	}
	public BigDecimal getP1r2s() {
		return p1r2s;
	}
	public void setP1r2s(BigDecimal p1r2s) {
		this.p1r2s = p1r2s;
	}
	public BigDecimal getP1r2e() {
		return p1r2e;
	}
	public void setP1r2e(BigDecimal p1r2e) {
		this.p1r2e = p1r2e;
	}
	public BigDecimal getP1r3s() {
		return p1r3s;
	}
	public void setP1r3s(BigDecimal p1r3s) {
		this.p1r3s = p1r3s;
	}
	public BigDecimal getP1r3e() {
		return p1r3e;
	}
	public void setP1r3e(BigDecimal p1r3e) {
		this.p1r3e = p1r3e;
	}
	public BigDecimal getP1r4s() {
		return p1r4s;
	}
	public void setP1r4s(BigDecimal p1r4s) {
		this.p1r4s = p1r4s;
	}
	public BigDecimal getP1r4e() {
		return p1r4e;
	}
	public void setP1r4e(BigDecimal p1r4e) {
		this.p1r4e = p1r4e;
	}
	public BigDecimal getP3r0s() {
		return p3r0s;
	}
	public void setP3r0s(BigDecimal p3r0s) {
		this.p3r0s = p3r0s;
	}
	public BigDecimal getP3r0e() {
		return p3r0e;
	}
	public void setP3r0e(BigDecimal p3r0e) {
		this.p3r0e = p3r0e;
	}
	public BigDecimal getCheckConstant() {
		return checkConstant;
	}
	public void setCheckConstant(BigDecimal checkConstant) {
		this.checkConstant = checkConstant;
	}
	public BigDecimal getMaxStandardDev() {
		return maxStandardDev;
	}
	public void setMaxStandardDev(BigDecimal maxStandardDev) {
		this.maxStandardDev = maxStandardDev;
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
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
										


}
