package es.eroski.miDemoProject.sesiones.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Service;

import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject.sesiones.persistence.SessionIDAO;
import es.eroski.miDemoProject.sesiones.service.SessionIBS;

@Service
public class SessionBS implements SessionIBS{
	
private static Logger logger = Logger.getLogger(SessionBS.class);
	
	@Autowired
	SessionIDAO sessionDao;
	
	@Override
	public DataTableResponse<SessionInformation> getDTResponseSessions(DataTableRequest datatableFilter) {
		List<SessionInformation> lstItems=sessionDao.findAll(datatableFilter);
		Long recordsTotal=sessionDao.findCount(datatableFilter);
		Long recordsFiltered=recordsTotal;
		return new DataTableResponse<SessionInformation>(datatableFilter.getDraw(), recordsTotal, recordsFiltered, lstItems, null);

	}

	@Override
	public Long expirarSession(String sesion) {
		logger.info("Solicitud expiracion sesion id : " + sesion);
		return sessionDao.ExpirarSesion(sesion);
	}

}
