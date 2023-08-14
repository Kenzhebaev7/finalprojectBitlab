package spring.site.news.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.site.news.dto.PostDTO;
import spring.site.news.models.Post;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    PostDTO toPostDTO(Post post);

    PostDTO toEntityPost(PostDTO postDTO);

    List<PostDTO> toPostDTOList(List<Post> posts);

    List<Post> toEntityPostList(List<PostDTO> postDTOS);
}
