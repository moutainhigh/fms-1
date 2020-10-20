/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:22:58
 *    Title:com.riozenc.cim.webapp.domain.CustomerDomain.java
 **/
package org.fms.cim.webapp.assets.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.cim.webapp.archives.domain.ManagerParamEntity;
import com.riozenc.titanTool.annotation.TablePrimaryKey;
import com.riozenc.titanTool.mybatis.MybatisEntity;

import java.util.Date;

/**
 * 互感器资产传输实体 INDUCTOR_ASSETS_INFO
 *
 * @author riozenc
 *
 */
public class InductorAssetsEntity extends  InductorAssetsDomain{
	private String parent;
	//前台展示
	private String showName1;
	private String showName2;
	private String showName3;
	private String showName4;
	private Byte phaseSeq;
	private Long	ctAssetsId;		//	CT资产ID	CT_ASSETS_ID	bigint
	private Long	ptAssetsId;
    private String ctAssetsNo; // ct资产号
    private String ptAssetsNo;
    private Long meterId;


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
		return this.getInductorAssetsNo()+"(资产编号)";
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

    public Long getCtAssetsId() {
        return ctAssetsId;
    }

    public void setCtAssetsId(Long ctAssetsId) {
        this.ctAssetsId = ctAssetsId;
    }

    public Long getPtAssetsId() {
        return ptAssetsId;
    }

    public void setPtAssetsId(Long ptAssetsId) {
        this.ptAssetsId = ptAssetsId;
    }

    public String getCtAssetsNo() {
        return ctAssetsNo;
    }

    public void setCtAssetsNo(String ctAssetsNo) {
        this.ctAssetsNo = ctAssetsNo;
    }

    public String getPtAssetsNo() {
        return ptAssetsNo;
    }

    public void setPtAssetsNo(String ptAssetsNo) {
        this.ptAssetsNo = ptAssetsNo;
    }

	public Long getMeterId() {
		return meterId;
	}

	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}
}
