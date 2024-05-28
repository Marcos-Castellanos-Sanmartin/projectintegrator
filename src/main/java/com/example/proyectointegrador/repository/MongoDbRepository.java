package com.example.proyectointegrador.repository;

import com.example.proyectointegrador.model.games;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNullApi;

public interface MongoDbRepository extends MongoRepository<games, String> {


    Object findByName(String name);
}
