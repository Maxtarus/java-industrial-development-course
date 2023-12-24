package ru.carsalon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "roles")
public class RoleInfo implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_GEN")
    @SequenceGenerator(name = "Role_GEN", sequenceName = "Role_SEQ", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private RoleType name;

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }

}
