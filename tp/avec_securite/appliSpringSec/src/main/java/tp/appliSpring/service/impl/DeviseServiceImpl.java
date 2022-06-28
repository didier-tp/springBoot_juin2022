package tp.appliSpring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.dao.DeviseDao;
import tp.appliSpring.entity.Devise;
import tp.appliSpring.exception.NotFoundException;
import tp.appliSpring.service.DeviseService;

@Service
@Transactional
public class DeviseServiceImpl implements DeviseService {
	
	private DeviseDao deviseDao;

	public DeviseServiceImpl(DeviseDao deviseDao) {
		//injection de dépendance par constructeur 
		//(plutot que via @Autowired)
		this.deviseDao = deviseDao; 
	}

	@Override
	public Devise rechercherDeviseParCode(String code) {
		Optional<Devise> optionalDevise = deviseDao.findById(code);
		if(optionalDevise.isPresent()) {
			return optionalDevise.get();
		}else {
			throw new NotFoundException("aucune devise existe avec le code="+code);
			//avec @ResponseStatus(HttpStatus.NOT_FOUND) 
			//au dessus de class NotFoundException ..{}
		}
	}

	@Override
	public List<Devise> rechercherToutesDevises() {
		return deviseDao.findAll();
	}

	@Override
	public void supprimerDeviseParCode(String code) {
		deviseDao.deleteById(code);
	}

	@Override
	public void sauvegarderDevise(Devise d) {
       deviseDao.save(d);
	}

	@Override
	public boolean existeOuPas(String code) {
		return deviseDao.existsById(code);
	}

}
