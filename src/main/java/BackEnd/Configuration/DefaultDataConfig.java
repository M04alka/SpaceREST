package BackEnd.Configuration;

import BackEnd.Model.Users;
import BackEnd.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static BackEnd.Model.Roles.RoleName.*;

@Component
public class DefaultDataConfig implements CommandLineRunner {

    private final UserService userService;

    public DefaultDataConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        createDefaultRoles();
        createDefaultUsers();
    }

    private void createDefaultRoles() {
        Arrays.stream(values())
                .forEach(userService::createRole);
    }

    private void createDefaultUsers() {
        Arrays.asList(
                Users.builder().login("john").password("123").build()
        ).forEach(x->userService.createUser(x));


        userService.createUser(
                Users.builder().login("admin").password("admin").build(),
                ADMIN
        );

        userService.createUser(
                Users.builder().login("bill").password("bill.the.butcher").build(),
                MODERATOR
        );

        userService.createUser(
                Users.builder().login("umoderator").password("12345").build(),
                MODERATOR, USER
        );
    }

}