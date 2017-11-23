package es.eroski.miDemoProject._model;
/**
 * Clase de modelo para los permisos
 * @author BICUGUAL
 */
public class Permission  {
	
	private String permissionname;
	private String description;
	
	@Override
	public String toString() {
		return "Permission [permissionname=" + permissionname + ", description=" + description + "]";
	}
	public String getPermissionname() {
		return permissionname;
	}
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
