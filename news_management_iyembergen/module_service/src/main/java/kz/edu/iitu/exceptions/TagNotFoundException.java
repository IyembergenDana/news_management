package kz.edu.iitu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TagNotFoundException extends ResponseStatusException {
    public TagNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Tag not found");
    }
}
