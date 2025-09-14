package com.TYPE;

public class Schedule {
    private String courseId;
    private String  classroomId;
    private int startTime;
    public Schedule(){
    }

    public Schedule(String courseId, String classroomId, int startTime) {
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.startTime = startTime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "courseId='" + courseId + '\'' +
                ", classroomId='" + classroomId + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
