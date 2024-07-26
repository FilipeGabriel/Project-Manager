package io.filipe.product_manager.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String password;

}
