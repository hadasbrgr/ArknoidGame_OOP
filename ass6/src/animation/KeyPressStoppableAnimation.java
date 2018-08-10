package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A KeyPressStoppableAnimation class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor KeyPressStoppableAnimation.
     * @param sensor KeyboardSensor
     * @param key String
     * @param animation Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);

        if (this.sensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;

        }
        if (!this.sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}