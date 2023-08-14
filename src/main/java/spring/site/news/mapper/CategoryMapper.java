package spring.site.news.mapper;

import spring.site.news.dto.CategoryDTO;
import spring.site.news.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    CategoryDTO toCourseDTO(Category category);

    CategoryDTO toEntityCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> toCategoryDTOList(List<Category> cours);

    List<Category> toEntityCategoryList(List<CategoryDTO> categoryDTOS);
}
