package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> findUser(int id) {
        return userRepository.findById(id);
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(int id, User upd) {
        return userRepository.findById(id) // 1. Ищем пользователя по ID
                .flatMap(existingUser -> { // 2. Если найден, обновляем данные
                    existingUser.setFirstName(upd.getFirstName());
                    existingUser.setLastName(upd.getLastName());
                    existingUser.setEmail(upd.getEmail());
                    return userRepository.save(existingUser); // 3. Сохраняем обновленного пользователя
                })
                .switchIfEmpty(Mono.error(new RuntimeException("User not found"))); // 4. Если пользователь не найден, выдаем ошибку
    }

    public Mono<Void> delete(int id) {
        return userRepository.deleteById(id);
    }
    // END
}
