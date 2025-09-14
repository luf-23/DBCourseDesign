package com.ui.teacher;

import com.TYPE.Teacher;
import com.utils.ButtonUtil;
import com.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TeacherChooseJFrame extends JFrame implements ActionListener {
    Teacher teacher;
    private JButton changeStudentGradeButton,feedbackInfoButton,sortStudentInfoButton,queryScheduleInfoButton;
    private JLabel jLabel = new JLabel("请选择操作：");

    public TeacherChooseJFrame(Teacher teacher){
        this.teacher = teacher;
        InitJFrame();
        jLabel.setFont(new Font("微软雅黑",Font.BOLD,30));
        jLabel.setBounds(100,100,600,100);
        changeStudentGradeButton = ButtonUtil.createStyledButton("修改学生成绩",ButtonUtil.buttonFont, ColorUtil.apricotYellow);
        feedbackInfoButton = ButtonUtil.createStyledButton("查看反馈信息",ButtonUtil.buttonFont, ColorUtil.lavenderPurple);
        sortStudentInfoButton = ButtonUtil.createStyledButton("排序学生信息",ButtonUtil.buttonFont, ColorUtil.slateGray);
        queryScheduleInfoButton = ButtonUtil.createStyledButton("查看排课信息",ButtonUtil.buttonFont, ColorUtil.coralRed);
        changeStudentGradeButton.setBounds(100,250,125,125);
        feedbackInfoButton.setBounds(250,250,125,125);
        sortStudentInfoButton.setBounds(400,250,125,125);
        queryScheduleInfoButton.setBounds(550,250,125,125);
        this.add(changeStudentGradeButton);
        this.add(feedbackInfoButton);
        this.add(sortStudentInfoButton);
        this.add(queryScheduleInfoButton);
        this.add(jLabel);
        changeStudentGradeButton.addActionListener(this);
        feedbackInfoButton.addActionListener(this);
        sortStudentInfoButton.addActionListener(this);
        queryScheduleInfoButton.addActionListener(this);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\teacherbackground.jpg"));
        background.setBounds(0,0,800,500);
        this.add(background);
        this.setVisible(true);
    }
    private void InitJFrame(){
        this.setTitle("选择");
        this.setLayout(null);//禁止布局管理器
        this.setSize(800,500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj==changeStudentGradeButton){
            try {
                new StudentGradeInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==feedbackInfoButton){
            try {
                new TeacherFeedbackInfoJFrame(teacher);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==sortStudentInfoButton){
            try {
                new SortedStudentGradeInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==queryScheduleInfoButton){
            try {
                new TeacherClassInfoJFrame(teacher);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
