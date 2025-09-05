import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {
    protected Random random = new Random();
    protected final int min;
    protected final int max;

    public Randoms(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return random.nextInt(min, max + 1);
            }
        };
    }
}