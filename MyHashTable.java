public class MyHashTable<K, V> {
    private HashNode[] chainList;
    private int M = 11;

    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public void setNext(HashNode<K, V> next) {
            this.next = next;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public HashNode<K, V> getNext() {
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
        chainList = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainList = new HashNode[M];
    }

    public int hash(K key) {
        return Math.abs(key.hashCode() % M);
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