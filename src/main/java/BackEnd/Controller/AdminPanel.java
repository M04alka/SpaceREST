package BackEnd.Controller;

import BackEnd.DTO.ArticlesDto;
import BackEnd.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminPanel {
    @Autowired
    AdminService adminService;

    @PostMapping("/addCatigory/{nameCategory}")
    public ResponseEntity<String> addNewCategory(@NotNull @PathVariable String nameCategory){
       return adminService.addNewCategory(nameCategory);
    }

    @PostMapping("/removeCatigory/{nameCategory}")
    public ResponseEntity<String> removeCategory(@NotNull @PathVariable String nameCategory){
        return adminService.removeCategory(nameCategory);
    }

    @PostMapping("/removeArticle/{nameTitle}")
    public ResponseEntity<String> removeTitle(@NotNull @PathVariable String nameTitle){
        return adminService.removeArticle(nameTitle);
    }
    @PostMapping("/addTitle")
    public ResponseEntity<String> addArticle(@NotNull @RequestBody ArticlesDto articlesDto){
        return adminService.addArticle(articlesDto);
    }

}
