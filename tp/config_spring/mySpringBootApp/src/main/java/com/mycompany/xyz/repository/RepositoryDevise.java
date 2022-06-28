package com.mycompany.xyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.xyz.entity.Devise;

public interface RepositoryDevise extends JpaRepository<Devise,String>{
//.findById()
//.save()
//.deleteById()
  List<Devise> findByChangeGreaterThanEqual(Double changeMini);
}
