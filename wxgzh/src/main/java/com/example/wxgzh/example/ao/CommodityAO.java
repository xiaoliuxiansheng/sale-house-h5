package com.example.wxgzh.example.ao;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommodityAO {
	@NotBlank
	private String commodityName;
	private String price;
	private String agio;
}
