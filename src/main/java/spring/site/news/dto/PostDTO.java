package spring.site.news.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PostDTO {

    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String name;
    private String description;
    private String image;
    private Boolean isActive;


    public String getImage() {
        if (image == null){
            return "/defaults/avatar.png";
        }
        else {
            return image;
        }
    }
}
