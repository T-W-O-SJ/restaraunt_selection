package com.git.selection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantSelectionRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantSelectionRestApplication.class, args);
    }

    //TODO  make H2 server and refactor Repository Service RestControllers

}
