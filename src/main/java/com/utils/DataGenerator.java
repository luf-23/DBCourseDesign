package com.utils;
import com.TYPE.*;
import com.start.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    //500位学生，30位老师，80门课程，2000条反馈信息，50个教室
    public static final int NUM_STUDENTS = 500;
    public static final int NUM_TEACHERS = 30;
    public static final int NUM_COURSES = 80;
    public static final int NUM_FEEDBACKS = 2000;
    public static final int NUM_CLASSROOMS = 50;
    public static final Random random = new Random();

    // Classroom对象生成函数
    public static List<Classroom> generateClassrooms(int count) {
        List<Classroom> classrooms = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String classroomId = "C" + String.format("%03d", i);
            String classroomName = "教" + (i % 13 + 1) + "楼" + (i % 20 + 1) + "室";
            int classroomCapacity = 30 + random.nextInt(50); // 容量在30到80之间
            Classroom classroom = new Classroom(classroomId, classroomName, classroomCapacity);
            classrooms.add(classroom);
        }
        return classrooms;
    }

    // Feedback对象生成函数
    public static List<Feedback> generateFeedbacks(int count) {
        List<Student> students = generateStudents(NUM_STUDENTS);
        List<Teacher> teachers = generateTeachers(NUM_TEACHERS);

        List<Feedback> feedbacks = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String feedbackId = "F" + String.format("%06d", i);
            Student student = students.get(random.nextInt(students.size()));
            Teacher teacher = teachers.get(random.nextInt(teachers.size()));
            String data = generateFeedbackData();
            Feedback feedback = new Feedback(feedbackId, student.getStuId(), teacher.getTeachId(), data);
            feedbacks.add(feedback);
        }
        return feedbacks;
    }
    // Course对象生成函数
    public static List<Course> generateCourses(int count) {
        List<Course> courses = new ArrayList<>();
        List<String> availableStudentIds = new ArrayList<>();
        for (int i = 1; i <= NUM_STUDENTS; i++) {
            availableStudentIds.add("S" + String.format("%06d", i));
        }

        for (int i = 1; i <= count; i++) {
            String courseId = "C" + String.format("%06d", i);
            String courseName = "课程" + i;
            int time_length = 30 + random.nextInt(51); // 30-80
            int grade = 1 + random.nextInt(5); // 1-5
            String position = "教" + (1 + random.nextInt(13)) + "楼";
            String teacherId = "T" + String.format("%02d", 1 + random.nextInt(NUM_TEACHERS)); // T01-T100

            // 随机选择学生ID，确保不重复
            List<String> selectedStudentIds = new ArrayList<>();
            int numStudents = random.nextInt(51) + 30; // 每门课随机选择30-80个学生
            while (selectedStudentIds.size() < numStudents) {
                String studentId = availableStudentIds.get(random.nextInt(availableStudentIds.size()));
                if (!selectedStudentIds.contains(studentId)) {
                    selectedStudentIds.add(studentId);
                }
            }
            int duration = 1+random.nextInt(2);
            Course course = new Course(courseId, courseName, time_length, grade, position, teacherId, selectedStudentIds,duration );
            courses.add(course);
        }
        return courses;
    }

    // Teacher对象生成函数
    public static List<Teacher> generateTeachers(int count) {
        List<Teacher>teachers = new ArrayList<>();
        String[] names = {"张三", "李四", "王五", "赵六", "孙七"};
        for (int i = 1; i <=count; i++) {
            String teachId = "T" + String.format("%02d", i);
            String name = names[random.nextInt(names.length)];
            String sex = random.nextBoolean() ? "男" : "女";
            String birthday = 1970 + random.nextInt(50) + "-" + String.format("%02d", 1 + random.nextInt(12)) + "-" + String.format("%02d", 1 + random.nextInt(28));
            String tel_number = "13" + String.format("%08d", random.nextInt(99999999));
            Teacher teacher = new Teacher(teachId, name, sex, birthday, tel_number);
            teachers.add(teacher);
        }
        return teachers;
    }

    // Student对象生成函数
    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String stuId = "S" + String.format("%06d", i);
            String name = "学生" + i;
            String sex = random.nextBoolean() ? "男" : "女";
            String birthday = "1990-01-" + String.format("%02d", random.nextInt(28) + 1);
            String tel_number = "138000000" + String.format("%02d", i);
            int grade = 1 + random.nextInt(100); // 成绩在1-100之间
            students.add(new Student(stuId, name, sex, birthday, tel_number, grade));
        }
        return students;
    }
    // Schedule对象生成函数
    public static List<Schedule> generateSchedules(List<Course> courses, List<Classroom> classrooms) {
        int maxTimeSlots = 20;
        List<Schedule> schedules = new ArrayList<>();
        Collections.sort(courses, (c1, c2) -> c2.getDuration() - c1.getDuration()); // 按课程时长降序排序

        for (Course course : courses) {
            boolean scheduled = false;
            for (Classroom classroom : classrooms) {
                if (classroom.getClassroomCapacity() >= course.getStudentIds().size()) {
                    for (int time = 8; time <= maxTimeSlots - course.getDuration(); time++) {
                        boolean conflict = false;
                        for (Schedule schedule : schedules) {
                            if (schedule.getClassroomId().equals(classroom.getClassroomId()) &&
                                    !(time + course.getDuration() <= schedule.getStartTime() || time >= schedule.getStartTime() + courses.get(getCourseIndexById(schedule.getCourseId(), courses)).getDuration())) {
                                conflict = true;
                                break;
                            }
                        }
                        if (!conflict) {
                            schedules.add(new Schedule(course.getCourseId(), classroom.getClassroomId(), time));
                            scheduled = true;
                            break;
                        }
                    }
                }
                if (scheduled) break;
            }
        }
        return schedules;
    }
    private static int getCourseIndexById(String courseId, List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(courseId)) {
                return i;
            }
        }
        return -1;
    }
    private static String generateFeedbackData() {
        String[] positiveComments = {
                "老师的讲解非常清晰，受益匪浅。",
                "课程内容丰富，老师讲课很有耐心。",
                "老师的教学方法很好，课堂氛围活跃。",
                "感谢老师的辛勤付出，学到了很多知识。",
                "老师的课让我对这门学科产生了浓厚的兴趣。"
        };

        String[] negativeComments = {
                "老师的讲解有些难以理解。",
                "课程进度太快，跟不上。",
                "希望老师能多一些互动环节。",
                "老师的课堂管理有待改进。",
                "课程内容有些枯燥，希望能增加一些实例。"
        };

        boolean isPositive = Math.random() < 0.5;
        if (isPositive) {
            return positiveComments[new Random().nextInt(positiveComments.length)];
        } else {
            return negativeComments[new Random().nextInt(negativeComments.length)];
        }
    }

    public static void main(String[] args) throws IOException {
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\studentInfo",DataGenerator.generateStudents(NUM_STUDENTS));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\teacherInfo",DataGenerator.generateTeachers(NUM_TEACHERS));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\courseInfo",DataGenerator.generateCourses(NUM_COURSES));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\classroomInfo",DataGenerator.generateClassrooms(NUM_CLASSROOMS));
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\feedbackInfo",DataGenerator.generateFeedbacks(NUM_FEEDBACKS));
        Data.COURSE_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\courseInfo", Course.class);
        Data.CLASSROOM_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\classroomInfo", Classroom.class);
        Dealinfo.saveToFile("E:\\javafile\\Homework\\Table\\scheduleInfo",DataGenerator.generateSchedules(Data.COURSE_INFO,Data.CLASSROOM_INFO));
    }
}