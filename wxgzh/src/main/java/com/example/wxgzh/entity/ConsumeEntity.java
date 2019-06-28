package com.example.wxgzh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 * 
 * @author chenfangxu
 *	消费信息
 */
@SuppressWarnings("serial")
@Data
@JsonIgnoreProperties(value = {"handler"})
public class ConsumeEntity implements Serializable {
	private String consumeId;//消费编号
	private ConsumerEntity consumer;//vip用户
	private CommodityEntity commodity;//商品
	private BigDecimal practicePrice;//商品实际价格
	private BigDecimal count;//商品总价
	private int number;//商品数量
	private Date time;//消费时间
	private Date ctModified;//修改时间
}
