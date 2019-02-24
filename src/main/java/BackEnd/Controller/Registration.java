package BackEnd.Controller;

import BackEnd.Model.Roles;
import BackEnd.Model.Users;
import BackEnd.Service.SecurityService;
import BackEnd.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://54.37.125.178", allowCredentials = "true")
public class Registration {
    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public Registration(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Users userForm) {
        if (!userService.userExists(userForm.getLogin())) {
            userService.createUser(userForm, Roles.RoleName.USER);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
        else return new ResponseEntity<String>("User is Exist",HttpStatus.CONFLICT);
    }
}
