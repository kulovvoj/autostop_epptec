package eu.epptec.autostop;

import eu.epptec.autostop.dtos.UserDTO;
import eu.epptec.autostop.model.User;
import eu.epptec.autostop.repositories.UserRepository;
import eu.epptec.autostop.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockTest {
    @InjectMocks
    public UserServiceImpl userService;

    @Mock
    public UserRepository userRepository;

    // Need to find out why this is recommended
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_usingMock() {
        User testUser = new User(1L, "V", "K", "v.k@seznam.cz", "123456789");
        UserDTO expectedUserDTO = new UserDTO(1L, "V", "K", "v.k@seznam.cz", "123456789");

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));

        UserDTO userDTO = userService.findById(testUser.getId());

        Assert.assertNotNull(userDTO);
        Assert.assertEquals(expectedUserDTO, userDTO);
    }

}
