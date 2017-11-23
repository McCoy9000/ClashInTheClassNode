/**
 * 
 */
package es.eroski.miDemoProject._demo.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.eroski.miDemoProject._demo.service.DemosIBS;

import es.eroski.miDemoProject._exceptions.GenericException;
import es.eroski.miDemoProject._exceptions.GenericJsonException;
import es.eroski.miDemoProject._model.Menu;
import es.eroski.miDemoProject._model.OptionSelectBean;
import es.eroski.miDemoProject._model.JqgridPage;
import es.eroski.miDemoProject._model.JqgridPaginationRequest;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Modulo de Demos
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/demos")
public class DemosController {
	@Autowired
	DemosIBS demoService;
	
	
	@Autowired
	Messages messages;
	
	/**
	 * Pagina demo para Ventana modal
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ventanaModal", method = RequestMethod.GET)
	public String ventanaModal(Principal principal, Model model){
		Menu menu=new Menu(messages.get("menu.demos"), messages.get("menu.modal"));
		model.addAttribute("menu", menu);
		
		return "demo/ventanaModal";	
	}
	
	/**
	 * Muestra demo de pagina con el menu horizontal completo
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/menuHorizontalCompleto", method = RequestMethod.GET)
	public String menuComplejo(Principal principal, Model model){
		Menu menu=new Menu(messages.get("menu.demos"), messages.get("menu.widgets"), messages.get("menu.menuHorizontal"),messages.get("menu.completo"));
		model.addAttribute("menu", menu);
		
		return "demo/menuHorizontalCompleto";
	}
	/**
	 * Muestra demo de pagina con el menu horizontal simple
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/menuHorizontalSimple", method = RequestMethod.GET)
	public String menuSimple(Principal principal, Model model){
		model.addAttribute("menu", new Menu("menu.simple"));
		
		return "demo/menuHorizontalSimple";
	}
	
	/**
	 * Muestra demo de pagina para la entrada de menu Mi Cuenta en el menú de usuario
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/miCuenta", method = RequestMethod.GET)
	public String miCuenta(Principal principal, Model model){
		Menu menu=new Menu("", messages.get("menu.miCuenta"));
		model.addAttribute("menu", menu);
		
		return "demo/miCuenta";
	}
	
	/**
	 * Muestra demo de pagina que utiliza tabs o pestañas para presentar datos.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tabs", method = RequestMethod.GET)
	public String tabs(Model model){
		Menu menu=new Menu(messages.get("menu.demos"),  messages.get("menu.widgets"), messages.get("menu.tabs"));
		model.addAttribute("menu", menu);
		
		return "demo/tabs";
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/testErrorPage",method = RequestMethod.GET)
	public String testErrorPage(Model model) {
		
		String prueba=null;
		
		try{
			prueba.toCharArray();
		}
		catch(Exception e){
			
//			throw new GenericException(e);
			throw new GenericException("Mensaje de error que se muestra al usuario ",e);

		}		
		return prueba;
		
	}
	
	/**
	 * Muestra demo de formulario que utiliza lista de desplegables anidados, datepicker.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/formulario", method = RequestMethod.GET)
	public String formulario(Model model){
		Menu menu=new Menu(messages.get("menu.demos"),  messages.get("menu.formulario"));
		model.addAttribute("menu", menu);
		
		try{
			//Cargo las areas en el modelo
			model.addAttribute("lstAreas",demoService.getLstAreas());
		}catch(Exception e){
			throw new GenericException("Error al mostrar la pagina demo de formulario", e);
		}
		
		
		return "demo/formulario";
	}
	
	/**
	 * Devuelve una lista de responsable en formato JSON para cargar un select o combo del formulario 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value="/area/{codigo}/responsables", method = RequestMethod.GET)
	public  @ResponseBody List<OptionSelectBean<Integer>> getResponsables(
			@PathVariable("codigo") Object codigo){
	
		List<OptionSelectBean<Integer>> lstResponsables=new ArrayList<OptionSelectBean<Integer>>();
		try{
			//Cargo la lista de responsables
			lstResponsables=demoService.getLstResponsablesByIdArea(new Integer(codigo.toString()));
		}catch(Exception e){
			throw new GenericJsonException("Error al mostrar la página demo de formulario", e);
		}
		
		return lstResponsables;
	}
	
	
	/**
	 * Muestra varias demo del componente datetimepicker
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/datetimepicker", method = RequestMethod.GET)
	public String datetimepicker(Model model){
		Menu menu=new Menu(messages.get("menu.demos"),  messages.get("menu.widgets"), messages.get("menu.datetimepicker"));
		model.addAttribute("menu", menu);
		
		return "demo/datetimepicker";
	}
	
	
	/**
	 * Muestra demo de pagina que utiliza tabs o pestañas para presentar datos.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/duallistbox", method = RequestMethod.GET)
	public String duallistbox(Model model){
		Menu menu=new Menu(messages.get("menu.demos"),  messages.get("menu.widgets"), messages.get("menu.duallistbox"));
		model.addAttribute("menu", menu);
		
		return "demo/duallistbox";
	}
	
	
	/**
	 * Entrada a la pagina de ejemplo de componente autocomplete
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/autocomplete", method = RequestMethod.GET)
	public String autocomplete(Model model){
		Menu menu=new Menu(messages.get("menu.demos"),  messages.get("menu.widgets"), messages.get("menu.autocomplete"));
		model.addAttribute("menu", menu);
		
		return "demo/autocomplete";
	}
	
	
	/**
	 * Autocomplete para el combo de grupos
	 * @param request
	 * @param term
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/lenguaje", method = RequestMethod.GET)
	public  @ResponseBody List<OptionSelectBean<Integer>>  getLenguajes(HttpServletRequest request,
			@RequestParam(value = "term", required = false) String term,
			HttpServletResponse response) {
		
		List<OptionSelectBean<Integer>>  lstLenguajes= new ArrayList<>();
		
		try{
			lstLenguajes=demoService.getLstLenguajes(term);
			
		}catch(Exception e){
			throw new GenericJsonException("Error en la operación", e);
		}
		
		return lstLenguajes;
	}
	
	
	/**
	 * Muestra jsp JqGrid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/jqgrid", method = RequestMethod.GET)
	public String jqgrid(Model model){
		Menu menu=new Menu(messages.get("menu.demos"), messages.get("menu.jqgrid"),  messages.get("menu.jqgrid.find"));
		model.addAttribute("menu", menu);
		
		return "jqGrid/jqgrid";
	}
	
	/**
	 * Busqueda de resultados para el jqgrid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/jqgridLoadData", method = RequestMethod.GET)
	public  @ResponseBody JqgridPage<Usuario> getPagedItems(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "descripcion", required = false) String descripcion,
			@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
			@RequestParam(value = "max", required = false, defaultValue = "10") Long max,
			@RequestParam(value = "index", required = false) String index,
			@RequestParam(value = "sortorder", required = false) String sortOrder,
			HttpServletResponse response, HttpSession session) throws Exception{
			
		JqgridPaginationRequest pagination= new JqgridPaginationRequest(max,page);
		
		if (index!=null && !index.isEmpty()){
			pagination.setSort(index);
        }
        
		if (sortOrder!=null){
            pagination.setAscDsc(sortOrder);
        }
		
		JqgridPage<Usuario> list=new JqgridPage<Usuario>();
		try {
			list = demoService.getJGResponseItems(pagination, username, descripcion, null);
		} catch (Exception e) {
			throw new GenericJsonException("Error en la operación", e);
		}
		
		return list;		

	}
	
	
}
