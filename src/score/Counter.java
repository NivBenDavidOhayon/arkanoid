// 213394364 Niv Ben David Ohayone
package score;

/**
 * representation of a simple counter that can be increased or decreased by a
 * specified number, and provides a method to retrieve the current count value.
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     *
     * @param count - the current count to set to the counter
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     *
     * @param number the number of the count
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number of the count
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets the current value of the count.
     *
     * @return the value
     */

    public int getValue() {
        return this.count;
    }
}