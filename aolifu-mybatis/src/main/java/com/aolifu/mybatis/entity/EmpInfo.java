package com.aolifu.mybatis.entity;

public class EmpInfo {

    private String id;

    private String name;

    private String age;

    private String job;

    private String phone;

    private String did;

    @Override
    public String toString() {
        return "EmpInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                ", did='" + did + '\'' +
                '}';
    }
}
