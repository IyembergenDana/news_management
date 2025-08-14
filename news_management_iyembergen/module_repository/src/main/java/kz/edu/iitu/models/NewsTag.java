package kz.edu.iitu.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}
