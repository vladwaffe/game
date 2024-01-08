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
    Enemy enemy;
    private int playerX = 175;  // Начальное положение игрока по горизонтали
    private int playerY = 480;  // Начальное положение игрока по вертикали
    private int playerSpeed = 15;  // Скорость движения игрока
    Cube cube_player = new Cube(0, 190);
    Cube field = new Cube(0, 0);
    private ArrayList<Integer> enemyX = new ArrayList<>();  // X-координаты врагов
    private ArrayList<Integer> enemyY = new ArrayList<>();  // Y-координаты врагов
    private ArrayList<Enemy> enemis = new ArrayList<>();
    private int enemySpeed = 15;  // Скорость движения врагов
    private Timer timer;  // Таймер для обновления экрана
    private boolean gameOver = false;  // Флаг окончания игры
    private int score = 0;  // Счет игрока
    public SimpleGame() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(100, this);  // Тут создаем таймер
        timer.start();  // В этой строчке его запускаем
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game");
        SimpleGame game = new SimpleGame();
        frame.add(game);
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        g.setColor(Color.BLACK); // Заливаем фон черным цветом
        g.fillRect(0, 0, 400, 600);






        g.setColor(Color.WHITE); // Белый цвет для фигуры игрока
        g.fillRect(199, 299, 2, 2);




        /*for (int i = 0; i < enemyX.size(); i++) {
            g.setColor(Color.RED); // Используем красный цвет
            g.fillOval(enemyX.get(i), enemyY.get(i), 20, 20);
        }*/
        for (int i = 0; i < enemis.size(); i++){
            enemis.get(i).project(g);
        }
        cube_player.project(g);



        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Счет: " + score, 10, 30);  // Выводим счет игрока на экран
        if (gameOver) {
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("Конец игры", 120, 300);  // Выводим надпись "Конец игры" при окончании игры
            timer.stop();  // Останавливаем таймер
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
                        enemis.get(i).move(1, 9);
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
            repaint();
            if (enemis.isEmpty()){
                spawnEnemy();
            }
            checkCollision();
        }

    }
    public void spawnEnemy() {
        Random rand = new Random();
        int numEnemies = rand.nextInt(4) + 1; // Генерируем от 1 до 3 врагов за раз
        for (int i = 0; i < numEnemies; i++) {
            //int x = rand.nextInt(350); // Генерируем случайную X-координату для врага
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
    public void checkCollision() {
        Rectangle playerBounds = new Rectangle(playerX, playerY, 50, 50);  // Границы игрока
        /*for (int i = 0; i < enemyX.size(); i++) {
            Rectangle enemyBounds = new Rectangle(enemyX.get(i), enemyY.get(i), 20, 20);  // Границы врага
            if (playerBounds.intersects(enemyBounds)) {
                gameOver = true;  // Если произошло столкновение, игра заканчивается
                break;
            }
        }*/
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
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}





}