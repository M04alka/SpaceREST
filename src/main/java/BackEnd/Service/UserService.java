package BackEnd.Service;

import BackEnd.Dao.RoleRepository;
import BackEnd.Dao.UserRepository;
import BackEnd.Model.Roles;
import BackEnd.Model.Users;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExists(String username) {
        return userRepository.findByLogin(username).isPresent();
    }

    public void createUser(Users user, Roles.RoleName ...roles) {
        Set<String> roleNames = Arrays.stream(roles).map(Roles.RoleName::asRoleNameString).collect(Collectors.toSet());

        if (roleNames.isEmpty()) {
            roleNames.add(Roles.RoleName.USER.asRoleNameString());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findAllByNameIn(roleNames));

        userRepository.save(user);
    }

    public void createRole(Roles.RoleName roleName) {
        roleRepository.save(
                Roles.builder()
                        .name(roleName.asRoleNameString())
                        .build()
        );
    }

    public UserDetails get(String username) {
        return null;
    }
}