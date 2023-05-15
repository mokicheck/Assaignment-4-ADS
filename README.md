# Assaignment-4-ADS

------

# Main [Link](Main.java)
```java
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> table = new MyHashTable<>();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Enter your choice:");
            System.out.println("1. Put (key, value)\n2. Get value by key\n3. Remove by key\n4. Check if value exists\n5. Get key by value");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter key:");
                    String key = scanner.next();
                    System.out.println("Enter value:");
                    int value = scanner.nextInt();
                    table.put(key, value);
                    System.out.println("Value inserted!");
                    break;
                case 2:
                    System.out.println("Enter key:");
                    key = scanner.next();
                    Integer result = table.get(key);
                    if (result != null) {
                        System.out.println("Value: " + result);
                    } else {
                        System.out.println("Key not found!");
                    }
                    break;
                case 3:
                    System.out.println("Enter key:");
                    key = scanner.next();
                    Integer removedValue = table.remove(key);
                    if (removedValue != null) {
                        System.out.println("Value removed: " + removedValue);
                    } else {
                        System.out.println("Key not found!");
                    }
                    break;
                case 4:
                    System.out.println("Enter value:");
                    value = scanner.nextInt();
                    boolean contains = table.contains(value);
                    System.out.println("Value exists: " + contains);
                    break;
                case 5:
                    System.out.println("Enter value:");
                    value = scanner.nextInt();
                    String foundKey = table.getKey(value);
                    if (foundKey != null) {
                        System.out.println("Key: " + foundKey);
                    } else {
                        System.out.println("No such value.");
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }

        System.out.println("Goodbye!");
    }
}
```

---------

# Class MyTestingClass [Link](MyTestingClass.java)
```java
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
```
