package dio.spring.security.init;

import dio.spring.security.model.User;
import dio.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StrartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user = userRepository.findByUsername("admin");
            if (user == null){
                user = new User();
                user.setName("ADMIN");
                user.setUsername("admin");
                user.setPassword("master123");
                user.getRoles().add("MANAGERS");

                userRepository.save(user);
            }

        user = userRepository.findByUsername("user");
        if (user == null){
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("user123");
            user.getRoles().add("USERS");

            userRepository.save(user);
        }
    }
}
