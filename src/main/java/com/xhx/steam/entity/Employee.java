package com.xhx.steam.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2639961158248278998L;
	private String id;
	private String name;
	private Integer age;
	private Double amount;
	//private transient Boolean isDr=false; transient可以阻止字段序列化
	private Boolean isDr=false;

}
