package br.com.ccs.grit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/healthcheck")
public class task3 {

    @GetMapping(value = "/{format}")
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<?> healthcheck(@PathVariable String format) {

        if (format.equalsIgnoreCase("short")) {
            return ResponseEntity.ok(new ShortResponse());
        } else if (format.equalsIgnoreCase("full")) {
            return ResponseEntity.ok(new FullResponse());
        } else {

            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void post() {

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void delete() {

    }

    @PutMapping
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void put() {

    }

    @PatchMapping
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void patch() {

    }
}

class ShortResponse {

    private String status = "OK";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class FullResponse {

    OffsetDateTime currentTime = OffsetDateTime.now();
    private String status = "OK";

    public OffsetDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(OffsetDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}