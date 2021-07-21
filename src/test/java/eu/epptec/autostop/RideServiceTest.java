package eu.epptec.autostop;

import eu.epptec.autostop.dtos.RideDTO;
import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.repositories.CarRepository;
import eu.epptec.autostop.repositories.RideRepository;
import eu.epptec.autostop.services.RideServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RideServiceTest {
    @Mock
    RideRepository rideRepository;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    RideServiceImpl rideService;

    @Captor
    ArgumentCaptor<Ride> rideCaptor;

    @Test
    public void testSave_RideEntity_usingCaptor() {
        RideDTO rideDTO = new RideDTO(null, 2);
        Car car = new Car(2L, true, "Corvette", "C3", "Two-seater", 1973, 2, null);
        car.setRides(null);
        Ride ride = new Ride(null, 2, null, car);
        Ride rideExpected = new Ride(1L, 2, null, car);
        Long carId = 3L;

        when(carRepository.getById(car.getId())).thenReturn(car);
        when(rideRepository.save(ride)).thenReturn(rideExpected);

        rideService.save(rideDTO, car.getId());

        // Checks whether save method was used and captures the ride argument
        verify(rideRepository).save(rideCaptor.capture());

        // Verifies, that method getById with any long as argument was never called
        verify(rideRepository, never()).getById(anyLong());

        // Verifies if there were any more interactions other than the first save
        verifyNoMoreInteractions(rideRepository);

        Ride rideCaptorValue = rideCaptor.getValue();
        assertEquals(ride, rideCaptorValue);
    }
}
