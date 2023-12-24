package ru.carsalon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class UserInfo implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_GEN")
    @SequenceGenerator(name = "User_GEN", sequenceName = "User_SEQ", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull
    @NotEmpty
    private String password;

    @OneToOne(fetch = FetchType.EAGER)

//    @Column(name = "trader", nullable = true, unique = true)
//    @NotNull
    private Trader trader;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Set<RoleInfo> roles = new HashSet<>();

    public List<RoleType> getEnumRoles() {
        return roles.stream().map(RoleInfo::getName).toList();
    }

    public boolean isAdmin() {
        return getEnumRoles().contains(RoleType.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
