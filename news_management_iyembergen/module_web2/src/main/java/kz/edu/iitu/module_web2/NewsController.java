package kz.edu.iitu.module_web2;

import kz.edu.iitu.NewsService;
import kz.edu.iitu.exceptions.AuthorNotFoundException;
import kz.edu.iitu.exceptions.CommentNotFoundException;
import kz.edu.iitu.exceptions.NewsNotFoundException;
import kz.edu.iitu.exceptions.TagNotFoundException;
import kz.edu.iitu.models.Author;
import kz.edu.iitu.models.Comment;
import kz.edu.iitu.models.News;
import kz.edu.iitu.models.Tag;
import kz.edu.iitu.module_web2.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class NewsController {
    static private final String DEFAULT_SORT_BY = "create_date";
    static private final String DEFAULT_ORDER = "desc";
    static private final int DEFAULT_PAGE_SIZE = 10;

    private NewsService newsService;
    @GetMapping("/")
    public String s(){
        return "Hello!";
    }

    @PutMapping("/authors")
    public AuthorDto createAuthor(@RequestParam String name){
        Author author = newsService.createAuthor(name);
        return buildAuthorDto(author);
    }
    @GetMapping("/authors/{id}")
    private AuthorDto getAuthor(@PathVariable long id) {
        Optional<Author> author = newsService.getAuthorById(id);
        if (author.isPresent()) {
            return buildAuthorDto(author.get());
        }
        throw new AuthorNotFoundException();
    }
    @GetMapping("/authors")
    public AllAuthorsDto getAllAuthors() {
        return new AllAuthorsDto(newsService.getAllAuthors()
                .stream().map(this::buildAuthorDto).toList());
    }
    @DeleteMapping("/authors/{id}")
    public AuthorDto deleteAuthor(@PathVariable long id) {
        Optional<Author> author = newsService.deleteAuthor(id);
        if (author.isPresent()) {
            return buildAuthorDto(author.get());
        }
        throw new AuthorNotFoundException();
    }
    @PostMapping("/authors/{id}")
    public AuthorDto updateAuthor(@PathVariable long id, @RequestParam String newName) {
        Optional<Author> author = newsService.updateAuthor(id, newName);
        if (author.isPresent()) {
            return buildAuthorDto(author.get());
        }
        throw new AuthorNotFoundException();
    }


    @PutMapping("/news")
    public NewsDto createNews(@RequestParam String title, @RequestParam String content, @RequestParam long authorId){
        News news = newsService.createNews(title, content, authorId);
        return buildNewsDto(news);
    }
    @GetMapping("/news/byAuthorId")
    public AllNewsDto getNewsByAuthor(@RequestParam long authorId) {
        return new AllNewsDto(newsService.getNewsByAuthorId(authorId)
                .stream().map(this::buildNewsDto).toList());
    }
    @GetMapping("/news/byAuthorName")
    public AllNewsDto getNewsByAuthor(@RequestParam String authorName) {
        return new AllNewsDto(newsService.getNewsByAuthorName(authorName)
                .stream().map(this::buildNewsDto).toList());
    }
    @PostMapping("/updateNews")
    public NewsDto updateNews(@RequestParam long id, @RequestParam String title, @RequestParam String content,
                              @RequestParam long authorId){
        Optional<News> news = newsService.updateNews(id, title, content, authorId);
        if (news.isPresent()) {
            return buildNewsDto(news.get());
        }
        throw new NewsNotFoundException();
    }

    @DeleteMapping("/news/{id}")
    public NewsDto deleteNews(@PathVariable long id){
        Optional<News> news = newsService.deleteNews(id);
        if (news.isPresent()) {
            return buildNewsDto(news.get());
        }
        throw new NewsNotFoundException();
    }
    @GetMapping("/news")
    public AllNewsDto getAllNews(@RequestParam(defaultValue = "createDate") String sortBy,
                                 @RequestParam(defaultValue = "DESC") String direction) {
        return new AllNewsDto(newsService.getAllNews(sortBy, direction)
                .stream().map(this::buildNewsDto).toList());
    }


    @PutMapping("/tags")
    public TagDto createTag(@RequestParam String name){
        Tag tag = newsService.createTag(name);
        return buildTagDto(tag);
    }
    @GetMapping("/tags")
    public AllTagsDto getAllTags() {
        return new AllTagsDto(newsService.getAllTags()
                .stream().map(this::buildTagDto).toList());
    }
    @PutMapping("/news/{id}/addTag")
    public NewsDto addTag(@PathVariable long id, @RequestParam String tagName) {
        return buildNewsDto(newsService.addTag(id, tagName));
    }
    @PostMapping("/updateTag")
    public TagDto updateTag(@RequestParam long id, @RequestParam String name){
        Optional<Tag> tag = newsService.updateTag(id, name);
        if (tag.isPresent()) {
            return buildTagDto(tag.get());
        }
        throw new TagNotFoundException();
    }
    @DeleteMapping("/deleteTag")
    public TagDto deleteTag(@RequestParam long id){
        Optional<Tag> tag = newsService.deleteTag(id);
        if (tag.isPresent()) {
            return buildTagDto(tag.get());
        }
        throw new TagNotFoundException();
    }

    @PutMapping("/comments")
    public CommentDto createComment(@RequestParam String content, @RequestParam long newsId){
        Comment comment = newsService.createComment(content, newsId);
        return buildCommentDto(comment);
    }
    @GetMapping("/comments/{id}")
    public CommentDto getCommentById(@PathVariable long id) {
        Optional<Comment> comment = newsService.getCommentById(id);
        if (comment.isPresent()) {
            return buildCommentDto(comment.get());
        }
        throw new CommentNotFoundException();
    }
    @GetMapping("/comments")
    public AllCommentsDto getAllComments(@RequestParam(defaultValue = "createDate") String sortBy,
                                         @RequestParam(defaultValue = "DESC") String direction) {
        return new AllCommentsDto(newsService.getAllComments(sortBy, direction)
                .stream().map(this::buildCommentDto).toList());
    }
    @DeleteMapping("/comments/{id}")
    public CommentDto deleteComment(@PathVariable long id) {
        Optional<Comment> author = newsService.deleteComment(id);
        if (author.isPresent()) {
            return buildCommentDto(author.get());
        }
        throw new AuthorNotFoundException();
    }
    @PostMapping("/comments/{id}")
    public CommentDto updateComment(@PathVariable long id, @RequestParam String newContent, @RequestParam long newsId) {
        Optional<Comment> author = newsService.updateComment(id, newContent, newsId);
        if (author.isPresent()) {
            return buildCommentDto(author.get());
        }
        throw new AuthorNotFoundException();
    }


    private NewsDto buildNewsDto(News news) {
        return new NewsDto(news.getId(), news.getTitle(), news.getContent(), news.getCreateDate(),
                news.getLastUpdateDate(), buildAuthorDto(news.getAuthor()), news.getTags().stream()
                .map(this::buildTagDto).toList());
    }
    private AuthorDto buildAuthorDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }
    private TagDto buildTagDto(Tag tag) {
        return new TagDto(tag.getId(), tag.getName());
    }
    private CommentDto buildCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent(), comment.getCreateDate(),
                comment.getLastUpdateDate(), buildNewsDto(comment.getNews()));
    }
}
