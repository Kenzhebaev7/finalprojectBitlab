package spring.site.news.controllers.RestControllers;

import spring.site.news.dto.CategoryDTO;
import spring.site.news.dto.PostDTO;
import spring.site.news.dto.UserDTO;
import spring.site.news.services.CategoryService;
import spring.site.news.services.PostService;
import spring.site.news.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;

    private final CategoryService categoryService;

    private final PostService postService;

    @PostMapping("/users/toggle/ban")
    public ResponseEntity<?> banUser(@RequestParam(name = "user_id") Long userId,
                                           @RequestParam(name = "ban") Boolean ban) {
        try {
            userService.toggleBan(userId, ban);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsersDTO());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCourses() {
        return ResponseEntity.ok(categoryService.getAllCategoriesDTO());
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllCategoriesDTO());
    }


}
