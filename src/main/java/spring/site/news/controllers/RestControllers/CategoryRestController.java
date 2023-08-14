package spring.site.news.controllers.RestControllers;

import spring.site.news.dto.CategoryDTO;
import spring.site.news.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deleteCourse(@PathVariable("id") Long courseId) {
        try {
            categoryService.deleteCourseById(courseId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
