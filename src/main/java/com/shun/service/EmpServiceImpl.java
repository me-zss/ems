package com.shun.service;


import com.shun.dao.EmpDao;
import com.shun.entity.Dept;
import com.shun.entity.Emp;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author shun
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer findTotalCount(String deptId) {
        return empDao.selectCount(new Emp().setDept(new Dept().setId(deptId)));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Emp> findByDeptIdAndPage(String deptId, Integer page) {
        int start = (page - 1) * 2;
        int size = 2;
        return empDao.selectByRowBounds(new Emp().setDept(new Dept().setId(deptId)), new RowBounds(start, size));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Emp findById(String id) {
        return (Emp) empDao.selectByPrimaryKey(new Emp().setId(id));
    }

    @Override
    public void add(Emp emp) {
        emp.setId(UUID.randomUUID().toString().replace("-", ""));
        empDao.insertSelective(emp);
    }

    @Override
    public void update(Emp emp) {
        empDao.updateByPrimaryKeySelective(emp);
    }

    @Override
    public void delete(String id) {
        empDao.deleteByPrimaryKey(new Emp().setId(id));
    }

    @Override
    public void deleteByDeptId(String id) {
        empDao.delete(new Emp().setDept(new Dept().setId(id)));
    }
}
