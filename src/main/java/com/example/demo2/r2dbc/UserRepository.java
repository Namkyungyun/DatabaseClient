package com.example.demo2.r2dbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserRepository {

    private final DatabaseClient databaseClient;


    public Flux<Map<String, Object>> findAll() {
        return this.databaseClient
                .sql("SELECT * FROM user")
                .fetch()
                .all();
    }
    public Mono<Map<String, Object>> findByName(String name) {
        return this.databaseClient
                .sql("SELECT * FROM user WHERE name=:name")
                .bind("name", name)
                .fetch()
                .one();
    }
    public Mono<Map<String, Object>> save(User p) {
        return this.databaseClient.sql("INSERT INTO  user (name) VALUES (:name)")
                .bind("name", p.getName())
                .fetch()
                .first();

    }
    public Mono<Integer> update(String name, String newName) {
        return this.databaseClient.sql("UPDATE user set name=:newName WHERE name=:name")
                .bind("newName", newName)
                .bind("name", name)
                .fetch()
                .rowsUpdated();
    }
    public Mono<Integer> deleteByName(String name) {
        return this.databaseClient.sql("DELETE FROM user WHERE name=:name")
                .bind("name", name)
                .fetch()
                .rowsUpdated();
    }
}

