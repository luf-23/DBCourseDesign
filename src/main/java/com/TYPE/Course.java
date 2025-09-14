package com.TYPE;

import java.util.List;

public class Course {
    private String courseId;
    private String courseName;
    private int Time_length;
    private int grade;
    private String position;
    private String teacherId;
    private List<String>studentIds;
    private int duration;
    public Course() {
    }

    public Course(String courseId, String courseName, int time_length, int grade, String position, String teacherId, List<String> studentIds, int duration) {
        this.courseId = courseId;
        this.courseName = courseName;
        Time_length = time_length;
        this.grade = grade;
        this.position = position;
        this.teacherId = teacherId;
        this.studentIds = studentIds;
        this.duration = duration;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTime_length() {
        return Time_length;
    }

    public void setTime_length(int time_length) {
        Time_length = time_length;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", Time_length=" + Time_length +
                ", grade=" + grade +
                ", position='" + position + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", studentIds=" + studentIds +
                ", duration=" + duration +
                '}';
    }
}
