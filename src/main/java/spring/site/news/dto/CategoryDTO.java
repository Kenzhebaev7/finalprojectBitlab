package spring.site.news.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CategoryDTO {

    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String name;

}
