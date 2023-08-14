package spring.site.news.mapper;

import spring.site.news.dto.UserDTO;
import spring.site.news.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    UserDTO toUserDTO(User user);

    User toEntityUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<User> users);

    List<User> toEntityUserList(List<UserDTO> userDTOS);
}