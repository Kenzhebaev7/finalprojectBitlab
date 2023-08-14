package spring.site.news.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_posts")
@Getter
@Setter
public class Post extends BaseModel{

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "is_active")
    private Boolean isActive;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public String loadImage(){
        if (image == null || image.isEmpty()) {
            return "/defaults/default.png";
        }
        return image;
    }
}
