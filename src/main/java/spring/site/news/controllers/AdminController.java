package spring.site.news.controllers;
import spring.site.news.services.CategoryService;
import spring.site.news.services.FileStorageService;
import spring.site.news.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    private final CategoryService categoryService;

    private final FileStorageService fileStorageService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/posts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String posts(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("posts", categoryService.getAllCategories());
        return "admin/posts";
    }

    @GetMapping("/categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String courses(Model model) {
        model.addAttribute("courses", categoryService.getAllCategories());
        return "admin/categories";
    }

    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/categories/edit/{id}")
    public String editCourse(@PathVariable(name = "id") Long id, Model model) throws NoHandlerFoundException {
        if (categoryService.getCategoryById(id) != null) {
            model.addAttribute("course", categoryService.getCategoryById(id));
            return "admin/edit/course";
        } else {
            throw new NoHandlerFoundException("GET", "/courses/" + id + "/edit", null);
        }
    }
}
