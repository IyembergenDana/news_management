package kz.edu.iitu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NewsNotFoundException extends ResponseStatusException {
    public NewsNotFoundException() {
        super(HttpStatus.NOT_FOUND, "News not found");
    }

}
