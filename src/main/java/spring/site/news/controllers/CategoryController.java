package spring.site.news.controllers;

import spring.site.news.models.Category;
import spring.site.news.services.CategoryService;
import spring.site.news.services.PostService;
import spring.site.news.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RequestMapping("/categories")
@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final PostService postService;

    @GetMapping
    public String posts(Model model) {
        model.addAttribute("posts", categoryService.getAllCategories());
        return "categories/index";
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PostMapping("/store")
    public String addCourse(@RequestParam(name = "name") String name){
        try {
            Category category = new Category();
            category.setName(name);

            Category newCategory = categoryService.saveCategory(category);

            if (newCategory != null) {
                return "redirect:/";
            } else {
                return "redirect:/categories?error";
            }

        } catch (Exception e) {
            return "redirect:/error?error";
        }

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String course(@PathVariable Long id, Model model) throws NoHandlerFoundException {
        if (categoryService.getCategoryById(id) != null) {
            model.addAttribute("category", categoryService.getCategoryById(id));
            model.addAttribute("posts", postService.getAllPostsByCategoryId(id));
            return "categories/single-category";
        } else {
            throw new NoHandlerFoundException("GET", "/categories/" + id, null);
        }
    }


    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update")
    public String updateCourse(@RequestParam(name = "course_id") Long id,
                               @RequestParam(name = "name") String name) {
        try {
            Category category = categoryService.getCategoryById(id);
            category.setName(name);

            Category updatedCategory = categoryService.saveCategory(category);

            if (updatedCategory != null) {
                return "redirect:/courses?success";
            } else {
                return "redirect:/courses?error";
            }

        } catch (Exception e) {
            return "redirect:/error?error";
        }
    }

}
