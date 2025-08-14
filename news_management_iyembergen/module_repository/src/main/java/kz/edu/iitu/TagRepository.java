package kz.edu.iitu;

import kz.edu.iitu.models.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findByName(String name);
}
