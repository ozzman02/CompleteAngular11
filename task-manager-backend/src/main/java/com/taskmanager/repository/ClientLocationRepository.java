package com.taskmanager.repository;

import com.taskmanager.entity.ClientLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientLocationRepository extends JpaRepository<ClientLocation, UUID> {

    ClientLocation findByName(String name);

}
