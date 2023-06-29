package com.example.game_news.controllers;


import com.example.game_news.models.gameNews;
import com.example.game_news.repositories.gameNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class gameNewsController {

    @Autowired
    private gameNewsRepository repo;

    @PostMapping("addNews")
    public ResponseEntity<String> addNews(@RequestBody gameNews news) {
        repo.save(news);

        return new ResponseEntity<String>("Game news is inserted", HttpStatus.OK);
    }

    @GetMapping("getAllNews")
    public List<gameNews> getAllNews () {
        return repo.findAll();
    }

    @DeleteMapping("deleteNews/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable String id) {
        Optional<gameNews> existingNews = repo.findById(id);

        if(existingNews.isPresent()){
            repo.deleteById(id);
            return new ResponseEntity<String>("Game news is deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Game news not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateNews/{id}")
    public ResponseEntity<String> updateNews(@PathVariable String id, @RequestBody gameNews newNews) {
        Optional<gameNews> existingNewsOpt = repo.findById(id);

        if(existingNewsOpt.isPresent()){
            gameNews existingNews = existingNewsOpt.get();
            existingNews.setTitle(newNews.getTitle());
            existingNews.setDescription(newNews.getDescription());
            repo.save(existingNews);
            return new ResponseEntity<String>("Game news updated!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Game news not found", HttpStatus.NOT_FOUND);
        }
    }
}
