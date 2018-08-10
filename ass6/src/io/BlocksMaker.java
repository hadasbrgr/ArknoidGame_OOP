package io;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

import geometry.Point;
import geometry.Rectangle;
import sprites.Block;

/**
 * A BlocksMaker class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class BlocksMaker implements BlockCreator {

    private int height;
    private int width;
    private Color strokeColor;
    private int hitPoints;
    private Map<Integer, Color> fillBlockColor;
    private Map<Integer, Image> fillBlockImage;

    /**
     * @param height int
     * @param width int
     * @param strokeColor color
     * @param hitPoints int
     */
    public BlocksMaker(int height, int width, Color strokeColor, int hitPoints) {
        this.height = height;
        this.width = width;
        this.strokeColor = strokeColor;
        this.hitPoints = hitPoints;
    }

    /**
     * defult cpnstructor.
     */
    public BlocksMaker() {
        this.height = 0;
        this.width = 0;
        this.strokeColor = null;
        this.hitPoints = 0;
        this.fillBlockColor = new TreeMap<>();
        this.fillBlockImage = new TreeMap<>();
    }

    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rect = new Rectangle(new Point(xpos, ypos), width, height);
        return new Block(rect, fillBlockColor, fillBlockImage, strokeColor, hitPoints);
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height1 the height to set
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width1 the width to set
     */
    public void setWidth(int width1) {
        this.width = width1;
    }

    /**
     * @return the strokeColor
     */
    public Color getStrokeColor() {
        return strokeColor;
    }

    /**
     * @param strokeColor1 the strokeColor to set
     */
    public void setStrokeColor(Color strokeColor1) {
        this.strokeColor = strokeColor1;
    }

    /**
     * @return the hit_points
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * @param hitPoints1 the hit_points to set
     */
    public void setHitPoints(int hitPoints1) {
        this.hitPoints = hitPoints1;
    }

    /**
     * @param fillBlockColor1 the fillBlockColor to set
     */
    public void setFillBlockColor(Map<Integer, Color> fillBlockColor1) {
        this.fillBlockColor = fillBlockColor1;
    }

    /**
     * @param fillBlockImage1 the fillBlockImage to set
     */
    public void setFillBlockImage(Map<Integer, Image> fillBlockImage1) {
        this.fillBlockImage = fillBlockImage1;
    }

}
