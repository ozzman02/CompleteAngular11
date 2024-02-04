package com.taskmanager.service.impl;

import com.taskmanager.mapper.ClientLocationMapper;
import com.taskmanager.model.ClientLocationDTO;
import com.taskmanager.repository.ClientLocationRepository;
import com.taskmanager.service.ClientLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientLocationServiceImpl implements ClientLocationService {

    private final ClientLocationRepository clientLocationRepository;

    private final ClientLocationMapper clientLocationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClientLocationDTO> findAll() {
        return clientLocationRepository.findAll().stream()
                .map(clientLocationMapper::clientLocationToClientLocationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientLocationDTO findById(String id) {
        return clientLocationRepository.findById(UUID.fromString(id.trim()))
                .map(clientLocationMapper::clientLocationToClientLocationDto).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientLocationDTO findByName(String name) {
        return clientLocationMapper.clientLocationToClientLocationDto(clientLocationRepository.findByName(name));
    }

}
