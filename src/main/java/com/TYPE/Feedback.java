package com.TYPE;

public class Feedback {
    private String feedbackId;
    private String fromStudent;
    private String toTeacher;
    private String data;

    public Feedback() {
    }

    public Feedback(String feedbackId, String fromStudent, String toTeacher, String data) {
        this.feedbackId = feedbackId;
        this.fromStudent = fromStudent;
        this.toTeacher = toTeacher;
        this.data = data;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFromStudent() {
        return fromStudent;
    }

    public void setFromStudent(String fromStudent) {
        this.fromStudent = fromStudent;
    }

    public String getToTeacher() {
        return toTeacher;
    }

    public void setToTeacher(String toTeacher) {
        this.toTeacher = toTeacher;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId='" + feedbackId + '\'' +
                ", fromStudent='" + fromStudent + '\'' +
                ", toTeacher='" + toTeacher + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

}
