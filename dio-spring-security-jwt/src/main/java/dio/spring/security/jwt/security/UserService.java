package dio.spring.security.jwt.security;

import dio.spring.security.jwt.model.User;
import dio.spring.security.jwt.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user){
        String pass = user.getPassword();

        // Cripografando antes de Salvar no Banco De Dados

        user.setPassword(passwordEncoder.encode(pass));
        userRespository.save(user);
    }

}
