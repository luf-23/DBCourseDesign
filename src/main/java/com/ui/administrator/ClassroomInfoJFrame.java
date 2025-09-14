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

public class ClassroomInfoJFrame extends JFrame{
    public Object [][]data;
    public String [] columnNames = {"教室编号","名称","容量","操作"};
    public DefaultTableModel model;
    public ClassroomInfoJFrame() throws IOException {
        data = new Object[Data.CLASSROOM_INFO.size()][columnNames.length];
        this.setTitle("教室表");
        //this.setLayout(null);//禁止布局管理器
        this.setSize(800,500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        for (int i=0;i<Data.CLASSROOM_INFO.size();i++){
            data[i][0] = Data.CLASSROOM_INFO.get(i).getClassroomId();
            data[i][1] = Data.CLASSROOM_INFO.get(i).getClassroomName();
            data[i][2] = Data.CLASSROOM_INFO.get(i).getClassroomCapacity();
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
                // 实现删除逻辑
                model.removeRow(rowIndex);
                Data.CLASSROOM_INFO.remove(rowIndex);
                try {
                    Dealinfo.RefreshFile("E:\\javafile\\Homework\\Table\\classroomInfo", Data.CLASSROOM_INFO);
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
