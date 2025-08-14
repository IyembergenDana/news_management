package kz.edu.iitu.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String title;
    public String content;

    public LocalDateTime createDate;

    public LocalDateTime lastUpdateDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToMany
    @JoinTable(
            name = "news_tags",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    Set<Tag> tags = new HashSet<Tag>();
    public News(String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, Author author) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.author = author;
    }
}
