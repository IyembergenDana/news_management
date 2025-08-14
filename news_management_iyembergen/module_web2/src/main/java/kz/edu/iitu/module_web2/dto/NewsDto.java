package kz.edu.iitu.module_web2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class NewsDto {
    public long id;
    public String title;
    public String content;
    public LocalDateTime createDate;
    public LocalDateTime lastUpdateDate;
    public AuthorDto author;
    public List<TagDto> tags;
}
