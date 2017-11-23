/**
 * 
 */
package es.eroski.miDemoProject.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eroski.miDemoProject._model.Carta;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.test.persistence.TestIDAO;
import es.eroski.miDemoProject.test.service.TestIBS;

/**
 * @author BICUGUAL
 *
 */
@Service
public class TestBS implements TestIBS {

	
	@Autowired
	TestIDAO testDAO;

	public List<Carta> getUserList() {
		
		List<Carta> lstItems = testDAO.findAll();
		return lstItems;
		
	}
	
	
	@Override
	public DataTableResponse<Usuario> getDTResponseItems(DataTableRequest datatableFilter) {
		List<Usuario> lstItems=testDAO.findAll(datatableFilter);
		Long recordsTotal=testDAO.findCount(datatableFilter);
		Long recordsFiltered=recordsTotal;
		return new DataTableResponse<Usuario>(datatableFilter.getDraw(), recordsTotal, recordsFiltered, lstItems, null);
	}

	@Override
	public Usuario getItem(Object id) {
		Usuario item=testDAO.getItemById(id);
		return item;
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void updateItem(Usuario item) throws Exception{
		
		testDAO.updateItem(item);
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void createItem(Usuario item) throws Exception {
		testDAO.createItem(item);
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void deleteItems(List<Object> lstIds) {
		for (Object id: lstIds){
			testDAO.deleteItem((String) id);
		}
	}


}
