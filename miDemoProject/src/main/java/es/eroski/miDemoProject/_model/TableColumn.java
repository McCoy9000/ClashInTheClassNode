/**
 * 
 */
package es.eroski.miDemoProject._model;

/**
 * @author BICUGUAL
 *
 */
public class TableColumn {

	private String name;
	private Boolean searchable=true;
	private String searchValue;
	
	public TableColumn() {
		super();
	}	
	
	public TableColumn(String name, Boolean searchable, String searchValue) {
		super();
		this.name = name;
		this.searchable = searchable;
		this.searchValue = searchValue;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSearchable() {
		return searchable;
	}

	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}
