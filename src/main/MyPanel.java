package main;

import javax.swing.*;
import java.awt.*;
import entity.Player;
import entity.RandomCircle;

public class MyPanel extends JPanel implements Runnable{

    final int screenWidth = 800;
    final int screenHeight = 600;

    int fps = 60;

    KeyHandler keyHandler = new KeyHandler();

    Thread mainThread;

    Player player = new Player(this,keyHandler);

    public java.util.List<RandomCircle> circles = new java.util.ArrayList<>();

    boolean GameOver = false;

    public MyPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startMainThread(){
        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        //Functie pentru ca jocul sa poata rula la 60fps
        double drawInterval = 1000000000/fps;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;
        while(mainThread!=null){
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
        java.util.Iterator<RandomCircle> iterator = circles.iterator();
        while (iterator.hasNext()) {
            RandomCircle circle = iterator.next();
            circle.update();
            if (circle.raza == 0) {
                iterator.remove();
            }
        }

        // Generam random cercurile pe harta
        if (Math.random() < 0.01) { // Se poate schimba valoarea pentru a controla frecventa cercurilor
            circles.add(new RandomCircle(this));
        }

        if (player.raza <= 0) {
            GameOver = true;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (!GameOver) {
            player.draw(g2);
            // Draw all circles
            for (RandomCircle circle : circles) {
                circle.draw(g2);
            }
        } else {
            // Draw game over screen
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("Game Over", screenWidth / 2 - 100, screenHeight / 2);
        }
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial",Font.BOLD,20));
        g2.drawString("Cercuri mancate: "+player.cercuriMancate,10,20);
        g2.dispose();
    }
}
