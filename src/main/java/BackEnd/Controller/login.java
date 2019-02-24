package BackEnd.Controller;

import BackEnd.Model.Users;
import BackEnd.Service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://54.37.125.178", allowCredentials = "true")
public class login {
    @Autowired
    SecurityService securityService;
    @PostMapping
    public ResponseEntity<String> Sign_in(@RequestBody Users userForm) {
        return securityService.loginUserAs(userForm.getLogin(), userForm.getPassword());
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
            return new ResponseEntity<String>(HttpStatus.OK);
    }
}
