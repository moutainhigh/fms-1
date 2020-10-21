/**
 * Auth:riozenc
 * Date:2019年3月8日 下午3:22:58
 * Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

/**
 * 电表资产传输实体 METER_ASSETS_INFO
 *
 * @author riozenc
 *
 */
public class MeterAssetsEntity extends MeterAssetsDomain {

    private String parent;

    //前台展示
    private String showName1;
    private String showName2;
    private String showName3;
    private String showName4;
    private String showName5;
    private String showName6;
    //相线
    private Byte phaseSeq;
    //功率方向
    private Byte powerDirection;

    private Long meterId;
    
    private double factorNum;

    public void setShowName1(String showName1) {
        this.showName1 = showName1;
    }

    public String getShowName1() {
        return null;
    }

    public void setShowName2(String showName2) {
        this.showName2 = showName2;
    }

    public String getShowName2() {
        return this.getMeterAssetsNo()+"(资产编号)";
    }

    public void setShowName3(String showName3) {
        this.showName3 = showName3;
    }

    public String getShowName3() {
        return this.getMadeNo()+"(出厂编号)";
    }

    public void setShowName4(String showName4) {
        this.showName4 = showName4;
    }

    public String getShowName4() {
        return this.showName4;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getShowName5() {
        return showName5;
    }

    public void setShowName5(String showName5) {
        this.showName5 = showName5;
    }

    public String getShowName6() {
        return showName6;
    }

    public void setShowName6(String showName6) {
        this.showName6 = showName6;
    }

	public double getFactorNum() {
		return factorNum;
	}

	public void setFactorNum(double factorNum) {
		this.factorNum = factorNum;
	}
}
