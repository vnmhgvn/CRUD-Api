package com.crudbasic.student.models;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String major;
    @Column
    private double point;

    public Student () {

    }
    public Student( String name, String address, String major, double point) {
        this.name = name;
        this.address = address;
        this.major = major;
        this.point = point;
    }

    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMajor() {
        return major;
    }

    public double getPoint() {
        return point;
    }

    public void setID(long ID) {
        this.id = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", major='" + major + '\'' +
                ", point=" + point +
                '}';
    }
}

