package BackEnd.Controller;

import BackEnd.DTO.UserInfo;
import BackEnd.Dao.UsersRep;
import BackEnd.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userinfo")
public class Controller {
    @Autowired
    UsersRep UsersRep;
    @Autowired
    UserService userService;
    @GetMapping("/get/{firstname}/{lastname}")
    public String  get(@PathVariable String firstname,@PathVariable String lastname) {
      return userService.findUser(firstname,lastname);
    }
    @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo) throws Exception{
        return userService.add(userInfo);
    }

}
