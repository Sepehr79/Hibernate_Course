package com.hibernate.advancemapping.onetomany;

import javax.persistence.*;

@Entity
@Table(name = "dependency")
public class Dependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public Dependency(String name) {
        this.name = name;
    }

    public Dependency() {
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

    @Override
    public String toString() {
        return "Dependency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
