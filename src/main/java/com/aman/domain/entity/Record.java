package com.aman.domain.entity;

import com.aman.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * a simple domain entity doubling as a DTO
(https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#data.sql)
*/
@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record extends BaseEntity {


    @Column(nullable = false)
    private String salary;

    @Column(nullable = false, unique = true)
    private String name;

    @Column()
    private String currency;

    @Column()
    private Department department;

    @Column()
    private SubDepartment subDepartment;

    @Column()
    private boolean onContract;
}
