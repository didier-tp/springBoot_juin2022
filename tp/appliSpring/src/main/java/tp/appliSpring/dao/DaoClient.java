package tp.appliSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.entity.Client;

public interface DaoClient extends JpaRepository<Client,Long>{

}
