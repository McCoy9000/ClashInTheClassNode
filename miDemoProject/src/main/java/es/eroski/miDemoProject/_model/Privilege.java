package es.eroski.miDemoProject._model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Clase de modelo que representa las authoritys del usuario
 * @author BICUGUAL
 */
@SuppressWarnings("serial")
public class Privilege implements GrantedAuthority {
	private String name;

    @Override  
	public String toString() {
	        StringBuilder builder = new StringBuilder();
	        builder.append("Privilege [name=");
	        builder.append(name);
	        builder.append("]");
	        return builder.toString();
	    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

}
