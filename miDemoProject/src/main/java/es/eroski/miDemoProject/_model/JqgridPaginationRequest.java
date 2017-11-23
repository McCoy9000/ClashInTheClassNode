package es.eroski.miDemoProject._model;

import java.io.Serializable;

public class JqgridPaginationRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long rows;
	private Long page;
	private String ascDsc;
	private String sort;
	
	public JqgridPaginationRequest(Long rows, Long page, String ascDsc, String sort){
		this.rows = rows;
		this.page = page;
		this.ascDsc = ascDsc;
		this.sort = sort;
	}
	public JqgridPaginationRequest(Long rows, Long page){
		this.rows = rows;
		this.page = page;
	}
	
	public JqgridPaginationRequest(){}
	
	public Long getRows() {
		return this.rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	public Long getPage() {
		return this.page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public String getAscDsc() {
		return this.ascDsc;
	}
	public void setAscDsc(String ascDsc) {
		this.ascDsc = ascDsc;
	}
	public String getSort() {
		return this.sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}	
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object {");
		result.append(" [ rows: ").append(this.rows).append(" ]");
		result.append(" [ page: ").append(this.page).append(" ]");
		result.append(" [ ascDsc: ").append(this.ascDsc).append(" ]");
		result.append(" [ sort: ").append(this.sort).append(" ]");
		result.append("}");
		return result.toString();
	}
}