package entity;

import main.KeyHandler;
import main.MyPanel;

import java.awt.*;

public class Player extends Entity{

    MyPanel panel;
    KeyHandler handler;
    public int speed;
    public int cercuriMancate;

    public Player(MyPanel panel, KeyHandler handler){
        this.panel=panel;
        this.handler=handler;

        setDefaultValues();
    }

    public void setDefaultValues(){
        xCentru=400;
        yCentru=300;
        speed=3;
        raza=50;
        cercuriMancate=0;
        culoare = Color.RED;
    }

    public void update(){
        if(handler.upPressed){
            yCentru-=speed;
        }
        if(handler.downPressed){
            yCentru+=speed;
        }
        if(handler.leftPressed){
            xCentru-=speed;
        }
        if(handler.rightPressed){
            xCentru+=speed;
        }

        // Verificam coliziunea cu cercurile
        java.util.Iterator<RandomCircle> iterator = panel.circles.iterator();
        while (iterator.hasNext()) {
            RandomCircle circle = iterator.next();
            double distance = Math.sqrt(Math.pow(circle.xCentru - xCentru, 2) + Math.pow(circle.yCentru - yCentru, 2));
            if (distance < raza + circle.raza) { // S-a detectat coliziunea
                if (circle.culoare.equals(culoare)) { // Aceeasi culoare
                    raza+=10; // Crestem raza
                    cercuriMancate++; // Crestem numarul de cercuri mancate
                } else { // Culoare diferita
                    raza-=10; // Scadem raza
                }
                iterator.remove(); // Distrugem cercul cu care s-a intersectat jucatorul
            }
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(culoare);
        g2.fillOval((int) (xCentru-raza), (int) (yCentru-raza), (int) (raza*2), (int) (raza*2));
    }
}
