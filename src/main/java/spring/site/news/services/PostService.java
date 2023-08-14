package spring.site.news.services;

import spring.site.news.dto.CategoryDTO;
import spring.site.news.dto.PostDTO;
import spring.site.news.mapper.PostMapper;
import spring.site.news.models.Post;
import spring.site.news.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPostsByCategoryId(Long courseId) {
        return postRepository.findAllByCategoryId(courseId);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<PostDTO> getAllCategoriesDTO() {
        return postMapper.toPostDTOList(postRepository.findAll());
    }
}
