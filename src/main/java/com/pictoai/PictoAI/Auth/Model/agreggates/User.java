package com.pictoai.PictoAI.Auth.Model.agreggates;

import com.pictoai.PictoAI.Auth.Model.enums.UserRol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import static net.sf.jsqlparser.util.validation.metadata.NamedObject.role;

@Getter
@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails { // implementamos userdetails pq sino no funca la autenticacion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRol role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return email;
    } // retorna el email

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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
