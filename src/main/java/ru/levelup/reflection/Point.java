package ru.levelup.reflection;

public class Point {

    public int y;

    private int x;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(int x){
        this.x = x;
    }

    private Point(){

    }

    private void changeYValue(int y){
        this.y = y;
    }

    public void print(){
        System.out.println("(" + x +", " + y +")");
    }

}
