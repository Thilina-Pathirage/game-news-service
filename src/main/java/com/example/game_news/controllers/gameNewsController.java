package com.example.game_news.controllers;


import com.example.game_news.models.CustomResponse;
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
    public ResponseEntity<CustomResponse> addNews(@RequestBody gameNews news) {
        repo.save(news);

        CustomResponse response = new CustomResponse();
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(HttpStatus.OK.value());
        response.setDescription("Game news is inserted");
        return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
    }


    @GetMapping("getAllNews")
    public List<gameNews> getAllNews () {
        return repo.findAll();
    }

    @DeleteMapping("deleteNews/{id}")
    public ResponseEntity<CustomResponse> deleteNews(@PathVariable String id) {
        Optional<gameNews> existingNews = repo.findById(id);

        if (existingNews.isPresent()) {
            repo.deleteById(id);

            CustomResponse response = new CustomResponse();
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatus.OK.value());
            response.setDescription("Game news is deleted");
            return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
        } else {
            CustomResponse response = new CustomResponse();
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setDescription("Game news not found");
            return new ResponseEntity<CustomResponse>(response, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("updateNews/{id}")
    public ResponseEntity<CustomResponse> updateNews(@PathVariable String id, @RequestBody gameNews game) {
        Optional<gameNews> existingNewsOpt = repo.findById(id);

        if(existingNewsOpt.isPresent()){
            gameNews existingGames = existingNewsOpt.get();
            existingGames.setTitle(game.getTitle());
            existingGames.setDescription(game.getDescription());
            repo.save(existingGames);

            CustomResponse response = new CustomResponse();
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatus.OK.value());
            response.setDescription("Game updated!");
            return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
        } else {
            CustomResponse response = new CustomResponse();
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setDescription("Game not found");
            return new ResponseEntity<CustomResponse>(response, HttpStatus.NOT_FOUND);
        }
    }
}
