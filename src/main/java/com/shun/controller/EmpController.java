package com.shun.controller;

import com.shun.entity.Emp;
import com.shun.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author shun
 */
@Controller
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpService empService;


    @RequestMapping("empList")
    public String getByPage(String deptId, Integer page, Model model) {
        Integer totalCount = empService.findTotalCount(deptId);
        int totalPage = totalCount % 2 == 0 ? totalCount / 2 : totalCount / 2 + 1;
        page = page == null ? 1 : page;
        List<Emp> emps = empService.findByDeptIdAndPage(deptId, page);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("emps", emps);
        model.addAttribute("currentPage", page);
        System.out.println("currentPage:" + page);
        System.out.println("totalPage:" + totalPage);
        return "emp/emplist";
    }

    @RequestMapping("delete")
    public String delete(String id) {
        Emp emp = empService.findById(id);
        empService.delete(id);
        return "redirect:/emp/empList?deptId=" + emp.getDept().getId();
    }

    @RequestMapping("getOne")
    public String getOne(String id, Model model) {
        Emp emp = empService.findById(id);
        model.addAttribute("emp", emp);
        return "emp/updateEmp";
    }

    @RequestMapping("update")
    public String update(Emp emp) {
        empService.update(emp);
        return "redirect:/emp/empList?deptId=" + emp.getDept().getId();
    }

    @RequestMapping("add")
    public String add(Emp emp) {
        empService.add(emp);
        return "redirect:/emp/empList?deptId=" + emp.getDept().getId();

    }

}
