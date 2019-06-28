package com.example.wxgzh.manager.ao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManagerAO {
	@NotBlank
	private String commodityName;
	private String price;
	private String agio;
}
