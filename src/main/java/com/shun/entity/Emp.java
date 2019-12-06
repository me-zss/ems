package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Emp implements Serializable {
    private String id;
    private String name;
    private BigDecimal salary;
    private int age;
    private Date bir;
    @Column(name = "dept_id")
    private String deptId;
    @Column(name = "dept_name")
    private String deptName;
}
