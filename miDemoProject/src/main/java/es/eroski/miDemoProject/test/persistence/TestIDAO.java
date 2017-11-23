package es.eroski.miDemoProject.test.persistence;

import java.util.List;

import es.eroski.miDemoProject._model.Carta;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.generic.persistence.GenericDAO;

/**
 * @author BICUGUAL
 *
 */
public interface TestIDAO extends GenericDAO<Usuario>{

	public List<Carta> findAll();

}
