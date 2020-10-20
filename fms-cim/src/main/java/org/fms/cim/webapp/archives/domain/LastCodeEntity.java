package org.fms.cim.webapp.archives.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//换表 上月表码实体
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastCodeEntity {
    private Long tsFlag;
    private String type;
    private BigDecimal oldP1;
    private BigDecimal oldP2;
    private BigDecimal oldP3;
    private BigDecimal oldP4;

    public Long getTsFlag() {
        return tsFlag;
    }

    public void setTsFlag(Long tsFlag) {
        this.tsFlag = tsFlag;
    }

    public BigDecimal getOldP1() {
        return oldP1;
    }

    public void setOldP1(BigDecimal oldP1) {
        this.oldP1 = oldP1;
    }

    public BigDecimal getOldP2() {
        return oldP2;
    }

    public void setOldP2(BigDecimal oldP2) {
        this.oldP2 = oldP2;
    }

    public BigDecimal getOldP3() {
        return oldP3;
    }

    public void setOldP3(BigDecimal oldP3) {
        this.oldP3 = oldP3;
    }

    public BigDecimal getOldP4() {
        return oldP4;
    }

    public void setOldP4(BigDecimal oldP4) {
        this.oldP4 = oldP4;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
