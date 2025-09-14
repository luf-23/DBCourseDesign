package com.TYPE;

public class Teacher{
    private String teachId;
    private String name;
    private String sex;
    private String birthday;
    private String tel_number;
    public Teacher() {
    }

    public String getTeachId() {
        return teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId;
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

    public Teacher(String teachId, String name, String sex, String birthday, String tel_number) {
        this.teachId = teachId;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.tel_number = tel_number;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teachId='" + teachId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", tel_number='" + tel_number + '\'' +
                '}';
    }
}
