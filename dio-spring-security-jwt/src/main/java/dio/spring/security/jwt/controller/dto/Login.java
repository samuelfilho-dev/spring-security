package dio.spring.security.jwt.controller.dto;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;

}
