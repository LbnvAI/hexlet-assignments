package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteUser(@PathVariable int id) {
        return userService.delete(id);
    }
    // END
}
