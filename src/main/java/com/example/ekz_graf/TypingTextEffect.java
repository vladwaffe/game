package com.example.ekz_graf;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TypingTextEffect extends Application {

    @Override
    public void start(Stage stage) {
        // Создание текста
        Text text = new Text();
        text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        text.setX(20);
        text.setY(50);
        text.setFill(Color.WHITE);

        // Установка начального состояния текста
        text.setText("");

        // Создание анимации набора текста
        String fullText = "Hello, JavaFX!";
        Timeline timeline = new Timeline();
        for (int i = 0; i < fullText.length(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.1), event -> {
                text.setText(fullText.substring(0, index + 1));
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        // Запуск анимации
        timeline.play();

        // Создание группы и добавление текста в нее
        Group root = new Group(text);

        // Создание сцены и добавление группы на сцену
        Scene scene = new Scene(root, 400, 200, Color.DARKBLUE);

        // Установка сцены на окно приложения
        stage.setScene(scene);

        // Отображение окна приложения
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}