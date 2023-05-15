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

The code I provided demonstrates a basic interactive console-based program that allows the user to perform various operations on a custom MyHashTable data structure. This code allows the user to interactively perform operations such as inserting key-value pairs, retrieving values by keys, removing key-value pairs, checking if a value exists, and finding keys by values using the MyHashTable data structure.

--------

# Class MyHashTable [Link](MyHashTable.java)
``` java
public class MyHashTable<K, V> {
    private Node<K, V>[] table;
    private int capacity;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    public MyHashTable() {
        capacity = 11;
        table = new Node[capacity];
    }

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        table = new Node[capacity];
    }

    public int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> currentNode = table[index];
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Node<K, V> currentNode = table[index];
        Node<K, V> prevNode = null;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                if (prevNode == null) {
                    table[index] = currentNode.getNext();
                } else {
                    prevNode.setNext(currentNode.getNext());
                }
                return currentNode.getValue();
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < capacity; i++) {
            Node<K, V> currentNode = table[i];
            while (currentNode != null) {
                if (currentNode.getValue().equals(value)) {
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < capacity; i++) {
            Node<K, V> currentNode = table[i];
            while (currentNode != null) {
                if (currentNode.getValue().equals(value)) {
                    return currentNode.getKey();
                }
                currentNode = currentNode.getNext();
            }
        }
        return null;
    }
}
```

The code represents a basic implementation of a hash table data structure called `MyHashTable`. Here's a short description of the code:

- The `MyHashTable` class is a generic class that takes two type parameters `K` and `V` representing the key and value types, respectively.
- The class contains an array of `Node<K, V>` objects called `table` to store the key-value pairs.
- The inner static class `Node<K, V>` represents a node in the hash table, storing a key, value, and a reference to the next node.
- The class provides constructors to initialize the hash table with a default capacity or a specified capacity.
- The `hash` method takes a key as input and returns the hashed index based on the key's hash code and the capacity of the table.
- The `put` method allows inserting a key-value pair into the hash table. It calculates the index using the `hash` method, creates a new node, and appends it to the end of the linked list at the specified index.
- The `get` method retrieves the value associated with a given key from the hash table. It calculates the index using the `hash` method and traverses the linked list at that index to find the matching key.
- The `remove` method removes a key-value pair from the hash table based on the provided key. It calculates the index using the `hash` method and traverses the linked list at that index to find and remove the node with the matching key.
- The `contains` method checks if a given value exists in the hash table. It iterates over each index in the table and traverses the linked list at each index to find the matching value.
- The `getKey` method retrieves the key associated with a given value in the hash table. It iterates over each index in the table and traverses the linked list at each index to find the matching value and return its corresponding key.

Overall, this code provides basic operations for inserting, retrieving, removing, and searching key-value pairs in a hash table data structure.


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

The provided code demonstrates the usage of a hash table implemented in the `MyHashTable` class. It also includes a `MyTestingClass` that is used as a key in the hash table. The code performs the following tasks:

1. `MyTestingClass` is a class representing an object with an integer `x` and a string `y` as its properties. It overrides the `hashCode()` method to generate a hash code based on `x` and `y`.

2. The code initializes a static list called `bucketSizes` to keep track of the sizes of each bucket in the hash table. It also creates an instance of the `MyHashTable` class called `ht` and a `Random` object called `random`.

3. The `main` method is the entry point of the program. It sets the value of `count` and then calls the `addElements` method to add elements to the hash table, and finally, calls the `printBucketSizes` method to print the sizes of each bucket in the hash table.

4. The `addElements` method adds `count` number of elements to the hash table. It generates a random integer `x` and creates a string `y` based on `x`. It then creates a new `MyTestingClass` object as the key and adds it to the hash table using the `put` method. It also keeps track of the sizes of each bucket by updating the `bucketSizes` list.

5. The `printBucketSizes` method iterates over the `bucketSizes` list and prints the size of each bucket. It also calculates the sum of all bucket sizes and checks if it exceeds or equals 10,000. If the sum reaches or exceeds 10,000, it prints a message indicating this and breaks the loop.

Overall, this code demonstrates the usage of a custom hash table implementation and provides insights into the distribution of elements across the buckets in the hash table.
