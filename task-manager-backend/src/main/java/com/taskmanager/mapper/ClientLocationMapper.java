package com.taskmanager.mapper;

import com.taskmanager.entity.ClientLocation;
import com.taskmanager.model.ClientLocationDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ClientLocationMapper {

    ClientLocation clientLocationDtoToClientLocation(ClientLocationDTO clientLocationDTO);

    ClientLocationDTO clientLocationToClientLocationDto(ClientLocation clientLocation);

}
