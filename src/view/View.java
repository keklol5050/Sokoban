package view;

import controller.Controller;
import controller.EventListener;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class View extends JFrame {
    private Controller controller;
    private Field field;
    private EventListener eventListener;
    private JLabel levelLabel;

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(field, BorderLayout.CENTER);

        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icon.png")));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton prevLevelButton = new JButton("<");
        JButton nextLevelButton = new JButton(">");

        levelLabel = new JLabel("Level: " + controller.getCurrentLevel());

        prevLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadLevel(controller.getCurrentLevel() - 1);
                updateLevelLabel();
                field.repaint();
            }
        });

        nextLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startNextLevel();
                updateLevelLabel();
                field.repaint();
            }
        });


        controlPanel.add(prevLevelButton);
        controlPanel.add(levelLabel);
        controlPanel.add(nextLevelButton);


        add(controlPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    // Метод для обновления отображаемого уровня
    private void updateLevelLabel() {
        levelLabel.setText("Level: " + controller.getCurrentLevel());
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(this, "Completed level " + level);
        controller.startNextLevel();
    }
}