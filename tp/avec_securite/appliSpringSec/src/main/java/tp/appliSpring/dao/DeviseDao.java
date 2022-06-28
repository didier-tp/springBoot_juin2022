package tp.appliSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.entity.Devise;

public interface DeviseDao extends JpaRepository<Devise,String> {

}
