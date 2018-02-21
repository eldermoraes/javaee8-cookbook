package com.eldermoraes.ch09.task.status;

/**
 *
 * @author eldermoraes
 */
public class User {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + '}';
    }
}
