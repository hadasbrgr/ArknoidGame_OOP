package menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A MenuAnimation class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 * @param <T> generic
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    // private List<T> options;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private boolean stop;
    private T status;
    private List<OptionTask<T>> option;
    private boolean bool;
/**
 * constructor.
 * @param menuTitle string
 * @param keyboardSensor KeyboardSensor
 * @param animationRunner AnimationRunner
 */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.menuTitle = menuTitle;
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.stop = false;
        this.option = new ArrayList<>();
        this.bool = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(58, 205, 255));
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.BLACK);
        d.fillCircle(100, 130, 50);
        d.fillCircle(120, 130, 50);
        d.setColor(new Color(58, 205, 255));
        d.fillCircle(95, 140, 55);
        d.fillCircle(125, 140, 55);

        d.setColor(Color.BLACK);
        d.fillCircle(60, 140, 50);
        d.fillCircle(80, 140, 50);
        d.setColor(new Color(58, 205, 255));
        d.fillCircle(55, 150, 55);
        d.fillCircle(85, 150, 55);

        d.setColor(Color.BLACK);
        d.fillCircle(700, 550, 50);
        d.fillCircle(720, 550, 50);
        d.setColor(new Color(58, 205, 255));
        d.fillCircle(695, 560, 55);
        d.fillCircle(725, 560, 55);

        d.setColor(Color.BLACK);
        d.fillCircle(660, 580, 50);
        d.fillCircle(680, 580, 50);
        d.setColor(new Color(58, 205, 255));
        d.fillCircle(655, 590, 55);
        d.fillCircle(685, 590, 55);

        d.setColor(new Color(24, 118, 150));
        d.fillRectangle(150, 120, 500, 400);
        d.setColor(Color.white);

        d.drawText(320, 100, this.menuTitle, 60);
        int j = 0;
        for (OptionTask<T> optionString : option) {
            d.drawText(220, 200 + j, optionString.getMessage(), 30);
            j += 120;
        }
        for (OptionTask<T> option1 : option) {
            if (this.keyboardSensor.isPressed(option1.getKey())) {
                this.status = option1.getReturnVal();
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.option.add(new OptionTask<T>(key, message, returnVal));

    }

    @Override
    public T getStatus() {
        this.stop = false;
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.option.add(new OptionTask<T>(key, message, subMenu.getStatus()));
    }

}
