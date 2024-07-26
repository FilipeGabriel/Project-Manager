package io.filipe.product_manager.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private String name;

    private Integer quantity;

    private Double price;

}
