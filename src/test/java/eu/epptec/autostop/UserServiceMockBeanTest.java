package eu.epptec.autostop;

import eu.epptec.autostop.dtos.UserDTO;
import eu.epptec.autostop.model.User;
import eu.epptec.autostop.repositories.UserRepository;
import eu.epptec.autostop.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceMockBeanTest {
    @SpyBean
    public UserServiceImpl userService;

    @MockBean
    public UserRepository userRepository;

    @Autowired
    ApplicationContext context;
    // Need to find out why this is recommended
    @Before
    public void init() {
        userRepository = context.getBean(UserRepository.class);
    }

    @Test
    public void testFindById_usingMockBean() {
        User testUser = new User(1L, "V", "K", "v.k@seznam.cz", "123456789");
        UserDTO expectedUserDTO = new UserDTO(1L, "V", "K", "v.k@seznam.cz", "123456789");

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));

        UserDTO userDTO = userService.findById(testUser.getId());

        Assert.assertNotNull(userDTO);
        Assert.assertEquals(expectedUserDTO, userDTO);
    }
}
