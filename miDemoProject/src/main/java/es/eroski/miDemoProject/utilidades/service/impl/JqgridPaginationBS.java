package es.eroski.miDemoProject.utilidades.service.impl;

import java.util.List;

import es.eroski.miDemoProject._model.JqgridPage;
import es.eroski.miDemoProject.utilidades.service.JqgridPaginationIBS;



public class JqgridPaginationBS<T> implements JqgridPaginationIBS<T> {

	@Override
	public JqgridPage<T> paginate (JqgridPage<T> resource, List<T> list, int max, int records, int page){
		resource.setRows(list);
		resource.setRecords(records+"");
		resource.setPage(page+"");
		int total = this.getTotalPages(records, max);
		resource.setTotal(total+"");
		
		return resource;
	}
	
	public int getPaginationLimit(int size, int max, int page){
		if ((page*max) <= size){
			size = (page*max);
		}
		return size;
	}
	
	public int getTotalPages(int records, int max){
		int total = records/max;
		if(records%max != 0){
			total++;
		}
		return total;
	}
	
}