package io.filipe.product_manager;

import io.filipe.product_manager.entities.Product;
import io.filipe.product_manager.entities.User;
import io.filipe.product_manager.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjQ5NDU4NTksInVzZXJfbmFtZSI6ImZpbGlwZSIsImp0aSI6IjljOWJhNjgzLWE2Y2UtNGRkZi1iZjVmLWEwNTA1MDJjMDNmOCIsImNsaWVudF9pZCI6Im15YXBwbmFtZTEyMyIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.1cIc4PZVJAOGsuujN50SncCqpGbgBZXNagU1EAxV-gI";

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(5L);
        user.setUsername("filipe");
        user.setPassword("12345678");

        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        Mockito.when(userRepository.findById(5L)).thenReturn(java.util.Optional.of(user));

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Mockito.when(userRepository.existsById(2L)).thenReturn(true);
        Mockito.doNothing().when(userRepository).deleteById(2L);


    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(5L))
                .andExpect(jsonPath("$[0].username").value("filipe"))
                .andExpect(jsonPath("$[0].password").value("12345678"));
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/5")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.username").value("filipe"))
                .andExpect(jsonPath("$.password").value("12345678"));
    }

    @Test
    public void findByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                        .header("Authorization", "Bearer " + BEARER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void insert() throws Exception {
        String userJson = "{\"username\":\"filipe\",\"password\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/2")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
