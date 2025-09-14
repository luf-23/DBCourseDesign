package com.ui.teacher;

import com.TYPE.Course;
import com.TYPE.Feedback;
import com.TYPE.Teacher;
import com.start.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherFeedbackInfoJFrame extends JFrame {
    public Object[][] data;
    public String[] columnNames = {"反馈编号","来源","内容"};

    public TeacherFeedbackInfoJFrame(Teacher teacher) throws IOException {
        // 初始化窗口
        InitJFrame();

        List<Feedback> feedbacks = new ArrayList<>();
        for (int i = 0; i < Data.FEEDBACK_INFO.size(); i++) {
           if (Data.FEEDBACK_INFO.get(i).getToTeacher().equals(teacher.getTeachId())){
               feedbacks.add(Data.FEEDBACK_INFO.get(i));
           }
        }

        // 将课程信息填入表格模型的数据数组中
        data = new Object[feedbacks.size()][columnNames.length];
        for (int i = 0; i < feedbacks.size(); i++) {
            data[i][0] = feedbacks.get(i).getFeedbackId();
            data[i][1] = feedbacks.get(i).getFromStudent();
            data[i][2] = feedbacks.get(i).getData();
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
        this.setTitle("学生反馈表");
        this.setSize(800, 500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
