package com.start;

import com.TYPE.*;
import com.ui.login.LoginJFrame;
import com.utils.Dealinfo;

import java.io.IOException;

public class APP {
    static{
        try {
            Data.STUDENT_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\studentInfo", Student.class);
            Data.TEACHER_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\teacherInfo", Teacher.class);
            Data.COURSE_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\courseInfo", Course.class);
            Data.FEEDBACK_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\feedbackInfo", Feedback.class);
            Data.CLASSROOM_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\classroomInfo", Classroom.class);
            Data.SCHEDULE_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\scheduleInfo", Schedule.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new LoginJFrame();
    }
}
