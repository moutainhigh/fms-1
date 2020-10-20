package org.fms.cim.webapp.archives.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riozenc.titanTool.mybatis.MybatisEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTransformerRelaDomain extends ManagerParamEntity implements MybatisEntity {

	private Long userId;		//用户id
	private Long TransformerId;	//变压器id
	private Date createDate;	//创建时间
	private String remark; 		//备注
	private Byte status; 		//状态
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTransformerId() {
		return TransformerId;
	}
	public void setTransformerId(Long transformerId) {
		TransformerId = transformerId;
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
