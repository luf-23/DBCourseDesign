package com.utils;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class ButtonUtil {
    public static Font buttonFont = new Font("微软雅黑", Font.BOLD, 20);
    public static JButton createStyledButton(String text, Font font, Color color) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(color);
        button.setForeground(Color.WHITE); // 文字颜色为白色
        button.setFocusPainted(false); // 去除焦点边框
        button.setBorder(BorderFactory.createEmptyBorder()); // 移除默认边框
        button.setOpaque(false); // 设置为透明以避免方形背景影响视觉效果

        // 使用自定义UI来尝试创建圆角边框
        button.setUI(new MetalButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                super.paintButtonPressed(g, b);
                g.setColor(b.getBackground().darker());
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20); // 圆角半径设为20
            }
        });

        // 添加一个轻微的边框
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

        return button;
    }
}
