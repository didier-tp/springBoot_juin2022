package tp.appliSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.dao.DaoClient;
import tp.appliSpring.entity.Client;

@Service
@Transactional
public class ServiceClientImpl implements ServiceClient {
	
	@Autowired
	private DaoClient daoClient;

	@Override
	public Client rechercherClientParId(long id) {
		return daoClient.findById(id).orElse(null);
	}

	@Override
	public Client rechercherClientAvecComptesParId(long id) {
		return daoClient.findByIdWithComptes(id);
	}

	@Override
	public Client sauvegarderClient(Client cli) {
		return daoClient.save(cli);
	}

}
