package com.ui.teacher;

import com.TYPE.Student;
import com.start.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeInfoJFrame extends JFrame {
    public Object[][] data;
    public String[] columnNames = {"学生学号","姓名","成绩"};

    public StudentGradeInfoJFrame() throws IOException {
        // 初始化窗口
        InitJFrame();

        List<Student> studentList = new ArrayList<>();
        for (Student student : Data.STUDENT_INFO){
            studentList.add(student);
        }
        data = new Object[studentList.size()][columnNames.length];
        for (int i=0;i<studentList.size();i++){
            data[i][0] = studentList.get(i).getStuId();
            data[i][1] = studentList.get(i).getName();
            data[i][2] = studentList.get(i).getGrade();
        }
        // 创建表格模型
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // 设置为可编辑
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
        this.setTitle("学生成绩表");
        this.setSize(800, 500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
