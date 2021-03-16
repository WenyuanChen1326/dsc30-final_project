import java.util.*;

@SuppressWarnings("rawtypes")
public class DAFTree<K extends Comparable<? super K>, D> implements Iterable {

    private DAFNode<K, D> root;
    public int nElem;
    private int nodeCounter;


    protected class DAFNode<K extends Comparable<? super K>, D> {
        K key;
        D data;
        int count; // duplicate counter
        DAFNode<K, D> left, right;

        public DAFNode(K key, D data) {
            this(key, data, 1);
        }

        public DAFNode(K key, D data, int nCopy) {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            if (nCopy < 1) {
                throw new IllegalArgumentException();
            }
            this.key = key;
            this.data = data;
            left = null;
            right = null;
            count = nCopy;
        }
    }
    public DAFTree() {
        this.root = null;
        this.nElem = 0;
    }
    public DAFNode<K,D> getRoot() {
        return root;
    }
    public int size() {
        return nElem;
    }

    public int nUniqueKeys() {
        return nodeCounter;
    }

    public DAFNode<K, D> insert(K key, D data, int nCopy) {

        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode<K, D> cur = root;
        if (cur == null) {
            DAFNode<K, D> toInsert = new DAFNode<>(key, data, nCopy);
            root = toInsert;
            nodeCounter++;
            nElem += nCopy;
            return toInsert;
        } else {
            while(cur != null){
                int value = cur.key.compareTo(key);
                if (value == 0) {
                    cur.count += nCopy;
                    nElem += nCopy;
                    return cur;
                } else if (value > 0) {
                    if (cur.left == null) {
                        DAFNode<K, D> toInsert = new DAFNode<>(key, data, nCopy);
                        cur.left = toInsert;
                        nElem += nCopy;
                        nodeCounter++;
                        return toInsert;
                    } else {
                        cur = cur.left;
                    }
                } else {
                    if (cur.right == null) {
                        DAFNode<K, D> toInsert = new DAFNode<>(key, data, nCopy);
                        cur.right = toInsert;
                        nElem += nCopy;
                        nodeCounter++;
                        return toInsert;
                    } else {
                        cur = cur.right;
                    }
                }
            }
            // should never reached
            return cur;
        }
    }

    public DAFNode<K, D> insertDuplicate(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode<K, D> node = lookup(key);
        if (node == null) {
            return null;
        } else {
            node.count += nCopy;
            nElem += nCopy;
            return node;
        }
    }

    public DAFNode<K, D> lookup(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFNode<K,D> currentNode = root;
        while (currentNode != null) {
            int value = currentNode.key.compareTo(key);
            if (value == 0) {
                return currentNode;
            } else if (value > 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return null;
    }

    public DAFNode<K, D> updateData(K key, D newData) {
        if (key == null || newData == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> node = lookup(key);
        if (node == null) {
            return null;
        }
        node.data = newData;
        return node;
    }

    private void removeHelper(K key) {
        DAFNode<K, D> cur = root;
        DAFNode<K, D> parent = null;
        while (cur != null) {
            int value = cur.key.compareTo(key);
            if (value == 0) {
                // the leaf node is found
                if (cur.left == null && cur.right == null) {
                    if (parent == null) {
                        root = null;
                    } else if (parent.left != null && parent.left.key.compareTo(cur.key) == 0) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                    break;
                }
                // the node only has left child
                else if (cur.right == null) {
                    if (parent == null) {
                        root = cur.left;
                    } else if (parent.left != null && parent.left.key.compareTo(cur.key) == 0) {
                        parent.left = cur.left;
                    } else {
                        parent.right = cur.left;
                    }
                    break;
                }
                // the node only has right child
                else if (cur.left == null) {
                    if (parent == null) {
                        root = cur.right;
                    } else if (parent.left != null && parent.left.key.compareTo(cur.key) == 0) {
                        parent.left = cur.right;
                    } else {
                        parent.right = cur.right;
                    }
                    break;
                }  else {
                    // the node has two children
                    DAFNode<K, D> successor = cur.right;
                    while (successor.left != null) {
                        successor = successor.left;
                    }
                    K savedKey = successor.key;
                    D savedData = successor.data;
                    int savedCount = successor.count;
                    removeHelper(savedKey);
                    cur.key = savedKey;
                    cur.data = savedData;
                    cur.count = savedCount;
                    break;
                }
            } else if (value < 0) {
                parent = cur;
                cur = cur.right;
            } else {
                parent = cur;
                cur = cur.left;
            }
        }
    }



    public DAFNode<K, D> remove(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode<K, D> node = lookup(key);
        if (node == null) {
            return null;
        }
        DAFNode<K, D> returnedNode = new DAFNode<>(node.key, node.data, node.count);
        if (node.count - nCopy > 0) {
            node.count -= nCopy;
            nElem -= nCopy;
        } else {
            removeHelper(key);
            nodeCounter--;
            nElem -= returnedNode.count;
        }
        return returnedNode;
    }

    public DAFNode<K, D> findExtreme(boolean isMax) {
        if (root == null) {
            return null;
        }
        DAFNode<K, D> cur = root;
        if (isMax) {
            while (cur.right != null) {
                cur = cur.right;
            }
        } else {
            while (cur.left != null ) {
                cur = cur.left;
            }
        }
        return cur;
    }

    public class DAFTreeIterator implements Iterator<K> {

        Stack<DAFNode<K, D>> stack;

        public DAFTreeIterator() {
            DAFNode<K, D> cur = root;
            stack = new Stack<>();
            while (cur != null) {
                for (int i = 0; i < cur.count; i++) {
                    stack.push(cur);
                }
                cur = cur.left;
            }
        }

        public boolean hasNext() {
            return !stack.empty();
        }

        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            DAFNode<K, D> outputNode = stack.pop();
            if (stack.isEmpty() || outputNode != stack.peek()) {
                DAFNode<K, D> currentLeftNode = outputNode.right;
                // append the leftmost children
                while (currentLeftNode != null) {
                    for (int i = 0; i < currentLeftNode.count; i++) {
                        stack.push(currentLeftNode);
                    }
                    currentLeftNode = currentLeftNode.left;
                }
            }
            return outputNode.key;
        }
    }

    public Iterator<K> iterator() {
        return new DAFTreeIterator();
    }
}
