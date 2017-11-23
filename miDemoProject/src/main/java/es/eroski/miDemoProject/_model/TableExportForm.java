package es.eroski.miDemoProject._model;

import java.util.ArrayList;
import java.util.List;

public class TableExportForm {

	private String orderColumn;
	private String ascDsc;
	private List<TableColumn> columns=new ArrayList<TableColumn>();
	private List<String> titleColumns=new ArrayList<String>();

	public TableExportForm() {
		super();
	}

	public TableExportForm(String orderColumn, String ascDsc, List<TableColumn> columns, List<String> titleColumns) {
		super();
		this.orderColumn = orderColumn;
		this.ascDsc = ascDsc;
		this.columns = columns;
		this.setTitleColumns(titleColumns);
	}


	public List<TableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<TableColumn> columns) {
		this.columns = columns;
	}

	public List<String> getTitleColumns() {
		return titleColumns;
	}

	public void setTitleColumns(List<String> titleColumns) {
		this.titleColumns = titleColumns;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getAscDsc() {
		return ascDsc;
	}

	public void setAscDsc(String ascDsc) {
		this.ascDsc = ascDsc;
	}
	
}
