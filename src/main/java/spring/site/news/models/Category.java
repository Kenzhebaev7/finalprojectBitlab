package spring.site.news.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_categories")
@Getter
@Setter
public class Category extends BaseModel{

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany
    private List<Post> posts;


}
