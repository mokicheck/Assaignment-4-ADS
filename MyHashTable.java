public class MyHashTable<K, V> {
    Node<K, V>[] table;
    int capacity;

    static class Node<K, V> {
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
