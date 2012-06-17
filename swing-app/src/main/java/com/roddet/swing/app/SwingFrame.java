package com.roddet.swing.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class SwingFrame extends JFrame implements ActionListener {

    private JTextField swingText = new JTextField(20);
    private JButton swingBtn = new JButton("Click!");

    public SwingFrame() {
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        swingBtn.addActionListener(this);
        this.setLayout(new GridLayout());
        this.add(swingText);
        this.add(swingBtn);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }


    public static void main(String[] args) throws IOException {
        JFrame app = new SwingFrame();
        app.setVisible(true);
    }
}
