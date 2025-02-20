package com.taskmanager.mapper;

import com.taskmanager.entity.ClientLocation;
import com.taskmanager.model.ClientLocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientLocationMapper {

    ClientLocationMapper INSTANCE = Mappers.getMapper(ClientLocationMapper.class);

    @Mapping(target = "projects", ignore = true)
    ClientLocation clientLocationDtoToClientLocation(ClientLocationDTO clientLocationDTO);

    ClientLocationDTO clientLocationToClientLocationDto(ClientLocation clientLocation);

}
