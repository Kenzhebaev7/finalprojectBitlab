package spring.site.news.controllers;

import spring.site.news.models.Post;
import spring.site.news.models.User;
import spring.site.news.services.PermissionService;
import spring.site.news.services.PostService;
import spring.site.news.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PermissionService permissionService;
    private final PostService postService;

    @GetMapping("/")
    public String index(Model model){
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-in")
    public String signIn(){
        return "auth/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-up")
    public String signUp(){
        return "auth/register";
    }

    @PostMapping("/to-sign-up")
    public String toSignUp(@RequestParam(name = "user_firstname") String firstName,
                           @RequestParam(name = "user_lastname") String lastName,
                           @RequestParam(name = "user_username") String username,
                           @RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_repeat_password") String repeat_password){
        if (password.equals(repeat_password)){
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            user.setIsPremium(false);
            user.setPassword(password);
            user.setIsBanned(false);
            User newUser = userService.addUser(user);

            if (newUser != null){
                newUser.getPermissions().add(permissionService.userRolePermission());
                return "redirect:/sign-in?success";
            }
            else{
                return "redirect:/sign-up?error";
            }
        }
        else{
            return "redirect:/sign-up?passwords_mismatch";
        }
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }

}
