package com.zz.form;

public class PictureForm {
	private String id;
	/**
	 * 图片标题
	 */
	private String realName;
	/**
	 * 图片路劲
	 */
	private String path;
	/**
	 * 修改时间
	 */
	private String uploadTime;
	/**
	 * 图片描述
	 */
	private String description;
	
	private String productId;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
