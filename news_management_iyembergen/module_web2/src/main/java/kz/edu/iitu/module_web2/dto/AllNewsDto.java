package kz.edu.iitu.module_web2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AllNewsDto {
    private List<NewsDto> items;
}
