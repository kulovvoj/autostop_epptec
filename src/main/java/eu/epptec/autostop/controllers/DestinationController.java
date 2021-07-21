package eu.epptec.autostop.controllers;

import eu.epptec.autostop.dtos.DestinationDTO;
import eu.epptec.autostop.model.Destination;
import eu.epptec.autostop.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/rides/{rideId}/destinations")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @PostMapping()
    @ResponseStatus(CREATED)
    DestinationDTO addDestination(@RequestBody DestinationDTO destinationDTO, @PathVariable Long rideId) {
        return destinationService.save(destinationDTO, rideId);
    }

    @GetMapping("/{destinationId}")
    DestinationDTO findById(@PathVariable Long destinationId) {
        return destinationService.findById(destinationId);
    }

    @PutMapping("/{destinationId}")
    DestinationDTO replace(@RequestBody DestinationDTO destinationDTO, @PathVariable Long destinationId) {
        return destinationService.replace(destinationDTO, destinationId);
    }

    @DeleteMapping("/{destinationId}")
    ResponseEntity<Object> deleteById(@PathVariable Long destinationId) {
        destinationService.deleteById(destinationId);

        return ResponseEntity.noContent().build();
    }
}
