package com.git.selection.web.restaurant;

import com.git.selection.model.Restaurant;
import com.git.selection.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestService.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestService {
    static final String REST_URL = "/admin/restaurants";

    RestaurantService service;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithDishes() {
        return service.getAll();
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant) {
        service.update(restaurant);
    }

    @PostMapping(value = "/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
