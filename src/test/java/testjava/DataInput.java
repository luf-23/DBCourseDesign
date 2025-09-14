package testjava;

import com.TYPE.Classroom;
import com.TYPE.Course;
import com.TYPE.Feedback;
import com.TYPE.Teacher;
import com.start.Data;
import com.utils.DataGenerator;
import com.utils.Dealinfo;

import java.io.IOException;

public class DataInput {
    public static void main(String[] args) throws IOException {
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\studentInfo",DataGenerator.generateStudents(500));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\teacherInfo",DataGenerator.generateTeachers(100));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\courseInfo",DataGenerator.generateCourses(50));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\classroomInfo",DataGenerator.generateClassrooms(200));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\feedbackInfo",DataGenerator.generateFeedbacks(300));
        Data.COURSE_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\courseInfo", Course.class);
        Data.CLASSROOM_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\classroomInfo", Classroom.class);
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\scheduleInfo",DataGenerator.generateSchedules(Data.COURSE_INFO,Data.CLASSROOM_INFO));
    }
}

