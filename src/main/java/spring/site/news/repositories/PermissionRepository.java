package spring.site.news.repositories;

import spring.site.news.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByRole(String role);
}
