package com.ansar.trainingcourse.model.service;

import com.ansar.trainingcourse.model.repository.UserRepository;
import com.ansar.trainingcourse.dto.RegistrationUserDTO;
import com.ansar.trainingcourse.dto.UpdateUserDTO;
import com.ansar.trainingcourse.model.entity.Authority;
import com.ansar.trainingcourse.model.entity.User;
import com.ansar.trainingcourse.util.exception.UsernameNotUniqueException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Log4j2
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private UUID uuid;


    public UserService(UserRepository userRepository,
                       MessageSource messageSource,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    public Page<User> findAllUsersPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid user id: " + id));
    }

//    number generetic
//    public class RandomNumbers {
//        Random objGenerator = new Random();
//         (int iCount = 0; iCount< 10; iCount++){
//        int randomNumber = objGenerator.nextInt(100);
//        System.out.println("Random No : " + randomNumber);
//    }
//
//}
//    public class RandomNumberExample3{
//    Random random = new Random();
//    int x = random.nextInt(50);
//    int y = random.nextInt(1000);
//}


    private Random rand = new Random();

    public void createUser(RegistrationUserDTO userDTO) {
        User user = User
                .builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .uuid(UUID.randomUUID().toString().replace("-","").substring(0,13))
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .enabled(true)
                .code(rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+""+rand.nextInt(9)+"")
                .authorities(Collections.singleton(Authority.USER))
                .build();
        try {
            userRepository.save(user);
            log.info("New user " + user);
        } catch (DataIntegrityViolationException e) {
            log.error("Login not unique: " + userDTO.getUsername());
            throw new UsernameNotUniqueException(messageSource.getMessage(
                    "users.registration.login.not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + userDTO.getUsername(), e);
        }
    }

    public void deleteUser(long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public void updateUser(long id, UpdateUserDTO userDTO) {
        User user = getUserById(id);

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        if (Objects.nonNull(userDTO.getPassword()) && userDTO.getPassword().length() > 0) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        user.setAuthorities(userDTO.getAuthorities());
        user.setRooms(userDTO.getRooms());
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error("Login not unique: " + userDTO.getUsername());
            throw new UsernameNotUniqueException(messageSource.getMessage(
                    "users.registration.login.not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + userDTO.getUsername(), e);
        }
    }

}
