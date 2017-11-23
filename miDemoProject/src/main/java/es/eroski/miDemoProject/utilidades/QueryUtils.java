package es.eroski.miDemoProject.utilidades;

import es.eroski.miDemoProject._model.TableColumn;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.JqgridPaginationRequest;

public class QueryUtils {


	/**
	 * PAGINACION DATATABLES
	 * 
	 * Envuelve la consulta en un rownum para establecer el numero de registros a mostrar en base a los parametros recibidos que indican cual es 
	 * el registro inicial y cual es el último 
	 * @param datatableFilters
	 * @param query
	 * @return
	 */
    public static  String getQueryLimits(DataTableRequest datatableFilters , String query)
    {
		String queryAux="";
		String order=(datatableFilters.getOrderColumn()!=null && !datatableFilters.getOrderColumn().isEmpty() && !"0".equals(datatableFilters.getOrderColumn())) ? " ORDER BY "+datatableFilters.getOrderColumn() : "";
		String orderAscDsc=!order.isEmpty() ? " "+datatableFilters.getAscDsc() : "";
		
		if (datatableFilters.getLength()!=null && datatableFilters.getStart()!=null){
			queryAux= "SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query + order+orderAscDsc+" )a) where "
					+ "rnum > " + (datatableFilters.getStart()) +" and rnum < " +(datatableFilters.getStart()+datatableFilters.getLength()+1);
			return queryAux;
		}else{
			queryAux= "SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query + order + orderAscDsc + ")a)" ;
			return queryAux;
		}
    }
    
    
    /**
	 * FILTRO DE BUSQUEDAS POR COLUMNAS. 
	 * 
	 * Contruccion de la where concatenando condiciones AND en funcion de los filtros de busqueda por
	 * columna en el datatable.
	 * Para uso con busqueda de filtro individual por columna. Ignora sensitiveCase y acentos. 
	 * @param datatableFilters
	 * @return
	 */
	public static String getWhereColumnConditionTranslateAndUpper(DataTableRequest datatableFilters){
		StringBuffer whereSearch=new StringBuffer();
		for (TableColumn dtc:datatableFilters.getLstColumns()){
			if (dtc.getSearchable() && !dtc.getSearchValue().isEmpty()){
				if (whereSearch.length()!=0)
					whereSearch.append(" AND ");
					whereSearch.append("TRANSLATE (UPPER ("+dtc.getName()+"), 'ÁÉÍÓÚ', 'AEIOU') "
					+ "LIKE TRANSLATE(UPPER ('%"+dtc.getSearchValue() + "%'), 'ÁÉÍÓÚ', 'AEIOU')");
			}
		}
		return whereSearch.toString();
	}
	/**
	 * FILTRO DE BUSQUEDA GENERAL 
	 * Contruccion de la where concatenando condiciones OR para todas las columnas configuradas como "de busqueda" 
	 * con el filtro de busqueda general.
	 * Para uso con busqueda general en todas las columnas. Ignora sensitiveCase y acentos.
	 * @param datatableFilters
	 * @return
	 */
	public static String getWhereGlobalConditionTranslateAndUpper(DataTableRequest datatableFilters){
		StringBuffer whereSearch=new StringBuffer();
		for (TableColumn dtc:datatableFilters.getLstColumns()){
			if (dtc.getSearchable()){
				if (whereSearch.length()!=0)
					whereSearch.append(" OR ");
					whereSearch.append("TRANSLATE (UPPER ("+dtc.getName()+"), 'ÁÉÍÓÚ', 'AEIOU') "
					+ "LIKE TRANSLATE(UPPER ('%"+datatableFilters.getSearchValue() + "%'), 'ÁÉÍÓÚ', 'AEIOU')");
			}
		}
		return whereSearch.toString();
	}
	
	/**
	 * PAGINACION JQGRID
	 * 
	 * Envuelve la consulta en un rnum para establecer el numero de registros a mostrar en base a los parametros recibidos que indican cual es 
	 * el registro inicial y cual es el último 
	 * @param pagination
	 * @param query
	 * @return
	 */
	public static  String getQueryLimits(JqgridPaginationRequest pagination , String query)
	    {
			String queryAux="";
			if (pagination.getPage()!=null && pagination.getRows()!=null){
				Long paginationRows = pagination.getRows();	
				queryAux= "SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query + ")a) where rnum > " + (paginationRows*(pagination.getPage()-1)) +" and rnum < " +(paginationRows*(pagination.getPage())+1);
				return queryAux;
			}else{
				Long paginationRows = pagination.getRows();	
				queryAux= "SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + query + ")a) where rnum > 1 and rnum < " +(paginationRows+1);
				return queryAux;
			}
			
	        
	    }

}
	
