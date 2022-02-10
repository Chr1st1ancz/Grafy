package com.example.javafxgradle;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class Node {

    private ArrayList<Node> neighbours = new ArrayList<>();
    private double y;
    private double x;
    private double w;
    private double h;

    public void add(){
        neighbours.add(new Node(2,2,2,2));
        neighbours.add(new Node(8,8,2,2));
        neighbours.add(new Node(16,16,2,2));
        neighbours.add(new Node(32,32,2,2));




    }
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Node(double y, double x, double w, double h) {
        this.y = y;
        this.x = x;
        this.w = w;
        this.h = h;

    }

}
