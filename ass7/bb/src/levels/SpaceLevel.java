package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import collision.Velocity;
import sprites.Block;
import sprites.Sprite;
/**
 * A SpaceLevel class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class SpaceLevel implements LevelInformation {

    @Override
    public List<Velocity> initialAliensVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 300;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Battle no.";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.black);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Image image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("block_images/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, Image> map = new TreeMap<Integer, Image>();
        map.put(1, image);
        int counter = 0;
        int y = 40;
        for (int i = 0; i < 5; i++) {
            int x = 20;
            for (int j = 0; j < 10; j++) {
                Block block = new Block();
                block.setImages(map);
                block.setNumOfHit(1);
                block.setRectwidth(40);
                block.setRectHeight(30);
                block.setUpperLeft(x, y);
                block.setPosition(counter);
                blocks.add(block);
                x += 50;
                counter++;
            }
            y += 40;
        }
        return blocks;
    }

    @Override
    public int numberOfAliensToRemove() {
        return 50;
    }

}
