package com.keyin.Entity;

import javax.persistence.*;
@Entity
public class Interface {
    @Id
    @SequenceGenerator(name = "interface_sequence", sequenceName = "interface_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interface_sequence")
    private Long id;
    private String input;
    @Column(columnDefinition = "TEXT")
    private String tree;

    public Interface() {
        this.input = input;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }
}

