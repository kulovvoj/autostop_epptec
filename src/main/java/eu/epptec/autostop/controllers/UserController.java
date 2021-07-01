package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.User;
import eu.epptec.autostop.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@ComponentScan(basePackageClasses = {UserModelAssembler.class})
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserModelAssembler assembler;

    @PostMapping(consumes = "application/json", produces = "application/json")
    EntityModel<User> addUser(@RequestBody User user) {
        user = userService.save(user);

        return assembler.toModel(user);
    }

    @GetMapping()
    CollectionModel<EntityModel<User>> findAll() {
        List<EntityModel<User>> users = userService.findAll()
                .stream()
                .map(user -> assembler.toModel(user))
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);

        return assembler.toModel(user);
    }

    @PutMapping("/users/{id}")
    EntityModel<User> replace(@RequestBody User user, @PathVariable Long id) {
        user = userService.replace(user, id);

        return assembler.toModel(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteById(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
