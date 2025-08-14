package kz.edu.iitu.module_web2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentDto {
    public Long id;
    public String content;
    public LocalDateTime createDate;
    public LocalDateTime lastUpdateDate;
    public NewsDto news;
}
