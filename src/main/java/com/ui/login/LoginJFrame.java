package com.ui.login;

import com.TYPE.Student;
import com.TYPE.Teacher;
import com.ui.administrator.AdministratorChooseJFrame;
import com.ui.student.StudentChooseJFrame;
import com.ui.teacher.TeacherChooseJFrame;
import com.utils.ButtonUtil;
import com.utils.ColorUtil;
import com.utils.MyDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJFrame extends JFrame implements ActionListener {
    public JButton administratorButton,studentButton,teacherButton;
    public JLabel jLabel = new JLabel("请选择身份：");
    public LoginJFrame(){

        InitJFrame();

        InitImage();

        this.setVisible(true);
    }
    public void InitJFrame(){
        this.setTitle("登录");
        this.setLayout(null);//禁止布局管理器
        this.setSize(800,500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    public void InitImage(){
        // 创建并配置按钮
        administratorButton = ButtonUtil.createStyledButton("管理员", ButtonUtil.buttonFont, ColorUtil.coralRed); // 番茄红
        studentButton = ButtonUtil.createStyledButton("学生", ButtonUtil.buttonFont, ColorUtil.mintGreen); // 薄荷绿
        teacherButton = ButtonUtil.createStyledButton("教师", ButtonUtil.buttonFont, ColorUtil.skyBlue); // 浅天蓝
        administratorButton.setBounds(100,300,150,150);
        studentButton.setBounds(325,300,150,150);
        teacherButton.setBounds(550,300,150,150);
        jLabel.setFont(new Font("微软雅黑",Font.BOLD,30));
        jLabel.setBounds(275,50,600,100);
        administratorButton.addActionListener(LoginJFrame.this);
        studentButton.addActionListener(LoginJFrame.this);
        teacherButton.addActionListener(LoginJFrame.this);
        this.add(administratorButton);
        this.add(studentButton);
        this.add(teacherButton);
        this.add(jLabel);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\loginbackground.jpg"));
        background.setBounds(0,0,800,500);
        this.add(background);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj==administratorButton){
            System.out.println("管理员");
            new AdministratorChooseJFrame();
        }else if (obj==studentButton){
            System.out.println("学生");
            MyDialog myDialog = new MyDialog(this,"学生登录","请输入学号和名字(示例:S000048 学生48)：",studentButton);
            Student student = myDialog.getStudent();
            System.out.println(student);
            if (student!=null) new StudentChooseJFrame(student);
        }else if (obj==teacherButton){
            System.out.println("教师");
            MyDialog myDialog = new MyDialog(this,"教师登录","请输入工号和名字(示例:T10 王五)：",teacherButton);
            Teacher teacher = myDialog.getTeacher();
            System.out.println(teacher);
            if (teacher!=null) new TeacherChooseJFrame(teacher);
        }
    }
}
