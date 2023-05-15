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
        putRecursive(key, value, chainList, 0);
    }

    private void putRecursive(K key, V value, HashNode<K, V>[] chainList, int index) {
        if (index >= chainList.length) {
            return;
        }
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainList[index] == null) {
            chainList[index] = newNode;
            return;
        }
        putRecursive(key, value, chainList, index + 1);
    }
    public V get(K key) {
        return (V) getRecursive(key, chainList, 0);
    }

    private V getRecursive(K key, HashNode<K, V>[] chainList, int index) {
        if (index >= chainList.length) {
            return null;
        }
        HashNode<K, V> node = chainList[index];
        if (node != null && node.getKey().equals(key)) {
            return node.getValue();
        }
        return getRecursive(key, chainList, index + 1);
    }

    public V remove(K key) {
        return (V) removeRecursive(key, chainList, 0, null);
    }