package dio.spring.security.jwt.security;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JWTObject {
    private String subject; // Nome De Usuario
    private Date issuerdAt; // Data De Criação Do Token
    private Date expiration; // Data De Expiração Do Token
    private List<String> roles; // Perfis De Acesso

}
