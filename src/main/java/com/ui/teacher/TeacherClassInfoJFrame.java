package com.ui.teacher;

import com.TYPE.Course;
import com.TYPE.Teacher;
import com.start.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherClassInfoJFrame extends JFrame {
    public Object[][] data;
    public String[] columnNames = {"课程编号", "课程名称", "学时", "学分", "上课地点", "本班学生(集合)", "上课时长(小时)"};

    public TeacherClassInfoJFrame(Teacher teacher) throws IOException {
        // 初始化窗口
        InitJFrame();

        // 获取学生的课程列表
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < Data.COURSE_INFO.size(); i++) {
            boolean flag = false;
            if (Data.COURSE_INFO.get(i).getTeacherId().equals(teacher.getTeachId())) flag = true;
            if (flag) {
                courseList.add(Data.COURSE_INFO.get(i));
            }
        }

        // 将课程信息填入表格模型的数据数组中
        data = new Object[courseList.size()][columnNames.length];
        for (int i = 0; i < courseList.size(); i++) {
            data[i][0] = courseList.get(i).getCourseId();
            data[i][1] = courseList.get(i).getCourseName();
            data[i][2] = courseList.get(i).getTime_length();
            data[i][3] = courseList.get(i).getGrade();
            data[i][4] = courseList.get(i).getPosition();
            data[i][5] = courseList.get(i).getStudentIds();
            data[i][6] = courseList.get(i).getDuration();
        }

        // 创建只读的表格模型
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 设置为不可编辑
            }
        };

        // 创建表格并启用排序功能
        JTable jTable = new JTable(model);
        jTable.setAutoCreateRowSorter(true);

        // 将表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(jTable);
        this.add(scrollPane);

        // 显示窗口
        this.setVisible(true);
    }

    private void InitJFrame() {
        this.setTitle("教师课程表");
        this.setSize(800, 500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
