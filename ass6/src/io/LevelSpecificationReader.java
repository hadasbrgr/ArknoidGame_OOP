package io;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import collision.Velocity;
import levels.Background;
import levels.Level;
import levels.LevelInformation;
import sprites.Block;
/**
 * A LevelSpecificationReader class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class LevelSpecificationReader {
    private BlocksFromSymbolsFactory blocksFromSymbolsFactory;

    /**
     * constructor defult.
     */
    public LevelSpecificationReader() {
    }
/**
 * @param reader reader
 * @return list of level information
 */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> listOfLevels = new ArrayList<LevelInformation>();
        List<String> listOfblock = new ArrayList<String>();
        blocksFromSymbolsFactory = null;
        try {
            listOfblock = separateToLevels(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listOfblock.size(); i++) {
            listOfLevels.add(createLevels(listOfblock.get(i)));
        }
        return listOfLevels;
    }
/**
 * separate To Levels.
 * @param reader reader
 * @return list of string
 * @throws FileNotFoundException exception
 */
    public List<String> separateToLevels(java.io.Reader reader) throws FileNotFoundException {
        List<String> list = new ArrayList<String>();
        BufferedReader is = null;
        is = new BufferedReader(reader);
        String thisLine = null;
        StringBuilder s = new StringBuilder();
        try {
            while ((thisLine = is.readLine()) != null) {
                if ((thisLine.equals("START_LEVEL"))) {
                    continue;
                }
                if ((thisLine.equals("END_LEVEL"))) {
                    list.add(s.toString());
                    s = new StringBuilder(); // null the level
                    continue;
                }
                s.append(thisLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
/**
 * create the level.
 * @param string string
 * @return level information.
 */
    public LevelInformation createLevels(String string) {
        List<Block> blocks = new ArrayList<Block>();
        Level level = new Level();
        boolean inBlock = false;
        String[] lines = string.trim().split("\n");
        int xpos = 0;
        int ypos = 0;
        for (String oneLine : lines) {
            if (oneLine.equals("START_BLOCKS")) {
                inBlock = true;
                xpos = level.getBlocksStartX();
                ypos = level.getBlocksStartY();
                continue;
            }
            if (oneLine.equals("END_BLOCKS")) {
                inBlock = false;
            } else if (inBlock) {
                String[] oneChar;
                if (oneLine.length() == 1) {
                    if (blocksFromSymbolsFactory.isSpaceSymbol(oneLine)) {
                        ypos += level.getRowHeight();
                    }
                } else {
                    oneChar = oneLine.split("");
                    for (int j = 0; j < oneChar.length; j++) {
                        if (blocksFromSymbolsFactory.isSpaceSymbol(oneChar[j])) {
                            xpos += blocksFromSymbolsFactory.getSpaceWidth(oneChar[j]);
                        }
                        if (blocksFromSymbolsFactory.isBlockSymbol((oneChar[j]))) {
                            blocks.add(blocksFromSymbolsFactory.getBlock(oneChar[j], xpos, ypos));
                            xpos += blocksFromSymbolsFactory.getBlock(oneChar[j], xpos, ypos).getWidth();
                        }
                    }
                    ypos += level.getRowHeight();
                    xpos = level.getBlocksStartX();
                }
            } else {
                String[] parts = oneLine.trim().split(":");
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    checkOptions(key, value, level);
                }
            }
        }
        level.setBlocks(blocks);
        if (level.levelName() == null || level.numberOfBalls() == 0 || level.getBackground() == null
                || level.numberOfBlocksToRemove() == 0 || level.paddleSpeed() == 0 || level.paddleWidth() == 0
                || level.getPaddleHeight() == 0 || level.getBlocksStartX() == 0 || level.getBlocksStartY() == 0
                || level.getRowHeight() == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return level;
    }
/**
 * get key and value and check the option and update the level accordenly.
 * @param key string
 * @param value string
 * @param level level
 */
    public void checkOptions(String key, String value, Level level) {
        level.setPaddleHeight(25);
        if (key.equals("level_name")) {
            level.setLevelName(value);
        }
        if (key.equals("ball_velocities")) {
            int numberOfBalls = 0;
            String[] velocities = value.split(" ");
            for (String velocity : velocities) {
                String[] velocity1 = velocity.split(",");
                if (!(velocity1.length == 2)) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String angle = velocity1[0];
                String speed = velocity1[1];
                level.addInitialBallVelocities(
                        Velocity.fromAngleAndSpeed(Double.valueOf(angle), Double.valueOf(speed)));
                numberOfBalls++;
            }
            level.setNumberOfBalls(numberOfBalls);
        }
        if (key.equals("background")) {
            if (value.startsWith("image(")) {
                value = value.substring("image(".length());
                value = value.replace(")", "");
                InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream(value);
                System.out.println(value);
                Image image = null;
                try { // trying reading the image
                    image = ImageIO.read(imageIS);
                } catch (IOException e) {
                    e.printStackTrace();
                } // put the image in the map
                if (image != null) {
                    Background background = new Background(image);
                    level.setBackground(background);
                }
            } else if (value.startsWith("color(")) {
                Color color = ColorsParser.colorFromString(value);
                Background background = new Background(color);
                level.setBackground(background);
            } else {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (key.equals("paddle_speed")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new NumberFormatException();
                }
                level.setPaddleSpeed(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (key.equals("paddle_width")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new NumberFormatException();
                }
                level.setPaddleWidth(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (key.equals("block_definitions")) {
            InputStream reader1 = null;
            BufferedReader r = null;
            reader1 = ClassLoader.getSystemClassLoader().getResourceAsStream(value);
            this.blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(new InputStreamReader(reader1));
        }
        if (key.equals("blocks_start_x")) {
            try {
                if (Integer.valueOf(value) < 0) {
                    throw new NumberFormatException();
                }
                level.setBlocksStartX(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (key.equals("blocks_start_y")) {
            try {
                if (Integer.valueOf(value) < 0) {
                    throw new NumberFormatException();
                }
                level.setBlocksStartY(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (key.equals("row_height")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new NumberFormatException();
                }
                level.setRowHeight(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (key.equals("num_blocks")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new NumberFormatException();
                }
                level.setNumberOfBlocksToRemove(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                try {
                    throw new Exception();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
