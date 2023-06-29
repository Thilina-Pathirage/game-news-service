package com.example.game_news.repositories;

import com.example.game_news.models.gameNews;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface gameNewsRepository extends MongoRepository<gameNews, String> {
}
