package com.shun.service;

import com.shun.dao.DeptDao;
import com.shun.dao.EmpDao;
import com.shun.entity.Dept;
import com.shun.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

/**
 * @author shun
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private EmpDao empDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Dept> findAll() {
        return deptDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Dept findById(String id) {
        return (Dept) deptDao.selectByPrimaryKey(new Dept().setId(id));
    }

    @Override
    public void deleteById(String id) {
        deptDao.deleteByPrimaryKey(new Dept().setId(id));
        Example example = new Example(Emp.class);
        example.createCriteria().andEqualTo("deptId", id);
        empDao.deleteByExample(example);
    }

    @Override
    public void save(Dept dept) {
        dept.setId(UUID.randomUUID().toString().replace("-", ""));
        deptDao.insertSelective(dept);

    }

    @Override
    public void update(Dept dept) {
        deptDao.updateByPrimaryKeySelective(dept);

    }
}
