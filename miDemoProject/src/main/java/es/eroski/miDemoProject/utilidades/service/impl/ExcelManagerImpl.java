/**
 * 
 */
package es.eroski.miDemoProject.utilidades.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import es.eroski.miDemoProject._model.GenericExcelReport;
import es.eroski.miDemoProject.utilidades.service.ExcelManager;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * @author BICUGUAL
 *
 */

@Service
public class ExcelManagerImpl implements ExcelManager {

	private static final String  LISTADO_GRID_GENERICO_XLS = "/excel/listaGenerica.xls";


	@Override
	public void genericExportGridData(GenericExcelReport formData, String excelName,	HttpServletResponse response) throws Exception {
		response.setContentType("text/xls; charset=utf-8");
		
		response.setHeader("Content-Disposition","attachment; filename="+excelName+".xls");
		OutputStream out = null;
		
		try {			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("excelTitle", excelName.replace('_', ' '));
			
			map.put("columnNames", formData.getColumnNames());
			map.put("records", formData.getDatos());
			
		    XLSTransformer transformer = new XLSTransformer();
		    InputStream templateIS = this.getClass().getClassLoader().getResourceAsStream(LISTADO_GRID_GENERICO_XLS);
		 
			org.apache.poi.ss.usermodel.Workbook resultWorkbook = transformer.transformXLS(templateIS, map);

			out = response.getOutputStream(); 	
			resultWorkbook.write(out);
			out.close();
		   
		} catch (IOException e) {
			//logger.error(StackTraceManager.getStackTrace(e));
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			throw e;
		} 
			finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					//logger.error(StackTraceManager.getStackTrace(e));
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					throw e;
				}
			}
		}
		
	}

}
