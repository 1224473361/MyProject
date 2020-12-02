package com.xhx.concurrent.threadpool.diy.entity;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 车 实体
 * 
 * @date 2019年8月30日
 * @author xhx
 */
@Data
@ToString
public class MyCar {

	private Integer id;

	private String name;

	private Integer type;

	private Double mony;

	private Date createTime;

	private String numb;

}
