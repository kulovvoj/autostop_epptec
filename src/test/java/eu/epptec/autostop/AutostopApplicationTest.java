package eu.epptec.autostop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epptec.autostop.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutostopApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    void testAddUser() throws URISyntaxException {
        String baseUrl = "http://localhost:" + randomServerPort + "/users";
        URI uri = new URI(baseUrl);
        User user = new User("Vojtěch", "Kulovaný", "vojtech.kulovany@seznam.cz", "608626271");

        HttpEntity<User> request = new HttpEntity<>(user);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        System.out.println(result);

        Assert.assertEquals(200, result.getStatusCodeValue());

        ObjectMapper objectMapper = new ObjectMapper();
        EntityModel<User> userEntityModel = null;
        try {
            userEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<EntityModel<User>>(){});
            System.out.println("Test");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert userEntityModel != null;
        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId();
        this.restTemplate.delete(baseUrl, null, String.class);

    }
}