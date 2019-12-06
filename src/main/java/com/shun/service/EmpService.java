package com.shun.service;

import com.shun.entity.Emp;

import java.util.List;

public interface EmpService {
    Integer findTotalCount(String deptId);

    List<Emp> findByDeptIdAndPage(String deptId, Integer page);

    Emp findById(String id);

    void add(Emp emp);

    void update(Emp emp);

    void delete(String id);

    void deleteByDeptId(String id);
}
