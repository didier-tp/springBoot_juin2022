package tp.appliSpring.dao;

import org.springframework.data.repository.CrudRepository;

import tp.appliSpring.entity.Client;

//  ou bien public interface CompteDao extends JpaRepository<Client,Long>{
public interface ClientDao extends CrudRepository<Client,Long>{
	
	 //...
}
