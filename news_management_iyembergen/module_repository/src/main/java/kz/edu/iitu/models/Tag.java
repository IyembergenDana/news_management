package kz.edu.iitu.models;


import jakarta.persistence.*;
import kz.edu.iitu.TagRepository;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<News> news = new HashSet<>();
    public Tag(String name) {
        this.name = name;
    }
}
