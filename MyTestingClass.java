import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyTestingClass {
    private int x;
    private String y;

    public MyTestingClass(int x, String y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    static List<Integer> bucketSizes = new ArrayList<>();
    static MyHashTable<MyTestingClass, String> ht = new MyHashTable<>();
    static Random random = new Random();

    public static void main(String[] args) {
        int count = 999999999;
        addElements(count);
        printBucketSizes();
    }

    private static void addElements(int count) {
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(100);
            String y = "Element_" + x;
            MyTestingClass key = new MyTestingClass(x, y);
            ht.put(key, y);
            int index = ht.hash(key);
            if (index >= bucketSizes.size()) {
                for (int j = bucketSizes.size(); j <= index; j++) {
                    bucketSizes.add(0);
                }
            }
            bucketSizes.set(index, bucketSizes.get(index) + 1);
        }
    }

    private static void printBucketSizes() {
        int sum = 0;
        for (int index = 0; index < bucketSizes.size(); index++) {
            int size = bucketSizes.get(index);
            System.out.println("Bucket " + index + ": " + size + " elements");
            sum += size;
            if (sum >= 10000) {
                System.out.println("Sum of all elements is equal to or greater than 10000");
                break;
            }
        }
    }
}
