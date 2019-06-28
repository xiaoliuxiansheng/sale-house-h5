package com.example.wxgzh.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
/**
 * 
 * @author chenfangxu
 *	商品信息
 */
@Data
public class CommodityEntity {
	private int commodityId;//商品编号
	private String commodityName;//商品名称
	private BigDecimal price;//商品实际价格
	private int agio;//折扣
	private boolean sign;
	private Date ctCreate;
	private Date ctModified;
}