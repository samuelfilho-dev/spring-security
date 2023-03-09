package dio.spring.security.jwt.config;

import lombok.Data;


// Criar a Chave Criptografada Do Token

@Data
public class SecurityConfig {
    public static String PREFIX = "Bearer";
    public static String KEY = "SECRET_KEY";
    public static Long EXPIRATION = 3600000L;

}
