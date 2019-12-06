package com.shun.service;

import com.shun.entity.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    Dept findById(String id);

    void deleteById(String id);

    void save(Dept dept);

    void update(Dept dept);
}
