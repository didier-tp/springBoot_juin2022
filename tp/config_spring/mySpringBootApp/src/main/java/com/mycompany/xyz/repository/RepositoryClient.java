package com.mycompany.xyz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.xyz.entity.Client;

public interface RepositoryClient extends JpaRepository<Client,Long>{

}
