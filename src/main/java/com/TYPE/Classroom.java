package com.TYPE;

public class Classroom {
    private String classroomId;
    private String classroomName;
    private int classroomCapacity;

    public Classroom() {
    }

    public Classroom(String classroomId, String classroomName, int classroomCapacity) {
        this.classroomId = classroomId;
        this.classroomName = classroomName;
        this.classroomCapacity = classroomCapacity;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public int getClassroomCapacity() {
        return classroomCapacity;
    }

    public void setClassroomCapacity(int classroomCapacity) {
        this.classroomCapacity = classroomCapacity;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId='" + classroomId + '\'' +
                ", classroomName='" + classroomName + '\'' +
                ", classroomCapacity=" + classroomCapacity +
                '}';
    }
}
