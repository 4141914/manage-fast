package com.mf.common.domain.coursetype;

import java.io.Serializable;
import java.util.Date;


/**
 * 课种
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-12-11 14:44:08
 */
public class CourseType implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键
	private Long id;
	//名称
	private String name;
	//门店ID
	private Long branchId;
	//门店名称
	private String branchName;
	//健身项目KEY
	private Integer keepPro;
	//健身项目名称
	private String keepProName;
	//课程类型KEY
	private Integer courseCategory;
	//课程类型名称
	private String courseCategoryName;
	//计费方式
	private Byte chargeMode;
	//有效期(天)
	private Integer days;
	//价钱
	private Float price;
	//课程节数
	private Integer count;
	//课程时长
	private Integer timeLength;
	//是否在售
	private Byte onSale;
	//图片地址
	private String imgPath;
	//描述
	private String courseTypeDescribe;
	//课种颜色
	private String color;
	//
	private Byte isCommon;

	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：门店ID
	 */
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	/**
	 * 获取：门店ID
	 */
	public Long getBranchId() {
		return branchId;
	}
	/**
	 * 设置：门店名称
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * 获取：门店名称
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * 设置：健身项目KEY
	 */
	public void setKeepPro(Integer keepPro) {
		this.keepPro = keepPro;
	}
	/**
	 * 获取：健身项目KEY
	 */
	public Integer getKeepPro() {
		return keepPro;
	}
	/**
	 * 设置：健身项目名称
	 */
	public void setKeepProName(String keepProName) {
		this.keepProName = keepProName;
	}
	/**
	 * 获取：健身项目名称
	 */
	public String getKeepProName() {
		return keepProName;
	}
	/**
	 * 设置：课程类型KEY
	 */
	public void setCourseCategory(Integer courseCategory) {
		this.courseCategory = courseCategory;
	}
	/**
	 * 获取：课程类型KEY
	 */
	public Integer getCourseCategory() {
		return courseCategory;
	}
	/**
	 * 设置：课程类型名称
	 */
	public void setCourseCategoryName(String courseCategoryName) {
		this.courseCategoryName = courseCategoryName;
	}
	/**
	 * 获取：课程类型名称
	 */
	public String getCourseCategoryName() {
		return courseCategoryName;
	}
	/**
	 * 设置：计费方式
	 */
	public void setChargeMode(Byte chargeMode) {
		this.chargeMode = chargeMode;
	}
	/**
	 * 获取：计费方式
	 */
	public Byte getChargeMode() {
		return chargeMode;
	}
	/**
	 * 设置：有效期(天)
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	/**
	 * 获取：有效期(天)
	 */
	public Integer getDays() {
		return days;
	}
	/**
	 * 设置：价钱
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	/**
	 * 获取：价钱
	 */
	public Float getPrice() {
		return price;
	}
	/**
	 * 设置：课程节数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：课程节数
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：课程时长
	 */
	public void setTimeLength(Integer timeLength) {
		this.timeLength = timeLength;
	}
	/**
	 * 获取：课程时长
	 */
	public Integer getTimeLength() {
		return timeLength;
	}
	/**
	 * 设置：是否在售
	 */
	public void setOnSale(Byte onSale) {
		this.onSale = onSale;
	}
	/**
	 * 获取：是否在售
	 */
	public Byte getOnSale() {
		return onSale;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImgPath() {
		return imgPath;
	}
	/**
	 * 设置：描述
	 */
	public void setCourseTypeDescribe(String courseTypeDescribe) {
		this.courseTypeDescribe = courseTypeDescribe;
	}
	/**
	 * 获取：描述
	 */
	public String getCourseTypeDescribe() {
		return courseTypeDescribe;
	}
	/**
	 * 设置：课种颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 获取：课种颜色
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置：
	 */
	public void setIsCommon(Byte isCommon) {
		this.isCommon = isCommon;
	}
	/**
	 * 获取：
	 */
	public Byte getIsCommon() {
		return isCommon;
	}
}
