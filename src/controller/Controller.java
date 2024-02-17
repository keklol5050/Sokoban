package controller;


import model.*;
import view.View;

public class Controller implements EventListener {
    private Model model;
    private View view;
    public Controller() {
        this.model = new Model();
        this.view = new View(this);
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
    }

    public static void main(String[] args) {
        System.out.println("hello world");
        new Controller();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public int getCurrentLevel() {
        return model.currentLevel;
    }

    public void loadLevel(int level) {
        model.restartLevel(level);
    }

    public void setViewSize(int width, int height) {
        view.setSize(width, height);
    }
}
