package com.example.demo2.r2dbc;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("USER")
@Builder
public class User {

    @Id
    @Column("id")
    private String id;
    @Column("name")
    private String name;
    @Column("team_id")
    private String team_id;

//    @Transient
//    private List<Team> teams;
}
