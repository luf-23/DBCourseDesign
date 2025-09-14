package com.ui.student;

import com.TYPE.Feedback;
import com.TYPE.Student;
import com.TYPE.Teacher;
import com.start.Data;
import com.utils.ButtonUtil;
import com.utils.ColorUtil;
import com.utils.Dealinfo;
import com.utils.MyDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GiveFeedbackJFrame extends JFrame implements ActionListener, KeyListener {
    Student student;
    private static int DEBUG = 155;
    private JLabel Content;
    private JTextField content;
    private JLabel To;
    private JTextField to;
    private JButton submit;
    public GiveFeedbackJFrame(Student student) {
        InitJFrame();
        this.student = student;
        Font font = new Font("微软雅黑", Font.PLAIN, 15);
        Content = new JLabel("请输入反馈内容：");
        Content.setFont(font);
        Content.setBounds(20, 20, 120, 40);
        content = new JTextField();
        content.setFont(font);
        content.setBounds(160, 20, 400, 40);
        To = new JLabel("请输入收件老师工号和姓名：");
        To.setFont(font);
        To.setBounds(20,100,200,40);
        to = new JTextField();
        to.setFont(font);
        to.setBounds(240,100,320,40);
        submit = ButtonUtil.createStyledButton("提交", new Font("微软雅黑", Font.BOLD, 20), ColorUtil.skyBlue);
        submit.setBounds(120,200,320,40);
        submit.addActionListener(this);
        this.add(submit);
        this.add(To);
        this.add(to);
        this.add(Content);
        this.add(content);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\givefeedbackbackground.jpg"));
        background.setBounds(0,0,600,300);
        this.add(background);
        content.addKeyListener(this);
        to.addKeyListener(this);
        this.setVisible(true);
    }
    private void InitJFrame() {
        this.setSize(600, 300);
        this.setTitle("提示");
        this.setLayout(null);//禁止布局管理器
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj==submit){
            String content = this.content.getText();
            String to = this.to.getText();
            String id = "";
            String name = "";
            int pos = to.indexOf(" ");
            for (int i=0;i<pos;i++) id += to.charAt(i);
            for (int i=pos+1;i<to.length();i++) name += to.charAt(i);
            boolean found = false;
            for (Teacher teacher: Data.TEACHER_INFO){
                if (teacher.getTeachId().equals(id)&&teacher.getName().equals(name)){
                    found = true;
                }
            }
            if (found){
                MyDialog.showOnlyReadModalJDialog("反馈成功!");
                Data.FEEDBACK_INFO.add(new Feedback("F"+String.format("%06d", Data.FEEDBACK_INFO.size()+1),student.getStuId(),id,content));
                try {
                    Dealinfo.RefreshFile("E:\\javafile\\Homework\\Table\\feedbackInfo", Data.FEEDBACK_INFO);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }else{
                MyDialog.showOnlyReadModalJDialog("反馈失败！教师工号或姓名有误!");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code==DEBUG){
            Object obj = e.getSource();
            //System.out.println("DEBUG");
            if (obj==content) content.setText("老师很负责，教学质量也很高，同学们上课很活跃");
            else if (obj==to) to.setText("T10 王五");
        }
    }
}
