package testjava;

import com.TYPE.Student;
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

public class JTableDemo extends JFrame{
    public Object [][]data;
    public String [] columnNames = {"学号","姓名","性别","出生日期","联系方式","成绩","操作"};
    public DefaultTableModel model;
    JTableDemo() throws IOException {
        Data.STUDENT_INFO = Dealinfo.readFromFile("E:\\javafile\\Homework\\Table\\studentInfo", Student.class);
        data = new Object[Data.STUDENT_INFO.size()][7];
        this.setTitle("学生信息表");
        //this.setLayout(null);//禁止布局管理器
        this.setSize(800,500);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        for (int i=0;i<Data.STUDENT_INFO.size();i++){
            data[i][0] = Data.STUDENT_INFO.get(i).getStuId();
            data[i][1] = Data.STUDENT_INFO.get(i).getName();
            data[i][2] = Data.STUDENT_INFO.get(i).getSex();
            data[i][3] = Data.STUDENT_INFO.get(i).getBirthday();
            data[i][4] = Data.STUDENT_INFO.get(i).getTel_number();
            data[i][5] = Data.STUDENT_INFO.get(i).getGrade();

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
                Data.STUDENT_INFO.remove(rowIndex);
                try {
                    Dealinfo.RefreshFile("E:\\javafile\\Homework\\Table\\studentInfo", Data.STUDENT_INFO);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public Object getCellEditorValue() {
                return null;
            }
        }
        new ButtonColumn(jTable, 6); // 添加到最后一列
        jTable.setAutoCreateRowSorter(true); // 启用排序
        JScrollPane scrollPane = new JScrollPane(jTable);
        this.add(scrollPane);

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new JTableDemo();
    }
}