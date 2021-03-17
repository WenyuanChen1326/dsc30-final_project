import java.util.*;

@SuppressWarnings("rawtypes")
public class FADAF<K extends Comparable<? super K>, D> {
    HashTable<K, DAFTree<K, D>.DAFNode<K,D>> table;
    DAFTree<K,D> tree;

    public FADAF(int capacity) {
        if (capacity < 10) {
            throw new IllegalArgumentException();
        }
        table = new HashTable<>(capacity);
        tree = new DAFTree<>();
    }

    public int size() {
        return tree.size();
    }

    public int nUniqueKeys() {
        return tree.nUniqueKeys();
    }

    public boolean insert(K key, D data, int nCopy) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFTree<K, D>.DAFNode<K, D> present = table.lookup(key);
        if (present == null) {
            return table.insert(key, tree.insert(key, data, nCopy));
        } else {
            present.count += nCopy;
            tree.nElem += nCopy;
            return false;
        }
    }
    public int lookup(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFTree<K,D>.DAFNode<K,D> node = table.lookup(key);
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    public boolean remove(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFTree<K,D>.DAFNode<K,D> node = table.lookup(key);
        if (node == null) {
            return false;
        }
        if (node.count - nCopy <= 0) {
            table.delete(key);
            tree.remove(key, nCopy);
        } else {
            node.count -= nCopy;
            tree.nElem -= nCopy;
        }
        return true;
    }

    public boolean removeAll(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFTree<K,D>.DAFNode<K,D> node = table.lookup(key);
        if (node == null) {
            return false;
        }
        return remove(key, node.count);

    }

    public boolean update(K key, D newData) {
        if (key == null || newData == null) {
            throw new NullPointerException();
        }
        DAFTree<K,D>.DAFNode<K,D> updatedNode = table.lookup(key);
        if (updatedNode == null) {
            return false;
        } else {
            updatedNode.data = newData;
            return true;
        }
    }


    public List<K> getAllKeys(boolean allowDuplicate) {
        List<K> output = new LinkedList<>();
        if (allowDuplicate) {
            Iterator<K> iterator = tree.iterator();
            while (iterator.hasNext()) {
                output.add(iterator.next());
            }
        } else {
            Iterator<K> iterator = tree.iterator();
            K current = null;
            K predecessor = null;
            while (iterator.hasNext()) {
                current = iterator.next();
                if (predecessor == null) {
                    output.add(current);
                }
                else {
                    if (!current.equals(predecessor)) {
                        output.add(current);
                    }
                }
                predecessor = current;
                }
            }
        return output;
    }

    public List<K> getUniqueKeysInRange(K lower, K upper) {
        if (lower == null || upper == null) {
            throw new NullPointerException();
        }
        List<K> output = new LinkedList<>();
        Iterator<K> iterator = tree.iterator();
        K current;
        K predecessor = null;
        while (iterator.hasNext()) {
            current = iterator.next();
            int valueLow = current.compareTo(lower);
            int valueHigh = current.compareTo(upper);
            if (predecessor == null) {
                if (valueLow > 0 && valueHigh < 0)
                    output.add(current);
            }
            else {
                if (!current.equals(predecessor)) {
                    if (valueLow > 0 && valueHigh < 0) {
                        output.add(current);
                    }
                }
            }
            predecessor = current;
        }
        return output;
    }

    public K getMinKey() {
        if (size() == 0) {
            return null;
        }
        return tree.findExtreme(false).key;
    }

    public K getMaxKey() {
        if (size() == 0) {
            return null;
        }
        return tree.findExtreme(true).key;
    }
}
