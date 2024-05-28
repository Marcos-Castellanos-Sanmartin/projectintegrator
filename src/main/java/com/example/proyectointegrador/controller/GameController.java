package com.example.proyectointegrador.controller;

import com.example.proyectointegrador.model.games;
import com.example.proyectointegrador.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/games")
public class GameController {

    @Autowired
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String showGameList(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "games-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditGameForm(@PathVariable String id, Model model) {
        // Obtiene el juego por su id y lo añade al modelo
        return gameService
                .getGameById(id)
                .map(game -> {
                    model.addAttribute("game", game);
                    return "edit-games"; // Devuelve la vista de edición de juegos
                })
                .orElse("redirect:/web/games"); // Si el juego no se encuentra, redirige a la lista de juegos
    }


    @PostMapping("/save")
    public String saveGame(@ModelAttribute games game) {
        gameService.saveGame(game); // Guarda el juego
        return "redirect:/web/games"; // Redirige a la lista de juegos
    }


    @PostMapping("/update/{id}")
    public String updateGame(
            @PathVariable String id,
            @ModelAttribute games gameDetails
    ) {
        return gameService
                .getGameById(id)
                .map(game -> {
                    game.setName(gameDetails.getName());
                    game.setDevelopers(gameDetails.getDevelopers());
                    game.setLaunchDate(gameDetails.getLaunchDate());
                    gameService.saveGame(game);
                    return "redirect:/web/games"; // Redirige a la lista de juegos
                })
                .orElse("redirect:/web/games"); // Si el juego no se encuentra, redirige a la lista de juegos
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable String id) {
        gameService.deleteGame(id); // Elimina el juego
        return "redirect:/web/games"; // Redirige a la lista de juegos
    }

    @GetMapping("/new")
    public String showNewGameForm(Model model) {
        model.addAttribute("game", new games()); // Añade un nuevo juego al modelo
        return "games-form"; // Devuelve la vista del formulario de juegos
    }

    @GetMapping("/search")
    public String searchGames(@RequestParam(value = "query", defaultValue = "") String query, Model model) {
        // Filtra los juegos basándose en la consulta de búsqueda y los añade al modelo
        List<games> games = gameService.getAllGames().stream()
                .filter(game -> game.getName().toLowerCase().contains(query.toLowerCase())
                        || game.getDevelopers().toLowerCase().contains(query.toLowerCase())
                        || game.getLaunchDate().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("games", games);
        return "games-search"; // Devuelve la vista de búsqueda de juegos
    }


}