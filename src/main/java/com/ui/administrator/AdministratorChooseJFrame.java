package com.ui.administrator;

import com.start.Data;
import com.utils.ButtonUtil;
import com.utils.ColorUtil;
import com.utils.DataGenerator;
import com.utils.MyDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdministratorChooseJFrame extends JFrame implements ActionListener{
    public JLabel jLabel = new JLabel("请选择要操作的数据表:");
    public JLabel jLabel2 = new JLabel("排课算法启动按钮:");
    public JButton forStudent,forTeacher,forCourse,forFeedback,forClassroom,startForSchedule;
    public AdministratorChooseJFrame(){
        InitJFrame();
        jLabel.setBounds(100,200,600,100);
        jLabel.setFont(new Font("微软雅黑",Font.BOLD,30));
        jLabel2.setBounds(100,100,300,100);
        jLabel2.setFont(new Font("微软雅黑",Font.BOLD,30));
        this.add(jLabel);
        this.add(jLabel2);
        startForSchedule = ButtonUtil.createStyledButton("排课",ButtonUtil.buttonFont, ColorUtil.lavenderPurple);
        forStudent = ButtonUtil.createStyledButton("学生表",ButtonUtil.buttonFont, ColorUtil.apricotYellow);
        forTeacher = ButtonUtil.createStyledButton("教师表",ButtonUtil.buttonFont, ColorUtil.slateGray);
        forCourse = ButtonUtil.createStyledButton("课程表",ButtonUtil.buttonFont, ColorUtil.mintGreen);
        forClassroom = ButtonUtil.createStyledButton("教室表",ButtonUtil.buttonFont, ColorUtil.coralRed);
        forFeedback = ButtonUtil.createStyledButton("反馈表",ButtonUtil.buttonFont, ColorUtil.skyBlue);
        startForSchedule.setBounds(400,100,125,100);
        forStudent.setBounds(50,300,125,100);
        forTeacher.setBounds(200,300,125,100);
        forCourse.setBounds(350,300,125,100);
        forClassroom.setBounds(500,300,125,100);
        forFeedback.setBounds(650,300,125,100);
        forStudent.addActionListener(this);
        forTeacher.addActionListener(this);
        forClassroom.addActionListener(this);
        forCourse.addActionListener(this);
        forFeedback.addActionListener(this);
        startForSchedule.addActionListener(this);
        this.add(forStudent);
        this.add(forTeacher);
        this.add(forCourse);
        this.add(forClassroom);
        this.add(forFeedback);
        this.add(startForSchedule);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\administratorbackground.png"));
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
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if (obj==forStudent){
            System.out.println("学生表");
            try {
                new StudentInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==forTeacher){
            System.out.println("教师表");
            try {
                new TeacherInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==forCourse){
            System.out.println("课程表");
            try {
                new CourseInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==forClassroom){
            System.out.println("教室表");
            try {
                new ClassroomInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==forFeedback){
            System.out.println("反馈表");
            try {
                new FeedbackInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj==startForSchedule){
            System.out.println("排课");
            MyDialog.showOnlyReadModalJDialog("排课成功！");
            Data.SCHEDULE_INFO = DataGenerator.generateSchedules(Data.COURSE_INFO,Data.CLASSROOM_INFO);
            try {
                new ScheduleInfoJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
