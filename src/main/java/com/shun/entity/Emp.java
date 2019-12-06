package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private String deptId;
    private String deptName;
}
