package eu.epptec.autostop;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epptec.autostop.controllers.UserController;
import eu.epptec.autostop.dtos.UserDTO;
import eu.epptec.autostop.exceptions.UserNotFoundException;
import eu.epptec.autostop.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;


    @Before
    public void init() {
        UserDTO userDTOInput = new UserDTO(null, "dala", "Vojtěch", "Kulovaný", "v.k@seznam.cz", "123456789");
        UserDTO userDTOOutput = userDTOInput;
        userDTOOutput.setId(1L);

        when(userService.save(userDTOInput)).thenReturn(userDTOOutput);
        doThrow(new UserNotFoundException()).when(userService).deleteById(3L);
        doNothing().when(userService).deleteById(2L);
    }

    @Test
    public void addUserTest() throws Exception {
        UserDTO userDTO = new UserDTO(null, "dala", "Vojtěch", "Kulovaný", "v.k@seznam.cz", "123456789");

        mockMvc.perform(post("/users")
                .content(new ObjectMapper().writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteUserTest() throws Exception {
        UserDTO userDTO = new UserDTO(null, "dala", "Vojtěch", "Kulovaný", "v.k@seznam.cz", "123456789");

        mockMvc.perform(delete("/users/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(delete("/users/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
