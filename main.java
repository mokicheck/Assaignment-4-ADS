import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> table = new MyHashTable<>();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Enter your choice:");
            System.out.println("1. Put (key, value)\n2. Get value by key\n3. Remove by key\n4. Check if value exists\n5. Get key by value");
            System.out.println("6. Copy elements to empty hash table");
            System.out.println("7. Exit");

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
                    // Create the second hash table
                    MyHashTable<String, Integer> emptyHashTable = new MyHashTable<>();
                    // Copy elements from the first hash table to the second hash table
                    emptyHashTable(table, emptyHashTable);
                    System.out.println("Elements copied to the empty hash table.");
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }

        System.out.println("Goodbye!");
    }
/*
  @source - The hash table to copy elements from.
  @destination -  The hash table to copy elements to.
  @<K> - The type of keys in the hash tables.
  @<V> - The type of values in the hash tables.
 */
    public static <K, V> void emptyHashTable(MyHashTable<K, V> source, MyHashTable<K, V> destination) {
        for (int i = 0; i < source.capacity; i++) {
            MyHashTable.Node<K, V> currentNode = source.table[i];
            while (currentNode != null) {
                destination.put(currentNode.getKey(), currentNode.getValue());
                currentNode = currentNode.getNext();
            }
        }
    }
}
