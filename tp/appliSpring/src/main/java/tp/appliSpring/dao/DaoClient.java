package tp.appliSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.entity.Client;

public interface DaoClient extends JpaRepository<Client,Long>{
	//@NamedQuery(name="Client.findByIdWithComptes") à placer sur classe entity.Client
    Client findByIdWithComptes(long idClient);
}
