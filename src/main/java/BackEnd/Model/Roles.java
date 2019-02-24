package BackEnd.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    @Transient
    RoleName roleName;

    public enum RoleName {
        ADMIN("ADMIN"),
        MODERATOR("MODERATOR"),
        USER("USER");

        private final String roleName;

        RoleName(String roleName) {
            this.roleName = roleName;
        }

        public String shorten() {
            return roleName;
        }

        public String asRoleNameString() {
            return "ROLE_" + roleName;
        }
    }
}