/**
 * Author : czy
 * Date : 2019年7月8日 下午4:10:35
 * Title : com.riozenc.billing.webapp.domain.MeterDomain.java
 *
**/
package org.fms.cim.webapp.archives.domain;

import java.util.List;

import org.fms.cim.webapp.assets.domain.InductorAssetsEntity;
import org.fms.cim.webapp.assets.domain.MeterAssetsEntity;

import com.riozenc.titanTool.mybatis.MybatisEntity;

public class MeterInformationEntity extends ManagerParamEntity implements MybatisEntity {
		private Long id;// ID ID bigint TRUE FALSE TRUE
	private String meterNo; // 计量点号
    private Long userId;
    private Long transformerId;
	private String meterName; // 计量点名称
	private String setAddress; // 安装地点
	private Long priceType; // 电价
	private Integer needIndex; // 需量定值
	private Byte voltLevelType; // 计量点电压
	private Byte meterType; // 计量点类别
	private Integer elecTypeCode; // 用电类别
	private String userNo;
	private String userName;
    private String constomerNo;
    private String parent;
    private Byte tsFlag;
    //互感器
    private List<InductorAssetsEntity> inductorAssetsEntities;
    //电能表
    private List<MeterAssetsEntity> meterAssetsEntities;

    //前台展示
    private String showName1;
    private String showName2;
    private String showName3;
    private String showName4;


    public String getShowName1() {
        return userNo;
    }

    public String getShowName2() {
        return userName;
    }

    public String getShowName3() {
        return meterNo;
    }

    public String getShowName4() {
        return meterName;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}

	public String getSetAddress() {
		return setAddress;
	}

	public void setSetAddress(String setAddress) {
		this.setAddress = setAddress;
	}

	public Long getPriceType() {
		return priceType;
	}

	public void setPriceType(Long priceType) {
		this.priceType = priceType;
	}

	public Integer getNeedIndex() {
		return needIndex;
	}

	public void setNeedIndex(Integer needIndex) {
		this.needIndex = needIndex;
	}

	public Byte getVoltLevelType() {
		return voltLevelType;
	}

	public void setVoltLevelType(Byte voltLevelType) {
		this.voltLevelType = voltLevelType;
	}

	public Byte getMeterType() {
		return meterType;
	}

	public void setMeterType(Byte meterType) {
		this.meterType = meterType;
	}

	public Integer getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(Integer elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

    public List<InductorAssetsEntity> getInductorAssetsEntities() {
        return inductorAssetsEntities;
    }

    public void setInductorAssetsEntities(List<InductorAssetsEntity> inductorAssetsEntities) {
        this.inductorAssetsEntities = inductorAssetsEntities;
    }

    public List<MeterAssetsEntity> getMeterAssetsEntities() {
        return meterAssetsEntities;
    }

    public void setMeterAssetsEntities(List<MeterAssetsEntity> meterAssetsEntities) {
        this.meterAssetsEntities = meterAssetsEntities;
    }

    public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(Long transformerId) {
        this.transformerId = transformerId;
    }

    public String getConstomerNo() {
        return constomerNo;
    }

    public void setConstomerNo(String constomerNo) {
        this.constomerNo = constomerNo;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

	public Byte getTsFlag() {
		return tsFlag;
	}

	public void setTsFlag(Byte tsFlag) {
		this.tsFlag = tsFlag;
	}
}
