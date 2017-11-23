/**
 * 
 */
package es.eroski.miDemoProject.report.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import es.eroski.miDemoProject._model.GenericExcelReport;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 
 * @author BICUGUAL
 */
public class GenericExcelView extends AbstractExcelView {

	private static final String  LISTADO_GRID_GENERICO_XLS = "/excel/listaGenerica.xls";

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
			   HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		  GenericExcelReport formData = (GenericExcelReport) model.get("formData");
		
		  String excelName=(String) model.get("excelName");
		  
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
				throw e;
			} 
				finally {
				if (out != null) {
					try {
						out.flush();
						out.close();
					} catch (IOException e) {
						throw e;
					}
				}
			}
		  

	}

}
