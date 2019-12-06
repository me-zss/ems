package com.shun.controller;

import com.shun.entity.Dept;
import com.shun.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shun
 */
@Controller
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("getAll")
    public String getAll(Model model) {
        List<Dept> depts = deptService.findAll();
        model.addAttribute("depts", depts);
        return "dept/deptlist";
    }

    @RequestMapping("getAllJson")
    @ResponseBody
    public List<Dept> getAllToJson() {
        List<Dept> depts = deptService.findAll();
        return depts;
    }

    @RequestMapping("getOne")
    public String getOne(String id, Model model) {
        Dept dept = deptService.findById(id);
        model.addAttribute("dept", dept);
        return "dept/updateDept";

    }

    @RequestMapping("delete")
    public String delete(String id) {
        deptService.deleteById(id);
        return "redirect:/dept/getAll";
    }

    @RequestMapping("update")
    public String update(Dept dept) {
        deptService.update(dept);
        return "redirect:/dept/getAll";
    }

    @RequestMapping("add")
    public String add(Dept dept) {
        deptService.save(dept);
        return "redirect:/dept/getAll";

    }

}
