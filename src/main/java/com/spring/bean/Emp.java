package com.spring.bean;

public class Emp {
    private String ename;
    private String gender;
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void testEmp() {
        System.out.println(ename + "::" + gender + "::" + dept);
    }
}
