import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                }
                return 0;

//                return o1.compareTo(o2);
            }
        };

        Queue<Integer> intQueue = new PriorityQueue<>(10, comparator);
        intQueue.add(1);
        intQueue.add(3);
        intQueue.add(4);
        while (!intQueue.isEmpty()) {
            System.out.println("size "+intQueue.size());
            System.out.println(intQueue.remove());
            System.out.println("size "+intQueue.size());
        }
    }
}
