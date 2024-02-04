package com.taskmanager.service;

import com.taskmanager.model.ClientLocationDTO;

import java.util.List;

public interface ClientLocationService {

    List<ClientLocationDTO> findAll();

    ClientLocationDTO findById(String id);

    ClientLocationDTO findByName(String name);

}
