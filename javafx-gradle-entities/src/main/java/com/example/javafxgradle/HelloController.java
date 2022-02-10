package com.example.javafxgradle;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Canvas canvas;
    public AnchorPane mainLayout;
    GraphicsContext gc;
    Entity myEntity;
    AnimationTimer mainLoop;
    ArrayList<Entity> entities;
    ArrayList<String> input;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entities = new ArrayList<>();
        input = new ArrayList<>();
       myEntity = new Entity(10, 10, 30, 30, Color.color(Math.random(), Math.random(), Math.random()), 11.0f);
        spawnEntities(10);
        gc = canvas.getGraphicsContext2D();
        Platform.runLater(() -> {
            mainLayout.requestFocus();
        });
        mainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //clear canvas
                clearCanvas();

                //update l
                update();
                checkCollision();

                //render
                render();
            }
        };
        mainLoop.start();
    }

    private void clearCanvas() {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void update() {
        myEntity.update(input, canvas);
    }
    private ArrayList<Entity> neighbours = new ArrayList<>();
    public void add(){
        neighbours.add(new Entity(2,2,2,2, Color.BLACK,5 ));
        neighbours.add(new Entity(8,8,2,2,Color.BLACK,5 ));
        neighbours.add(new Entity(16,16,2,2, Color.BLACK,5 ));
        neighbours.add(new Entity(32,32,2,2, Color.BLACK,5 ));




    }

    private void render() {

        for (int i = 0; i < entities.size()-1; i++) {
            gc.setLineWidth(2.0);
                    gc.moveTo(entities.get(i).getX() + entities.get(i).getW()/2, entities.get(i).getY() + entities.get(i).getH()/2);
                    gc.lineTo(entities.get(i+1).getX() + entities.get(i+1).getW()/2, entities.get(i+1).getY() + entities.get(i+1).getH()/2);
                    gc.stroke();
            gc.setStroke(Color.BLACK);
        }
        entities.forEach(obj ->{
            obj.render(gc);
        });
    }

    private void spawnEntities(int n) {
        for (int i = 0; i < n; i++) {
            entities.add(new Entity(generate(0, canvas.getWidth()), generate(0, canvas.getHeight()), generate(30, 30), generate(30, 30), Color.color(Math.random(), Math.random(), Math.random()), 1.1f));
        }
    }

    private int generate(int min, double max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private void checkCollision() {
        entities.removeIf(obj -> myEntity.getX() < obj.getX() + obj.getW() &&
                myEntity.getX() + myEntity.getW() > obj.getX() &&
                myEntity.getY() < obj.getY() + obj.getH() &&
                myEntity.getH() + myEntity.getY() > obj.getY());
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (!input.contains(keyEvent.getText())) {
            input.add(keyEvent.getText());
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        input.remove(keyEvent.getText());
    }
}