package com.cgq.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * t_operate_log
 * 
 * @Date 2018-11-28
 */

public class OperateLogDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 操作人
	 */
	private String userId;

	/**
	 * ip
	 */
	private String ip;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethodPath() {
		return methodPath;
	}

	public void setMethodPath(String methodPath) {
		this.methodPath = methodPath;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getReqParams() {
		return reqParams;
	}

	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}

	public String getMethodParams() {
		return methodParams;
	}

	public void setMethodParams(String methodParams) {
		this.methodParams = methodParams;
	}

	public Date getOpDate() {
		return opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 方法全路径
	 */
	private String methodPath;

	/**
	 * 操作
	 */
	private String operate;

	/**
	 * 请求参数
	 */
	private String reqParams;

	/**
	 * 方法入参
	 */
	private String methodParams;

	/**
	 * 操作时间
	 */
	private Date opDate;

	/**
	 * 描述
	 */
	private String description;

	public OperateLogDto() {
	}

	public OperateLogDto(String userId, String ip, String methodPath, String operate, String reqParams,
                         String methodParams, String description) {
		this.userId = userId;
		this.ip = ip;
		this.methodPath = methodPath;
		this.operate = operate;
		this.reqParams = reqParams;
		this.methodParams = methodParams;
		this.description = description;
	}

}