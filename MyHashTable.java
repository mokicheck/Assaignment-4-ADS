import java.util.Scanner;

public class Main {
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