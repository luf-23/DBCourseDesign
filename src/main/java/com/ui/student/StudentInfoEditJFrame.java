package com.ui.student;

import com.TYPE.Student;
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

public class StudentInfoEditJFrame extends JFrame implements KeyListener {
    private Student student;
    private static final int BACKSPACE = 8;
    private JTextField stuIdField, nameField, sexField, birthdayField, telNumberField, gradeField;

    public StudentInfoEditJFrame(Student student) {
        this.student = student;
        InitJFrame();
        initUI(student);
        this.setVisible(true);
    }

    private void InitJFrame() {
        this.setSize(500, 400); // 增大窗口高度以适应更多组件
        this.setTitle("编辑学生信息");
        this.setLayout(null); // 禁用布局管理器
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void initUI(Student student) {
        int padding = 10; // 组件之间的间距
        int startX = 50, startY = 30; // 起始位置
        int labelWidth = 80, fieldWidth = 200, height = 30; // 标签宽度、字段宽度和组件高度

        // 学号
        JLabel lblStuId = new JLabel("学号:");
        lblStuId.setBounds(startX, startY, labelWidth, height);
        this.add(lblStuId);

        stuIdField = new JTextField(student.getStuId());
        stuIdField.setBounds(startX + labelWidth + padding, startY, fieldWidth, height);
        stuIdField.setEditable(false);
        this.add(stuIdField);

        startY += height + padding;
        // 姓名
        JLabel lblName = new JLabel("姓名:");
        lblName.setBounds(startX, startY, labelWidth, height);
        this.add(lblName);

        nameField = new JTextField(student.getName());
        nameField.setBounds(startX + labelWidth + padding, startY, fieldWidth, height);
        this.add(nameField);

        startY += height + padding;
        // 性别
        JLabel lblSex = new JLabel("性别:");
        lblSex.setBounds(startX, startY, labelWidth, height);
        this.add(lblSex);

        sexField = new JTextField(student.getSex());
        sexField.setBounds(startX + labelWidth + padding, startY, fieldWidth, height);
        this.add(sexField);

        startY += height + padding;
        // 生日
        JLabel lblBirthday = new JLabel("生日:");
        lblBirthday.setBounds(startX, startY, labelWidth, height);
        this.add(lblBirthday);

        birthdayField = new JTextField(student.getBirthday());
        birthdayField.setBounds(startX + labelWidth + padding, startY, fieldWidth, height);
        this.add(birthdayField);

        startY += height + padding;
        // 电话号码
        JLabel lblTelNumber = new JLabel("电话:");
        lblTelNumber.setBounds(startX, startY, labelWidth, height);
        this.add(lblTelNumber);

        telNumberField = new JTextField(student.getTel_number());
        telNumberField.setBounds(startX + labelWidth + padding, startY, fieldWidth, height);
        this.add(telNumberField);

        startY += height + padding;
        // 成绩

        JLabel lblGrade = new JLabel("成绩:");
        lblGrade.setBounds(startX, startY, labelWidth, height);
        this.add(lblGrade);

        gradeField = new JTextField(String.valueOf(student.getGrade()));
        gradeField.setBounds(startX + labelWidth + padding, startY, fieldWidth, height);
        gradeField.setEditable(false);
        this.add(gradeField);

        // 提交按钮
        JButton submitBtn = ButtonUtil.createStyledButton("提交", ButtonUtil.buttonFont, ColorUtil.coralRed);
        startY += height + padding * 2;
        submitBtn.setBounds(startX + labelWidth / 2 + padding+100, startY, 100, height);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onSubmit();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.add(submitBtn);
        JLabel background = new JLabel(new ImageIcon("E:\\javafile\\Homework\\Image\\studentinfoeditbackground.jpg"));
        background.setBounds(0,0,500,400);
        this.add(background);

        stuIdField.addKeyListener(this);
        gradeField.addKeyListener(this);
    }

    private void onSubmit() throws IOException {
        System.out.println("提交的学生信息: " +
                "学号=" + stuIdField.getText() + ", " +
                "姓名=" + nameField.getText() + ", " +
                "性别=" + sexField.getText() + ", " +
                "生日=" + birthdayField.getText() + ", " +
                "电话=" + telNumberField.getText() + ", " +
                "年级=" + gradeField.getText()
        );
        MyDialog.showOnlyReadModalJDialog("修改成功！");
        dispose();
        for (int i=0;i<Data.STUDENT_INFO.size();i++){
            if (Data.STUDENT_INFO.get(i).getStuId().equals(this.student.getStuId())){
                Student student = new Student(
                        stuIdField.getText(),
                        nameField.getText(),
                        sexField.getText(),
                        birthdayField.getText(),
                        telNumberField.getText(),
                        Integer.parseInt(gradeField.getText())
                );
                Data.STUDENT_INFO.set(i,student);
                this.student = student;
                break;
            }
        }
        Dealinfo.RefreshFile("E:\\javafile\\Homework\\Table\\studentInfo", Data.STUDENT_INFO);
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
        Object obj = e.getSource();
        if (code==BACKSPACE){
            if (obj==stuIdField) MyDialog.showOnlyReadModalJDialog("学号不允许修改");
            else if (obj==gradeField) MyDialog.showOnlyReadModalJDialog("成绩不允许修改");
        }
    }
}