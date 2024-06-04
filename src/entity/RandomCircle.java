package entity;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

import main.MyPanel;

public class RandomCircle extends Entity{
    private static final Random random = new Random();

    //Lista de culori posibile
    private static final Color[] possibleColors = {Color.RED,Color.GREEN,Color.BLUE,Color.yellow};

    MyPanel panel;

    public RandomCircle(MyPanel panel){
        this.panel=panel;
        generateRandomCircle();
        update();
    }

    private void generateRandomCircle(){
        //Setam coordonatele cercurilor sa fie aleatorii
        this.xCentru=random.nextInt(500);
        this.yCentru=random.nextInt(500);
        this.raza=20;

        //Setam o culoare aleatorie din lista de culori
        this.culoare=possibleColors[random.nextInt(possibleColors.length)];
    }

    public void update() {
        //Raza cercurilor sa scada in timp
        raza-=0.1;
        if(raza<=0){
            //Sa se genereze un cerc nou dupa ce dispare
            generateRandomCircle();
            update();
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(culoare);
        g2.fillOval((int) (xCentru-raza), (int) (yCentru-raza), (int) (raza*2), (int) (raza*2));
    }
}

