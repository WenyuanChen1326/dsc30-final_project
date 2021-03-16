import java.util.*;

@SuppressWarnings("rawtypes")
public class HashTable<K, D> {

    protected class TableEntry<K, D> {
        private K key;
        private D data;

        public TableEntry(K key, D data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public boolean equals(Object obj) {
            if ((obj == null) || !(obj instanceof TableEntry))
                return false;
            return key.equals(((TableEntry<?, ?>) obj).key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }


    }

    private LinkedList<TableEntry<K, D>>[] table;
    private int nElems;


    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < 10) {
            throw new IllegalArgumentException();
        }
        table = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        nElems = 0;

    }

    public boolean insert(K key, D data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (lookup(key) != null) {
            return false;
        }
        if (nElems / (double) table.length > 2/(double)3) {
            rehash();
        }
        LinkedList<TableEntry<K, D>> bucketList = table[hashValue(key)];
        TableEntry<K,D>pair = new TableEntry<>(key, data);
        bucketList.add(pair);
        nElems++;
        return true;
    }

    public boolean update(K key, D newData) {
        if (key == null || newData == null) {
            throw new NullPointerException();
        }
        if (lookup(key) == null) {
            return false;
        }
        LinkedList<TableEntry<K, D>> bucketList = table[hashValue(key)];
        TableEntry<K,D> newPair = new TableEntry<>(key, newData);
        for (int i = 0; i < bucketList.size(); i++) {
            TableEntry curPair = bucketList.get(i);
            //this or the one below
            if (curPair.equals(newPair)) {
                curPair.data = newData;
            }
        }
        return true;
    }

    public boolean delete(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        D curData = lookup(key);
        if (curData == null) {
            return false;
        }
        LinkedList<TableEntry<K, D>> bucketList = table[hashValue(key)];
        TableEntry<K,D> toRemove = new TableEntry<>(key, curData);
        bucketList.remove(toRemove);
        nElems--;
        return true;
    }

    public D lookup(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList<TableEntry<K, D>> bucketList = table[hashValue(key)];
        if (bucketList.size() == 0) {
            return null;
        }
        for (int i = 0; i < bucketList.size(); i++) {
            TableEntry<K,D> curPair = bucketList.get(i);
            if(curPair.key.equals(key)) {
                return curPair.data;
            }
        }
        return null;
    }

    public int size() {
        return nElems;
    }

    public int capacity() {
        return table.length;
    }

    private int hashValue(K key) {
        return Math.abs(key.hashCode()%(capacity()));
    }


    @SuppressWarnings("unchecked")
    private void rehash() {
        nElems = 0;
        LinkedList<TableEntry<K, D>>[] curTable = table;
        this.table = new LinkedList [capacity() * 2];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        for (LinkedList<TableEntry<K, D>> ll: curTable) {
            if (!ll.isEmpty()) {
                for(TableEntry<K, D> pair: ll) {
                    insert(pair.key, pair.data);
                }
            }
        }
    }
}
