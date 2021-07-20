package eu.epptec.autostop.controllers;

import eu.epptec.autostop.dtos.RatingDTO;
import eu.epptec.autostop.dtos.UserDTO;
import eu.epptec.autostop.model.User;
import eu.epptec.autostop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping()
    Page<UserDTO> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/rating")
    RatingDTO getRating(@PathVariable Long id) {
        return userService.getRating(id);
    }

    @PutMapping("/{id}")
    UserDTO replace(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return userService.replace(userDTO, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteById(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/timeNow")
    Timestamp getTimeNow() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return now;
    }
}
