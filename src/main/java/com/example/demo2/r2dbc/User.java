package com.example.demo2.r2dbc;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("USER")
@Builder
public class User {

    @Id
    private String id;
    private String name;

}
