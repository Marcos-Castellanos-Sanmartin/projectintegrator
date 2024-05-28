package com.example.proyectointegrador.service;

import com.example.proyectointegrador.model.games;
import com.example.proyectointegrador.repository.MongoDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final MongoDbRepository gameRepository;

    @Autowired
    public GameService(MongoDbRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<games> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<games> getGameById(String id) {
        return gameRepository.findById(id);
    }

    public void saveGame(games game) {
        gameRepository.save(game);
    }

    public void deleteGame(String id) {
        gameRepository.deleteById(id);
    }

    public Object searchGame(String name) {
        gameRepository.findByName(name);
        return gameRepository.findByName(name);
    }
}
