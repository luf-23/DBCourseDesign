package com.utils;

import com.TYPE.Student;
import com.TYPE.Teacher;
import com.start.Data;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyDialog extends JDialog implements ActionListener, KeyListener {
    private JLabel jLabel;
    private JButton jButton;
    private JTextField jTextField;
    private JButton IDENTIFY;
    private Student student;
    private Teacher teacher;
    private static int DEBUG = 155;

    public MyDialog(JFrame owner, String title,String message,JButton identity) {
        super(owner, title, true);// 设置为模态对话框
        this.setSize(400, 300);
        this.setModal(true);//不关闭弹窗无法进行其他操作
        this.setAlwaysOnTop(true);//弹窗置顶
        this.setLayout(null);//禁止布局管理器
        this.setLocationRelativeTo(null);//弹框居中
        IDENTIFY = identity;
        jLabel = new JLabel(message);
        jLabel.setBounds(0, 0, 400, 200);
        jLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jTextField = new JTextField();
        jTextField.setBounds(0, 150, 250, 80);
        jTextField.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jButton = ButtonUtil.createStyledButton("确定", new Font("微软雅黑", Font.BOLD, 20), ColorUtil.lavenderPurple);
        jButton.setBounds(280, 150, 100, 80);
        jButton.addActionListener(this);
        this.add(jButton);
        this.add(jTextField);
        this.add(jLabel);
        jTextField.addKeyListener(this);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\dialogbackground.jpg"));
        background.setBounds(0,0,400,300);
        this.add(background);
        this.setVisible(true);
    }

    public static void showOnlyReadModalJDialog(String message) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200,150);
        jDialog.setTitle("弹窗提示");
        jDialog.setAlwaysOnTop(true);//弹窗置顶
        jDialog.setLocationRelativeTo(null);//弹框居中
        jDialog.setModal(true);//不关闭弹窗无法进行其他操作
        JLabel jLabel = new JLabel(message);
        jLabel.setBounds(0,0,200,150);
        jDialog.getContentPane().add(jLabel);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\onlyreaddialog.jpg"));
        background.setBounds(0,0,200,150);
        jDialog.add(background);
        jDialog.repaint();
        jDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj==jButton){
            String inputVal = jTextField.getText();
            String id = "";
            String name = "";
            int pos = inputVal.indexOf(" ");
            for (int i=0;i<pos;i++) id += inputVal.charAt(i);
            for (int i=pos+1;i<inputVal.length();i++) name += inputVal.charAt(i);
            if (IDENTIFY.getText()=="学生"){
                boolean found = false;
                for (Student student: Data.STUDENT_INFO){
                    if (student.getStuId().equals(id)&&student.getName().equals(name)){
                        found = true;
                        this.student = student;
                    }
                }
                if (found){
                    MyDialog.showOnlyReadModalJDialog("登录成功！");
                    dispose();
                }else{
                    MyDialog.showOnlyReadModalJDialog("登录失败！学生号或名字有误");
                    System.out.println(id+" "+name);
                }

            }else if (IDENTIFY.getText()=="教师"){
                boolean found = false;
                for (Teacher teacher: Data.TEACHER_INFO){
                    if (teacher.getTeachId().equals(id)&&teacher.getName().equals(name)){
                        found = true;
                        this.teacher = teacher;
                    }
                }
                if (found){
                    MyDialog.showOnlyReadModalJDialog("登录成功！");
                    dispose();
                }else{
                    MyDialog.showOnlyReadModalJDialog("登录失败！教师工号或名字有误");
                    System.out.println(id+" "+name);
                }

            }
        }
    }
    public Student getStudent() {
        return student;
    }
    public Teacher getTeacher() {
        return teacher;
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
            if (IDENTIFY.getText()=="学生"){
                jTextField.setText("S000048 学生48");
            }else if (IDENTIFY.getText()=="教师"){
                jTextField.setText("T10 王五");
            }
        }
    }
}
