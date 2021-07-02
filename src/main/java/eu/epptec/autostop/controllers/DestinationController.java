package eu.epptec.autostop.controllers;


import eu.epptec.autostop.model.Address;
import eu.epptec.autostop.model.Destination;
import eu.epptec.autostop.services.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides/{rideId}/destinations")
@ComponentScan(basePackageClasses = {DestinationModelAssembler.class})
public class DestinationController {
    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private DestinationModelAssembler assembler;

    @PostMapping()
    EntityModel<Destination> addDestination(@RequestBody Destination destination, @PathVariable Long rideId) {
        destination = destinationService.save(destination, rideId);

        return assembler.toModel(destination);
    }

    @GetMapping("/{destinationId}")
    EntityModel<Destination> findById(@PathVariable Long destinationId) {
        Destination destination = destinationService.findById(destinationId);

        return assembler.toModel(destination);
    }

    @PutMapping("/{destinationId}")
    EntityModel<Destination> replace(@RequestBody Destination destination, @PathVariable Long destinationId) {
        destination = destinationService.replace(destination, destinationId);

        return assembler.toModel(destination);
    }

    @DeleteMapping("/{destinationId}")
    ResponseEntity<Object> deleteById(@PathVariable Long destinationId) {
        destinationService.deleteById(destinationId);

        return ResponseEntity.noContent().build();
    }
}
