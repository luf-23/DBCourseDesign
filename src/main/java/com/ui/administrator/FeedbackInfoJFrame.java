package com.ui.administrator;

import com.start.Data;
import com.utils.Dealinfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FeedbackInfoJFrame extends JFrame{
    public Object [][]data;
    public String [] columnNames = {"id","来源","接收人","内容","操作"};
    public DefaultTableModel model;
    public FeedbackInfoJFrame() throws IOException {
        data = new Object[Data.FEEDBACK_INFO.size()][columnNames.length];
        this.setTitle("反馈表");
        //this.setLayout(null);//禁止布局管理器
        this.setSize(800,500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        for (int i=0;i<Data.FEEDBACK_INFO.size();i++){
            data[i][0] = Data.FEEDBACK_INFO.get(i).getFeedbackId();
            data[i][1] = Data.FEEDBACK_INFO.get(i).getFromStudent();
            data[i][2] = Data.FEEDBACK_INFO.get(i).getToTeacher();
            data[i][3] = Data.FEEDBACK_INFO.get(i).getData();
        }
        model = new DefaultTableModel(data, columnNames);
        JTable jTable = new JTable(model);
        class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
            private final JButton button;
            private int row;

            public ButtonColumn(JTable table, int column) {
                button = new JButton("删除");
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addActionListener(this);
                button.setHorizontalAlignment(SwingConstants.CENTER);

                // 设置点击按钮时获取焦点
                table.getColumnModel().getColumn(column).setCellEditor(this);
                table.getColumnModel().getColumn(column).setCellRenderer(this);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return button;
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                this.row = row;
                return button;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                // 这里可以调用删除行的方法
                System.out.println("删除第" + (row + 1) + "行");
                // 假设有一个方法叫做deleteRow，它接受行号作为参数
                deleteRow(row);
            }

            private void deleteRow(int rowIndex) {
                // 实现你的删除逻辑
                // 比如从模型中移除指定行：model.removeRow(rowIndex);
                model.removeRow(rowIndex);
                Data.FEEDBACK_INFO.remove(rowIndex);
                try {
                    Dealinfo.RefreshFile("E:\\javafile\\Homework\\Table\\feedbackInfo", Data.FEEDBACK_INFO);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public Object getCellEditorValue() {
                return null;
            }
        }
        new ButtonColumn(jTable, columnNames.length-1); // 添加到最后一列
        jTable.setAutoCreateRowSorter(true); // 启用排序
        JScrollPane scrollPane = new JScrollPane(jTable);
        this.add(scrollPane);

        this.setVisible(true);
    }
}
