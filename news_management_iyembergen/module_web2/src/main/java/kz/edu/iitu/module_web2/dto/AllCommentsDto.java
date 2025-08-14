package kz.edu.iitu.module_web2.dto;

import kz.edu.iitu.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AllCommentsDto {
    private List<CommentDto> items;
}
