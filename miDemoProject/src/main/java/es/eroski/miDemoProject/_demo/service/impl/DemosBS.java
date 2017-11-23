/**
 * 
 */
package es.eroski.miDemoProject._demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eroski.miDemoProject._demo.persistence.DemosIDAO;
import es.eroski.miDemoProject._demo.service.DemosIBS;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.GenericExcelReport;
import es.eroski.miDemoProject._model.GenericExcelRow;
import es.eroski.miDemoProject._model.JqgridPage;
import es.eroski.miDemoProject._model.JqgridPaginationRequest;
import es.eroski.miDemoProject._model.OptionSelectBean;
import es.eroski.miDemoProject._model.TableExportForm;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.utilidades.service.JqgridPaginationIBS;
import es.eroski.miDemoProject.utilidades.service.impl.JqgridPaginationBS;

/**
 * @author BICUGUAL
 *
 */
@Service
public class DemosBS implements DemosIBS {

	
	@Autowired
	DemosIDAO demoDAO;

	@Override
	public JqgridPage<Usuario> getJGResponseItems(JqgridPaginationRequest jqGridRequest, String username, String descripcion, Integer flagldap)
			throws Exception {
		JqgridPaginationIBS<Usuario> paginationManager = new JqgridPaginationBS<Usuario>();
		
		List<Usuario> lstItems=demoDAO.findAllJG(jqGridRequest, username, descripcion, flagldap);
		
		JqgridPage<Usuario> result = null;
		
		if (lstItems != null) {
			//Obtengo el numero total del registros mediante otra consulta porque la lista de referencias obtenida está ya paginada y no incluye todos los registros.
			int records = demoDAO.findCountJG(jqGridRequest, username, descripcion, flagldap).intValue();
			result = paginationManager.paginate(new JqgridPage<Usuario>(), lstItems,
					jqGridRequest.getRows().intValue(), records, jqGridRequest.getPage().intValue());	
		}
		else {
			return new JqgridPage<Usuario>();
		} 
		
		return result;
	}
	
	@Override
	@Transactional("transactionManagerSecurity")
	public void updateJqGridUsuario (Usuario item) throws Exception{
		Usuario oldUser=demoDAO.getItemById(item.getUsername());
		
		//Si el flagldap o la descripcion son nulas, cargamos las anteriores
		item.setFlagldap(item.getFlagldap()==null?oldUser.getFlagldap():item.getFlagldap());
		item.setPassword(item.getPassword()==null?oldUser.getPassword():item.getPassword());
		
		demoDAO.updateItem(item);
	}
	
	@Override
	public DataTableResponse<Usuario> getDTResponseItems(DataTableRequest datatableFilter) {
		List<Usuario> lstItems=demoDAO.findAll(datatableFilter);
		Long recordsTotal=demoDAO.findCount(datatableFilter);
		Long recordsFiltered=recordsTotal;
		return new DataTableResponse<Usuario>(datatableFilter.getDraw(), recordsTotal, recordsFiltered, lstItems, null);
	}

	@Override
	public Usuario getItem(Object id) {
		Usuario item=demoDAO.getItemById(id);
		return item;
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void updateItem(Usuario item) throws Exception{
		
		demoDAO.updateItem(item);
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void createItem(Usuario item) throws Exception {
		demoDAO.createItem(item);
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void deleteItems(List<Object> lstIds) {
		for (Object id: lstIds){
			demoDAO.deleteItem((String) id);
		}
	}

	@Override
	public GenericExcelReport getItemstoExport(TableExportForm form)
			throws Exception {
		//Preparo las cabeceras
		List<String> cabeceras=form.getTitleColumns();
			
		//Preparo los datos
		DataTableRequest datatableFilters= new DataTableRequest(form.getOrderColumn(), form.getAscDsc(), form.getColumns());
		List<Usuario> lstItems=demoDAO.findAll(datatableFilters);
		
		//Cargo la lista generica para la plantilla
		List<GenericExcelRow> lstGenericItems=parseItemListToGenericList(lstItems);
		GenericExcelReport exportForm= new GenericExcelReport(cabeceras, lstGenericItems);
				
		return exportForm;
	}
	
	/**
	 * Convierte la lista de registros de a una lista de Objectos genericos para ser utilizados en 
	 * la plantilla excel.
	 * @param lstItems
	 * @return
	 */
	private List<GenericExcelRow> parseItemListToGenericList(List<Usuario> lstItems){
		List<GenericExcelRow> lstGeneric=new ArrayList<GenericExcelRow>();
		
		for (Usuario item:lstItems){
			GenericExcelRow generic=new GenericExcelRow();
			//Comienzo a cargar los datos en el campo2 porque la plantilla excel comienza en este campo. 
			//El field1 se reserva para campos de ID que puede que no quieran mostrarse
			generic.setField2(item.getUsername());
			generic.setField3(item.getPassword());
			generic.setField4(item.getFlagldap());
			generic.setField5(item.getDescripcion());
						
			lstGeneric.add(generic);
		}
		return lstGeneric;
	}
	
	public List<OptionSelectBean<Integer>> getLstAreas(){
		List<OptionSelectBean<Integer>> lstAreas=demoDAO.getLstAreas();
		
		return lstAreas;
	}
	
	@Override
	public List<OptionSelectBean<Integer>> getLstResponsablesByIdArea(Integer idArea) {
		List<OptionSelectBean<Integer>> lstResponsables=demoDAO.getLstResponsablesByIdArea(idArea);
		
		return lstResponsables;
	}

	@Override
	public List<OptionSelectBean<Integer>> getLstLenguajes(String term) {
		List<OptionSelectBean<Integer>> lstLenguajes=demoDAO.getLstLenguajes(term);
		
		return lstLenguajes;
	}
}
