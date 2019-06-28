package com.example.wxgzh.common.dto;

import java.util.List;

public class QueryResult<Row> {
	
	private int totalRows;
	private int pageSize;
	private int pageNo;
	private int totalPages = -1;
	private List<Row> rows;

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		if (totalPages == -1) {
			/* 计算总页数 */
			int tp = totalRows / pageSize;

			if (totalRows % pageSize != 0) {
				tp++;
			}
			totalPages = tp;
		}
		return totalPages;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

}
