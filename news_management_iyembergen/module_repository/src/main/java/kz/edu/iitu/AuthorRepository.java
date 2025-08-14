package kz.edu.iitu;

import kz.edu.iitu.models.Author;
import kz.edu.iitu.models.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long>, PagingAndSortingRepository<Author, Long> {
    List<Author> findByName(String name);
}
