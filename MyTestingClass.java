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
