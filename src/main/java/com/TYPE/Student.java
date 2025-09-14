package com.TYPE;
public class Student{
    private String stuId;
    private String name;
    private String sex;
    private String birthday;
    private String tel_number;
    private int grade;
    public Student() {
    }
    public Student(String stuId, String name, String sex, String birthday, String tel_number, int grade) {
        this.stuId = stuId;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.tel_number = tel_number;
        this.grade = grade;

    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", tel_number='" + tel_number + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
