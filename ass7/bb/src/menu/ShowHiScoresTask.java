package menu;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
/**
* A ShowHiScoresTask class.
* @author Hadas Berger <hadasbrgr@gmail.com>
* @version 1.6
* @since 2010-03-31 )
*/
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor ks;
/**
 * constructor.
 * @param runner AnimationRunner
 * @param highScoresAnimation Animation
 * @param ks KeyboardSensor
 */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = runner;
        this.highScoresAnimation = (HighScoresAnimation) highScoresAnimation;
        this.ks = ks;
    }
/**
 * run the animation.
 * @return nothing
 */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        System.out.println("out show");
        return null;
    }
}
