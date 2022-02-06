package com.example.demo2.r2dbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserRepository {

    private final DatabaseClient databaseClient;


    public Flux<Map<String, Object>> findAll() {
        String query = "SELECT user.id,user.name,team.team_name " +
                       "FROM user INNER JOIN team ON user.team_id = team.id";

        return databaseClient.sql(query)
                             .fetch()
                             .all();

    }

    /** name으로 데이터 조회 **/
    public Mono<Map<String, Object>> findByName(String name) {
        return this.databaseClient
                .sql("SELECT * FROM user WHERE name=:name")
                .bind("name", name)
                .fetch()
                .one();
    }

    /** 저장 **/
    public Mono<Map<String, Object>> save(User p) {
        return this.databaseClient.sql("INSERT INTO  user (name, team_id) VALUES (:name, :team_id)")
                .bind("name", p.getName())
                .bind("team_id", p.getTeam_id())
                .fetch()
                .first();

    }

    /** teamName 수정 **/
    public Mono<Integer> update(String name, String newName) {
        return this.databaseClient.sql("UPDATE team set team_name=:newName WHERE team_name=:name")
                .bind("newName", newName)
                .bind("name", name)
                .fetch()
                .rowsUpdated();
    }

    /** 유저 삭제 **/
    public Mono<Integer> deleteByName(String name) {
        return this.databaseClient.sql("DELETE FROM user WHERE name=:name")
                .bind("name", name)
                .fetch()
                .rowsUpdated();
    }
}

