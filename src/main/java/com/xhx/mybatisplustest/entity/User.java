package com.xhx.mybatisplustest.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 
 * @date 2019年6月12日
 * @author xhx
 */
@SuppressWarnings("serial")
@TableName(value = "MUSER")
public class User extends SuperEntity<User> {

	@TableId(value = "test_id", type = IdType.INPUT)
	private Long id;
	private String name;
	private Integer age;
	private Long tenantId;

	// @TableField("test_type")
	// @TableLogic
	private Long testType;
	/**
	 * 测试填充
	 */
	@TableField(value = "test_date", fill = FieldFill.INSERT)
	private Date testDate;

	private Long role;
	private String phone;
	private Long testId;

	public User() {
	}

	public User(Long id, String name, Integer age, Long testType) {
		this.setId(id);
		this.name = name;
		this.age = age;
		this.testType = testType;
	}

	public User(String name, Integer age, Long testType) {
		this.name = name;
		this.age = age;
		this.testType = testType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getTestType() {
		return testType;
	}

	public void setTestType(Long testType) {
		this.testType = testType;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User [id=" + this.getId() + ",name=" + name + ", age=" + age + ", testType=" + testType + ", testDate="
				+ testDate + ", role=" + role + ", phone=" + phone + "]";
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

}
