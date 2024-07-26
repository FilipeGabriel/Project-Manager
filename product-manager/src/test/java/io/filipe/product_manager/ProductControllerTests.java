package io.filipe.product_manager;

import io.filipe.product_manager.entities.Product;
import io.filipe.product_manager.entities.User;
import io.filipe.product_manager.repositories.ProductRepository;
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
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    private final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjIwMTQ0MTQsInVzZXJfbmFtZSI6ImZpbGlwZSIsImp0aSI6ImRjYmY0ZTNhLWI4ZTItNDUwNi04ODI5LWMxNzZlMzcyNGQyNyIsImNsaWVudF9pZCI6Im15YXBwbmFtZTEyMyIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.Lyk8EioeB5m8vSHboBh8xOvpRFjTbi19EE7rMHrv5L0";

    @BeforeEach
    public void setUp() {
        Product product = new Product();
        product.setId(2L);
        product.setName("television");
        product.setPrice(1500.00);
        product.setQuantity(100);

        Product updatedProduct = new Product();
        updatedProduct.setId(2L);
        updatedProduct.setName("television");
        updatedProduct.setPrice(2500.00);
        updatedProduct.setQuantity(200);


        Mockito.when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        Mockito.when(productRepository.findById(2L)).thenReturn(java.util.Optional.of(product));

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Mockito.when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Mockito.when(productRepository.existsById(2L)).thenReturn(true);
        Mockito.doNothing().when(productRepository).deleteById(2L);

        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(2L))
                .andExpect(jsonPath("$[0].name").value("television"))
                .andExpect(jsonPath("$[0].price").value(1500.00))
                .andExpect(jsonPath("$[0].quantity").value(100));
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/2")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("television"))
                .andExpect(jsonPath("$.price").value(1500.00))
                .andExpect(jsonPath("$.quantity").value(100));
    }

    @Test
    public void findByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void insert() throws Exception {
        String productJson = "{\"name\":\"television\",\"price\":1500.00,\"quantity\":100}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/2")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void update() throws Exception {
        String updatedProductJson = "{\"name\":\"television\",\"price\":2500.00,\"quantity\":200}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/2")
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("television"))
                .andExpect(jsonPath("$.price").value(2500.00))
                .andExpect(jsonPath("$.quantity").value(200));
    }


}
