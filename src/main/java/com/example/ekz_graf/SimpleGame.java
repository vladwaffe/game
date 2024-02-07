package com.example.ekz_graf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
public class SimpleGame extends JPanel implements ActionListener, KeyListener {
    private boolean showText = true;
    Enemy enemy;
    int stars_counter = 0;
    private int playerX = 175;  // Начальное положение игрока по горизонтали
    private int playerY = 480;
    private int playerSpeed = 15;
    Cube cube_player = new Cube(0, 190);
    static SimpleGame game = new SimpleGame();
    Field field = new Field();
    private ArrayList<Enemy> enemis = new ArrayList<>();
    private ArrayList<Stars> stars = new ArrayList<>();
    private int enemySpeed = 15;
    private Timer timer;
    private boolean gameOver = false;
    private boolean startPage = true;

    private int score = 0;
    public SimpleGame() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(100, this);  // Тут создаем таймер

        Timer timer_text = new Timer(500, e -> {
            showText = !showText;
            repaint();
        });
        timer_text.start();
    }

    public void paintComponent(Graphics g) {

        if(startPage){
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 400, 600);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("Начать игру", 90, 300);
            if(showText) {
                g.setFont(new Font("Arial", Font.PLAIN, 15));
                g.drawString("press enter", 160, 320);
            }
        }else {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 400, 600);
            for (int i = 0; i < stars.size(); i++) {
                stars.get(i).draw(g);
            }
            field.draw(g);
            for (int i = 0; i < enemis.size(); i++) {
                enemis.get(i).project(g);
            }
            cube_player.project(g);


            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Счет: " + score, 10, 30);  // Выводим счет игрока на экран
            if (gameOver) {
                g.setFont(new Font("Arial", Font.PLAIN, 40));
                g.drawString("Конец игры", 90, 300);  // Выводим надпись "Конец игры" при окончании игры
                timer.stop();  // Останавливаем таймер
            }

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            for (int i = 0; i < enemis.size(); i++) {
                switch (enemis.get(i).getPlase()) {
                    case 1: {
                        enemis.get(i).move(-2, 9);
                        enemis.get(i).further(18);
                        if (enemis.get(i).llb.to2D().getY() > 600) {
                            enemis.remove(0);
                            score++;
                        }
                        break;
                    }
                    case 2: {
                        enemis.get(i).move(-1, 9);
                        enemis.get(i).further(18);
                        if (enemis.get(i).llb.to2D().getY() > 600) {
                            enemis.remove(0);
                            score++;
                        }
                        break;
                    }
                    case 3: {
                        enemis.get(i).move(0, 9);
                        enemis.get(i).further(18);
                        if (enemis.get(i).llb.to2D().getY() > 600) {
                            enemis.remove(0);
                            score++;
                        }
                        break;
                    }
                    case 4: {
                        enemis.get(i).move(1, 9);
                        enemis.get(i).further(18);
                        if (enemis.get(i).llb.to2D().getY() > 600) {
                            enemis.remove(0);
                            score++;
                        }
                        break;
                    }
                    case 5: {
                        enemis.get(i).move(2, 9);
                        enemis.get(i).further(18);
                        if (enemis.get(i).llb.to2D().getY() > 600) {
                            enemis.remove(0);
                            score++;
                        }
                        break;
                    }
                }
            }
            for(int i = 0; i < stars.size(); i++){
                stars.get(i).motion(5);
                if(stars.get(i).getY() > 600){
                    stars.remove(0);
                }
            }
            repaint();
            /*if(stars.isEmpty() || stars.get(0).getY() > 150){
                spawnStars();
            }*/
            if(stars_counter == 10){
                spawnStars();
                stars_counter = 0;
            }else {
                stars_counter++;
            }
            if (enemis.isEmpty()){
                spawnEnemy();
            }
            checkCollision();
        }

    }
    public void spawnEnemy() {
        Random rand = new Random();
        int numEnemies = rand.nextInt(4) + 1;
        for (int i = 0; i < numEnemies; i++) {
            int x = rand.nextInt(-50, 50);
            int y = -150;
            if(x >= -50 && x <= -25){
                enemy = new Enemy(0, -150, 1);
            }else if (x > -25 && x < 0) {
                enemy = new Enemy(0, -150, 2);
            }else if (x == 0) {
                enemy = new Enemy(0, -150, 3);
            }else if (x > 0 && x < 25) {
                enemy = new Enemy(0, -150, 4);
            }else if (x >= 25 && x <= 50) {
                enemy = new Enemy(0, -150, 5);
            }
            enemis.add(enemy);
        }
    }
    public void spawnStars(){
        Random rand = new Random();
        int numStars = rand.nextInt(8) + 5;

        for (int i = 0; i < numStars; i++) {
            int x = rand.nextInt(400);
            int y = 0 - i * 5 ;
            Stars star = new Stars(x, y);
            stars.add(star);
        }
    }
    public void checkCollision() {
        Rectangle playerBounds = new Rectangle(playerX, playerY, 45, 45);  // Границы игрока
        for(int i = 0; i < enemis.size(); i++){
            Rectangle enemyBounds1 = new Rectangle((int) enemis.get(i).ulf.to2D().getX(), (int) enemis.get(i).ulf.to2D().getY(), 30, 30);
            if (playerBounds.intersects(enemyBounds1)) {
                gameOver = true;  // Если произошло столкновение, игра заканчивается
                break;
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!gameOver) {
            if (key == KeyEvent.VK_LEFT && playerX > 0) {
                playerX -= playerSpeed;  // Перемещаем игрока влево
                cube_player.move(-playerSpeed, 0);
            }
            if (key == KeyEvent.VK_RIGHT && playerX < 350) {
                playerX += playerSpeed;  // Перемещаем игрока вправо
                cube_player.move(+playerSpeed, 0);
            }
            if(key == KeyEvent.VK_ENTER){
                startPage = false;
                timer.start();  // В этой строчке его запускаем
            }
           /* if(key == KeyEvent.VK_ENTER && gameOver){
                *//*try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }*//*
                startPage = true;
                timer.start();
            }*/

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}





}