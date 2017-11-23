/**
 * 
 */
package es.eroski.miDemoProject.test.service;

import java.util.List;

import es.eroski.miDemoProject._model.Carta;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.generic.service.GenericIBS;

/**
 * Implementacion para la capa de servicio del modulo de demos
 * @author BICUGUAL
 *
 */
public interface TestIBS extends GenericIBS<Usuario> {

	public List<Carta> getUserList();

}
