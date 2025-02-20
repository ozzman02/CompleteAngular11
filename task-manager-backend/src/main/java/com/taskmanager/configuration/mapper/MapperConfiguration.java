package com.taskmanager.configuration.mapper;

import com.taskmanager.mapper.ClientLocationMapper;
import com.taskmanager.mapper.ProjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ClientLocationMapper clientLocationMapper() { return ClientLocationMapper.INSTANCE; }

    @Bean
    public ProjectMapper projectMapper() { return ProjectMapper.INSTANCE; }

}
