package spring.site.news.repositories;

import jakarta.transaction.Transactional;
import spring.site.news.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
