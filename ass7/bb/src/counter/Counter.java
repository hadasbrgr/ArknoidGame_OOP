package counter;

/**
 * A Counter class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class Counter {
    private int count;

    /**
     * a constructor to Counter.
     * @param count int
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     * @param number to add
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number from current count.
     * @param number to minus
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * get current count.
     * @return the current value
     */
    public int getValue() {
        return this.count;
    }
}