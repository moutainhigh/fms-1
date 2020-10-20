package org.fms.cim.webapp.archives.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 计量点与表资产中间表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterMeterAssetsRelDomain extends ManagerParamEntity implements MybatisEntity {
    //id
    @TablePrimaryKey
    private Long id;
    //计量点id
    private Long meterId;
    //资产id
    private Long meterAssetsId;
    //相线
    private Byte phaseSeq;
    //功能代码
    private Long functionCode;
    //功率方向
    private Byte powerDirection;
    //表序号
    private Byte meterOrder;
    //分时标识
    private Byte tsFlag;
    //创建时间
    private Date createDate;
    //倍率
    private Double factorNum;

    //倍率
    private BigDecimal multiplyingPower;
    //状态
    private Byte status;
    //抄表序号
    private Long writeSn;
    private Integer meterSn;

    private String meterAssetsNo;

    @Transient
    private List<Long> meterAssetsIds;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Long getMeterAssetsId() {
        return meterAssetsId;
    }

    public void setMeterAssetsId(Long meterAssetsId) {
        this.meterAssetsId = meterAssetsId;
    }



    public Long getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(Long functionCode) {
        this.functionCode = functionCode;
    }



    public Byte getMeterOrder() {
        return meterOrder;
    }

    public void setMeterOrder(Byte meterOrder) {
        this.meterOrder = meterOrder;
    }

    public Byte getPhaseSeq() {
        return phaseSeq;
    }

    public void setPhaseSeq(Byte phaseSeq) {
        this.phaseSeq = phaseSeq;
    }

    public Byte getPowerDirection() {
        return powerDirection;
    }

    public void setPowerDirection(Byte powerDirection) {
        this.powerDirection = powerDirection;
    }

    public Byte getTsFlag() {
        return tsFlag;
    }

    public void setTsFlag(Byte tsFlag) {
        this.tsFlag = tsFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getMultiplyingPower() {
        return multiplyingPower;
    }

    public void setMultiplyingPower(BigDecimal multiplyingPower) {
        this.multiplyingPower = multiplyingPower;
    }

    public Long getWriteSn() {
        return writeSn;
    }

    public void setWriteSn(Long writeSn) {
        this.writeSn = writeSn;
    }

    public Double getFactorNum() {
        return factorNum;
    }

    public void setFactorNum(Double factorNum) {
        this.factorNum = factorNum;
    }

	public String getMeterAssetsNo() {
		return meterAssetsNo;
	}

	public void setMeterAssetsNo(String meterAssetsNo) {
		this.meterAssetsNo = meterAssetsNo;
	}

	public Integer getMeterSn() {
		return meterSn;
	}

	public void setMeterSn(Integer meterSn) {
		this.meterSn = meterSn;
	}

    public List<Long> getMeterAssetsIds() {
        return meterAssetsIds;
    }

    public void setMeterAssetsIds(List<Long> meterAssetsIds) {
        this.meterAssetsIds = meterAssetsIds;
    }
}
