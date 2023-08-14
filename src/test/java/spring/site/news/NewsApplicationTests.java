package spring.site.news;

import spring.site.news.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApp {

	@Autowired
	private CategoryService categoryService;

	@Test
	void contextLoads() {
	}

}
