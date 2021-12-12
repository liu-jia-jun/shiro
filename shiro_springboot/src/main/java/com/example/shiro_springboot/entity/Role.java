package com.example.shiro_springboot.entity;

import java.util.List;

/**
 * @author 刘佳俊
 */
public class Role {
    private Integer id;
    private String name;

    private List<Perms> perms;

    public List<Perms> getPerms() {
        return perms;
    }

    public void setPerms(List<Perms> perms) {
        this.perms = perms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
