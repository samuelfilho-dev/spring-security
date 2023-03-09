package dio.spring.security.jwt.controller;

import dio.spring.security.jwt.config.SecurityConfig;
import dio.spring.security.jwt.controller.dto.Login;
import dio.spring.security.jwt.controller.dto.Sessao;
import dio.spring.security.jwt.model.User;
import dio.spring.security.jwt.repository.UserRespository;
import dio.spring.security.jwt.security.JWTCreator;
import dio.spring.security.jwt.security.JWTObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@Log4j2
public class LoginController {
    private final PasswordEncoder passwordEncoder;
    private final UserRespository userRespository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){

        User user = userRespository.findByUsername(login.getUsername());

        if (user != null) {
            boolean password0k = passwordEncoder.matches(login.getPassword(), user.getPassword());
            if (!password0k){
                throw new RuntimeException("Senha Invalida Para Login " + login.getUsername());
            }

            // Enviando o Objeto Sessão

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuerdAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());

            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());
            sessao.setToken(JWTCreator.createToken(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;

        }else{
            throw new RuntimeException("Erro Na Tentativa De Fazer Login");
        }
    }

}
