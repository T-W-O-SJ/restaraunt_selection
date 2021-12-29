package com.git.selection.web.restaurant;

import com.git.selection.model.Dish;
import com.git.selection.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController  {
    static final String REST_URL = "/admin/restaurants";

    DishService service;


    @GetMapping("{restaurantId}/{id}")
    public Dish get(@PathVariable int id,@PathVariable int restaurantId) {
        return service.get(id,restaurantId);
    }

    @DeleteMapping("{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id,@PathVariable int restaurantId) {
       service.delete(id,restaurantId);
    }


    @GetMapping(value = "/{restaurantId}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(int restaurantId) {
        return service.getAll(restaurantId);
    }

    @PutMapping(value = "/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int id) {
        service.update(dish, id);
    }

    @PostMapping(value = "/{id}/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable int id) {
        Dish created = service.create(dish,id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
