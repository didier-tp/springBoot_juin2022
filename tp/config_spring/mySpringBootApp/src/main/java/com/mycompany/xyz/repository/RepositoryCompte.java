package com.mycompany.xyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.xyz.entity.Compte;

public interface RepositoryCompte extends JpaRepository<Compte,Long>{
	
	   List<Compte> findByClientNumero(Long numClient);

}
