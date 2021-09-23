package com.example.BellTestProject.model;

import javax.persistence.*;

@Entity
@Table(name = "docs")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id", unique = true, nullable = false)
    private int id;

    @Column(name = "name" , length = 255 , nullable = false)
    private String name;

    @Column(name = "code", length = 2)
    private int code;

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
