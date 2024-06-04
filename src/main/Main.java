package main;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Test 2");

        MyPanel myPanel = new MyPanel();
        window.add(myPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        myPanel.startMainThread();
    }
}