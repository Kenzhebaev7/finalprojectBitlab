package spring.site.news.controllers.RestControllers;

import spring.site.news.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.site.news.dto.PostDTO;
import spring.site.news.services.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> deleteCourse(@PathVariable("id") Long postId) {
        try {
            postService.deletePostById(postId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
