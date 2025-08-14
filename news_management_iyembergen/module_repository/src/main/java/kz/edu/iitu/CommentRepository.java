package kz.edu.iitu;

import kz.edu.iitu.models.Comment;
import kz.edu.iitu.models.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long>, PagingAndSortingRepository<Comment, Long> {
    public List<Comment> findByNews(News news);
}
