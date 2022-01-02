package com.git.selection.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.IOException;
import java.sql.SQLException;

@Configuration
@Slf4j
@EnableCaching
// TODO: cache only most requested data!
public class AppConfig {

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server hsqlServer() throws IOException, AclFormatException {
//        Server bean = new Server();
//        return bean;
//    }
//    @Bean
//    @DependsOn("hsqlServer") // This is important!!
//    public DataSource getDataSource(
//            @Autowired DataSourceProperties dsProps) {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(dsProps.getDriverClassName());
//        dataSourceBuilder.url(dsProps.getUrl());
//        dataSourceBuilder.username(dsProps.getUsername());
//        dataSourceBuilder.password(dsProps.getPassword());
//        return dataSourceBuilder.build();
//    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException {
        log.info("Start H2 TCP server");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    //    https://stackoverflow.com/a/46947975/548473
    @Bean
    Module module() {
        return new Hibernate5Module();
    }
}