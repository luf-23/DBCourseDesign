package com.ui.student;

import com.TYPE.Student;
import com.TYPE.Teacher;
import com.start.Data;
import com.utils.ButtonUtil;
import com.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StudentChooseJFrame extends JFrame implements ActionListener {
    public JLabel jLabel = new JLabel("请选择操作：");
    public JButton changeInfo,giveFeedback,queryClassInfo;
    public Student student;
    public StudentChooseJFrame(Student student){
        this.student = student;
        InitJFrame();
        jLabel.setFont(new Font("微软雅黑", Font.BOLD,30));
        jLabel.setBounds(100,100,600,100);
        this.add(jLabel);
        changeInfo = ButtonUtil.createStyledButton("修改个人信息",ButtonUtil.buttonFont, ColorUtil.apricotYellow);
        queryClassInfo = ButtonUtil.createStyledButton("查询课表",ButtonUtil.buttonFont, ColorUtil.coralRed);
        giveFeedback = ButtonUtil.createStyledButton("留言反馈",ButtonUtil.buttonFont,ColorUtil.lavenderPurple);
        changeInfo.setBounds(100,300,125,100);
        changeInfo.addActionListener(this);
        this.add(changeInfo);
        giveFeedback.setBounds(300,300,125,100);
        giveFeedback.addActionListener(this);
        this.add(giveFeedback);
        queryClassInfo.setBounds(500,300,125,100);
        queryClassInfo.addActionListener(this);
        this.add(queryClassInfo);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\studentbackground.jpg"));
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
        if (obj==changeInfo){
            new StudentInfoEditJFrame(RegainStudentInfo(student));
        }else if (obj==giveFeedback){
            new GiveFeedbackJFrame(student);
        }else if (obj==queryClassInfo){
            try {
                new StudentClassInfoJFrame(student);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private Student RegainStudentInfo(Student student){
        for (int i=0;i< Data.STUDENT_INFO.size();i++){
            if (Data.STUDENT_INFO.get(i).getStuId().equals(student.getStuId())){
                return Data.STUDENT_INFO.get(i);
            }
        }
        System.out.println("学生id有误");
        return student;
    }
}
