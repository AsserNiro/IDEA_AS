package com.entiy;

import java.sql.Date;

public class student {
    private int id;
    private String name;
    private int chinese;
    private int math;
    private int eng;
    private int total;
    private Date data;

    public student() {
        name = null;
        chinese = 0;
        math = 0;
        eng = 0;
        total = 0;
        data = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
        total = chinese + math + eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
        total = chinese + math + eng;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
        total = chinese + math + eng;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", eng=" + eng +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
