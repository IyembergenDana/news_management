package kz.edu.iitu;

import jakarta.transaction.Transactional;
import kz.edu.iitu.exceptions.AuthorNotFoundException;
import kz.edu.iitu.exceptions.CommentNotFoundException;
import kz.edu.iitu.exceptions.NewsNotFoundException;
import kz.edu.iitu.models.Author;
import kz.edu.iitu.models.Comment;
import kz.edu.iitu.models.News;
import kz.edu.iitu.models.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequiredArgsConstructor
public class NewsService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CommentRepository commentRepository;

    //AUTHORS
    @Transactional
    public Author createAuthor(String name){
        Author author = new Author(name);
        return authorRepository.save(author);
    }
    @Transactional
    public Optional<Author> getAuthorById(long id) {
        return authorRepository.findById(id);
    }
    @Transactional
    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name).stream().findFirst();
    }
    @Transactional
    public Optional<Author> deleteAuthor(long id){
        Optional<Author> author = getAuthorById(id);
        if (author.isPresent()) {
            authorRepository.deleteById(id);
        }
        return author;
    }
    @Transactional
    public Optional<Author> updateAuthor(long id, String name) {
        Optional<Author> author = getAuthorById(id);
        if (author.isPresent()) {
            author.get().setName(name);
            authorRepository.save(author.get());
        }
        return author;
    }
    @Transactional
    public List<Author> getAllAuthors() {
        List<Author> res = new ArrayList<>();
        authorRepository.findAll().forEach(res::add);
        return res;
    }


    // NEWS
    @Transactional
    public News createNews(String title, String content, long authorId){
        LocalDateTime now = LocalDateTime.now();
        Optional<Author> author = getAuthorById(authorId);
        if (author.isPresent()) {
            News news = new News(title, content, now, now, author.get());
            return newsRepository.save(news);
        }
        throw new AuthorNotFoundException();
    }
    @Transactional
    public Optional<News> getNewsById(long id) {
        return newsRepository.findById(id);
    }
    public Optional<News> updateNews(long id, String title, String content, long authorId){
        Optional<News> news = getNewsById(id);
        if (!news.isPresent()) {
            throw new NewsNotFoundException();
        }
        Optional<Author> author = getAuthorById(authorId);
        if (!author.isPresent()) {
            throw new AuthorNotFoundException();
        }
        news.get().setTitle(title);
        news.get().setContent(content);
        news.get().setAuthor(author.get());
        news.get().setLastUpdateDate(LocalDateTime.now());
        newsRepository.save(news.get());
        return news;
    }
    public Optional<News> deleteNews(long id){
        Optional<News> news = getNewsById(id);
        if (news.isPresent()) {
            newsRepository.deleteById(id);
        }
        return news;
    }
    @Transactional
    public List<News> getAllNews(String sortBy, String direction) {
        if (sortBy == null || sortBy.isEmpty()) sortBy = "createDate";
        if (direction == null || direction.isEmpty()) direction = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        System.out.println("Sorting by: " + sort.toString());
        return (List<News>) newsRepository.findAll(sort);
    }
    public List<News> getNewsByAuthorId(long authorId) {
        Optional<Author> author = getAuthorById(authorId);
        if (!author.isPresent()) {
            throw new AuthorNotFoundException();
        }
        return newsRepository.findByAuthor(author.get());
    }
    public List<News> getNewsByAuthorName(String authorName) {
        Optional<Author> author = getAuthorByName(authorName);
        if (!author.isPresent()) {
            throw new AuthorNotFoundException();
        }
        return newsRepository.findByAuthor(author.get());
    }

    //TAGS
    @Transactional
    public Tag createTag (String name){
        Tag tag = new Tag(name);
        return tagRepository.save(tag);
    }
    @Transactional
    public List<Tag> getAllTags() {
        List<Tag> res = new ArrayList<>();
        tagRepository.findAll().forEach(res::add);
        return res;
    }
    @Transactional
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name).stream().findFirst();
    }
    @Transactional
    public Optional<Tag> getTagById(long id) {
        return tagRepository.findById(id);
    }
    @Transactional
    public News addTag(long id, String tagName) {
        Optional<News> newsO = getNewsById(id);
        if (!newsO.isPresent()) {
            throw new NewsNotFoundException();
        }
        News news = newsO.get();
        Optional<Tag> tagO = getTagByName(tagName);
        Tag tag;
        if (tagO.isPresent()) {
            tag = tagO.get();
        }
        else {
            tag = createTag(tagName);
        }
        news.getTags().add(tag);
        newsRepository.save(news);
        return news;
    }
    @Transactional
    public Optional<Tag> deleteTag(long id){
        Optional<Tag> tag = getTagById(id);
        if (tag.isPresent()) {
            tagRepository.deleteById(id);
        }
        return tag;
    }
    @Transactional
    public Optional<Tag> updateTag(long id, String name) {
        Optional<Tag> tag = getTagById(id);
        if (tag.isPresent()) {
            tag.get().setName(name);
            tagRepository.save(tag.get());
        }
        return tag;
    }


    //COMMENTS
    @Transactional
    public Comment createComment(String content, long newsId) {
        Optional<News> news = getNewsById(newsId);
        if (news.isPresent()) {
           Comment comment = new Comment(content, LocalDateTime.now(), LocalDateTime.now(), news.get());
            return commentRepository.save(comment);
        }
        throw new CommentNotFoundException();
    }
    @Transactional
    public List<Comment> getAllComments(String sortBy, String direction) {
        if (sortBy == null || sortBy.isEmpty()) sortBy = "createDate";
        if (direction == null || direction.isEmpty()) direction = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        System.out.println("Sorting by: " + sort.toString());
        return (List<Comment>) commentRepository.findAll(sort);
    }
    @Transactional
    public Optional<Comment> getCommentById(long id) {
        return commentRepository.findById(id);
    }
    public List<Comment> getCommentByNews(long newsId) {
        Optional<News> news = getNewsById(newsId);
        if (!news.isPresent()) {
            throw new NewsNotFoundException();
        }
        return commentRepository.findByNews(news.get());
    }
    @Transactional
    public Optional<Comment> updateComment(long id, String content, long newsId){
        Optional<Comment> comment = getCommentById(id);
        if (!comment.isPresent()) {
            throw new CommentNotFoundException();
        }
        Optional<News> news = getNewsById(newsId);
        if (!news.isPresent()) {
            throw new NewsNotFoundException();
        }
        comment.get().setContent(content);
        comment.get().setNews(news.get());
        comment.get().setLastUpdateDate(LocalDateTime.now());
        commentRepository.save(comment.get());
        return comment;
    }
    public Optional<Comment> deleteComment(long id){
        Optional<Comment> comment = getCommentById(id);
        if (comment.isPresent()) {
            commentRepository.deleteById(id);
        }
        return comment;
    }
}
