package tp.appliSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.entity.Pays;

public interface PaysDao  extends JpaRepository<Pays,String> {

}
