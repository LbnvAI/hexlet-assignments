package exercise.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter

// UserDetails -
// Определяет обязательные методы для работы с данными пользователя в Spring Security
public class User implements BaseEntity, UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String passwordDigest;

    @CreatedDate
    private LocalDate createdAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    // Метод интерфейса UserDetails
    // Возвращает хэшированную версию пароля, сам объект хранит также только хэш.
    @Override
    public String getPassword() {
        return passwordDigest;
    }

    // Метод интерфейса UserDetails
    // Возвращает значение поля, которе используется, как уникальный идентификатор, в данном случае email
    @Override
    public String getUsername() {
        return email;
    }

    // Методы интерфейса UserDetails
    // Этот метод должен возвращать коллекцию разрешений (ролей или прав) пользователя
    // В текущей реализации возвращается пустая коллекция, что означает отсутствие специальных прав доступа
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

    // Все эти методы - это также методы интерфейса UserDetails,
    // возвращают boolean значение, показывающее:
    // 1. Пользователь активен или нет
    // 2. Ограничено ли время жизни пользователя или нет
    // 3. Пользователь заблокирован или нет
    // 4. Учетные данные требуют обновления или нет
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
