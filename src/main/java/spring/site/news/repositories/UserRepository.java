package spring.site.news.repositories;

import jakarta.transaction.Transactional;
import spring.site.news.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);


}
