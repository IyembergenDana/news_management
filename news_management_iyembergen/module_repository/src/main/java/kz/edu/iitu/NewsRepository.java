package kz.edu.iitu;

import kz.edu.iitu.models.Author;
import kz.edu.iitu.models.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long>, PagingAndSortingRepository<News, Long> {
    public List<News> findByAuthor(Author author);
}
