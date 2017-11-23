package es.eroski.miDemoProject._model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class DataTableRequest {

private static final long serialVersionUID = 1L;
	
	private Long draw;
	private Long length;
	private Long start;
	private String orderColumn;
	private String ascDsc;
	private String searchValue;
	private List<TableColumn> lstColumns;
	
	/*Se utiliza para obtener los datos necesarios para establecer las para montar las consultas en base a los 
	 * parametros de la request.
	 */
	public DataTableRequest(Long draw, Long length, Long start, HttpServletRequest request) {
		super();
		this.draw = draw;
		this.length = length;
		this.start = start;
		this.orderColumn=request.getParameter("columns["+request.getParameter("order[0][column]")+"][name]");
		this.ascDsc=request.getParameter("order[0][dir]");
		this.lstColumns=getAllColumnsDataFromRequest(request);
		this.searchValue= request.getParameter("search[value]");
	}
	
	//Se utiliza en la exportaciones
	public DataTableRequest(String orderColumn, String ascDsc, List<TableColumn> lstColumns) {
		super();
		this.orderColumn = orderColumn;
		this.ascDsc = ascDsc;
		this.lstColumns = lstColumns;
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
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
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TableColumn> getLstColumns() {
		return lstColumns;
	}

	public void setLstColumns(List<TableColumn> lstColumns) {
		this.lstColumns = lstColumns;
	}

	@SuppressWarnings("rawtypes")
	/**
	 * Recorro todos los parametros del request para quedarme tan solo con los correspondientes a las columnas, 
	 * devolviendo asi un listado de tipo DataTableColumn con los valores para las columnas que necesitamos en las busquedas. 
	 * @param request
	 * @return
	 */
	public List<TableColumn> getAllColumnsDataFromRequest (HttpServletRequest request) {
		
		List<TableColumn>lstColumns=new ArrayList<TableColumn>();
		
		Map map=request.getParameterMap();
		
		Iterator entries = map.entrySet().iterator();
		
		int indexColumn=0;
	
		while (entries.hasNext()) {
		    
			Map.Entry entry = (Map.Entry) entries.next();
		    String key = (String)entry.getKey();
		    if (key.contains("column")&&key.contains("data")){
		    	String data=request.getParameter("columns["+indexColumn+"][data]");
		    	if (data!=null && !data.isEmpty()){
		    		String name=request.getParameter("columns["+indexColumn+"][name]");
		    		Boolean searchable=new Boolean(request.getParameter("columns["+indexColumn+"][searchable]"));
		    		String seachValue=request.getParameter("columns["+indexColumn+"][search][value]");
		    		lstColumns.add(new TableColumn(name,searchable,seachValue));
		    	}
		    	indexColumn++;
		    }
		}
		
		return lstColumns;
	}	
}
