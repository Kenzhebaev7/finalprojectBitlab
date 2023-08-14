package spring.site.news.controllers;

import org.springframework.web.multipart.MultipartFile;
import spring.site.news.models.Category;
import spring.site.news.models.Post;
import spring.site.news.services.CategoryService;
import spring.site.news.services.FileStorageService;
import spring.site.news.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    @Value("${posts.images.path}")
    private String postsImagePath;

    private final PostService postService;

    private final CategoryService categoryService;

    private final FileStorageService fileStorageService;

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @GetMapping("/create")
    public String createPost(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories/post-create";
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PostMapping("/store")
    public String addPost(@RequestParam(name = "name") String name,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "image") MultipartFile image,
                            @RequestParam(name = "category_id") Long categoryId) {
        try {
            Category category = categoryService.getCategoryById(categoryId);

            if (category == null) {
                return "redirect:/course" + categoryId + "/?error=course_not_found";
            }

            Post post = new Post();

            post.setName(name);
            post.setDescription(description);
            post.setCategory(category);

            if (!image.isEmpty()) {
                String fileName = fileStorageService.saveFile(image, postsImagePath);
                post.setImage(fileName);
            }

            postService.savePost(post);
            category.getPosts().add(post);
            categoryService.saveCategory(category);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        List<Category> categories = categoryService.getAllCategories();
        Category category = categoryService.getCategoryById(post.getCategory().getId());
        List<Post> posts = postService.getAllPostsByCategoryId(category.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categories);
        model.addAttribute("post", post);
        return "posts/single-post";
    }
}
