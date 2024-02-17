package model;

import model.gameobjects.Box;
import model.gameobjects.Home;
import model.gameobjects.Player;
import model.gameobjects.Wall;

import java.io.*;
import java.util.HashSet;

public class LevelLoader {
    private byte[] levelBytes;

    public LevelLoader(InputStream levels) {
        try {
            levelBytes = levels.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameObjects getLevel(int level) {
        if (level > 60)
            level = level%60;

        HashSet<Home> homes = new HashSet<Home>();
        HashSet<Box> boxes = new HashSet<Box>();
        HashSet<Wall> walls = new HashSet<Wall>();
        Player player = null;

        InputStream levelStream = new ByteArrayInputStream(levelBytes);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(levelStream))) {
            int readLevel = 0;
            do {
                String line = reader.readLine();
                if (line.contains("Maze")) {
                    String[] maze = line.split(":");
                    readLevel = Integer.parseInt(maze[1].trim());
                } 
            } while (readLevel != level);
            int x;
            int y = Model.FIELD_CELL_SIZE/2;

            String line;
            boolean gotLevel = false;
            while ((line = reader.readLine()) != null) {
                if (!gotLevel) {
                    if (line.trim().isEmpty()) {
                        gotLevel = true;
                    }
                    continue;
                }
                if (line.trim().isEmpty()) break;
                x = Model.FIELD_CELL_SIZE / 2;
                char[] objs = line.toCharArray();
                for (char c : objs) {
                    switch (c) {
                        case 'X':
                            walls.add(new Wall(x, y));
                            break;
                        case '*':
                            boxes.add(new Box(x, y));
                            break;
                        case '.':
                            homes.add(new Home(x, y));
                            break;
                        case '&':
                            boxes.add(new Box(x, y));
                            homes.add(new Home(x, y));
                            break;
                        case '@':
                            player = new Player(x, y);
                    }
                    x += Model.FIELD_CELL_SIZE;;
                }
                y += Model.FIELD_CELL_SIZE;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
