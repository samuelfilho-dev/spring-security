package dio.spring.security.jwt.controller.dto;

import lombok.Data;

@Data
public class Sessao {
    private String login;
    private String token;
}
