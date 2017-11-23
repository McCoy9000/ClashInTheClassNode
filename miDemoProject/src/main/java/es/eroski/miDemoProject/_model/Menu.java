/**
 * 
 */
package es.eroski.miDemoProject._model;

/**
 * @author BICUGUAL
 * Representa la estructura arborea de niveles de menú a la que  pertenece la sección de la aplicación que se está visualizando. 
 *En caso de ser necesario, podría utilizarse tambien como *"migas de pan"*.
 */
public class Menu {

	public String menuLevel1, menuLevel2, menuLevel3, menuLevel4, menuLevel5;
	
	public Menu() {
		super();
	}
	
	public Menu(String menuLevel1) {
		super();
		this.menuLevel1 = menuLevel1;
	}
	
	public Menu(String menuLevel1, String menuLevel2) {
		super();
		this.menuLevel1 = menuLevel1;
		this.menuLevel2 = menuLevel2;
	}
	
	public Menu(String menuLevel1, String menuLevel2, String menuLevel3) {
		super();
		this.menuLevel1 = menuLevel1;
		this.menuLevel2 = menuLevel2;
		this.menuLevel3 = menuLevel3;
	}
	
	public Menu(String menuLevel1, String menuLevel2, String menuLevel3, String menuLevel4) {
		super();
		this.menuLevel1 = menuLevel1;
		this.menuLevel2 = menuLevel2;
		this.menuLevel3 = menuLevel3;
		this.menuLevel4 = menuLevel4;
	}
	
	public Menu(String menuLevel1, String menuLevel2, String menuLevel3, String menuLevel4, String menuLevel5) {
		super();
		this.menuLevel1 = menuLevel1;
		this.menuLevel2 = menuLevel2;
		this.menuLevel3 = menuLevel3;
		this.menuLevel4 = menuLevel4;
		this.menuLevel5 = menuLevel5;
	}

	public String getMenuLevel1() {
		return menuLevel1;
	}

	public void setMenuLevel1(String menuLevel1) {
		this.menuLevel1 = menuLevel1;
	}

	public String getMenuLevel2() {
		return menuLevel2;
	}

	public void setMenuLevel2(String menuLevel2) {
		this.menuLevel2 = menuLevel2;
	}

	public String getMenuLevel3() {
		return menuLevel3;
	}

	public void setMenuLevel3(String menuLevel3) {
		this.menuLevel3 = menuLevel3;
	}

	public String getMenuLevel4() {
		return menuLevel4;
	}

	public void setMenuLevel4(String menuLevel4) {
		this.menuLevel4 = menuLevel4;
	}

	public String getMenuLevel5() {
		return menuLevel5;
	}

	public void setMenuLevel5(String menuLevel5) {
		this.menuLevel5 = menuLevel5;
	}
	
	
	
	
}
