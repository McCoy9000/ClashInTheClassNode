package es.eroski.miDemoProject.utilidades.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import es.eroski.miDemoProject._model.GenericExcelReport;
/**
 * Manager para gestionar las exportaciones a excel
 * @author BICUGUAL
 *
 */
@Service
public interface ExcelManager{
	
	/**
	 * Abre la plantilla excel cargando los datos del grid
	 * @param formData
	 * @param excelName. Este campo se utiliza para establecer el nombre y el titulo del listado. No debe de contener espacios. Si contiene mas de una palabra
	 * la deberemos de separa con el simbolo "_". Ej: Listado_de_Bultos.
	 * @param messageSource
	 * @param response
	 * @throws Exception
	 */
	public void genericExportGridData(GenericExcelReport formData,String excelName, HttpServletResponse response)	throws Exception;
}
