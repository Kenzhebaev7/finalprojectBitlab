package spring.site.news.repositories;

import jakarta.transaction.Transactional;
import spring.site.news.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByCategoryId(Long categoryId);
}
