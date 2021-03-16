import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class DAFTreeTest {

    DAFTree tree1;
    DAFTree tree2;
    DAFTree tree3;
    int[] array;
    int[] key;
    int[] copy;

    @Before
    public void setUp() {
        tree1 = new DAFTree();
        array = new int[]{10, 5, 3, 7, 2, 4, 6, 8, 9, 20, 15, 13, 11, 14, 16, 18, 29, 23, 24, 25, 30};
        tree2 = new DAFTree();
        for (int i : array) tree2.insert(i, i, 1);
        tree3 = new DAFTree();
        key = new int[]{99, 6, 91, 76, 65, 51, 41, 55, 200, 150, 111, 164, 300, 250, 230, 310};
        copy = new int[]{3, 2, 5, 38, 3, 2, 2, 2, 3, 2, 100, 15, 9, 100, 50, 4};
        for (int i = 0; i < key.length; i++) tree3.insert(key[i], key[i], copy[i]);
    }

    @org.junit.Test
    public void nUniqueKeys() {
        assertEquals(21, tree2.nUniqueKeys());
    }

    @org.junit.Test
    public void insert() {
        int[] array = {10, 5, 3, 7, 2, 4, 6, 8, 9, 20, 15, 13, 11, 14, 16, 18, 29, 23, 24, 25, 30};
        for (int i : array) assertEquals(i, tree1.insert(i, i, 1).key);

        for (int i = 0; i < key.length; i++) assertEquals(key[i], tree3.insert(key[i], key[i], copy[i]).key);
    }

    @org.junit.Test
    public void insertDuplicate() {
        for (int i : array) {
            tree2.insertDuplicate(i, 5);
            assertEquals(6, tree2.lookup(i).count);
        }
        assertEquals(21, tree2.nUniqueKeys());
        assertEquals(126, tree2.size());

        for (int i = 0; i < key.length; i++) {
            assertEquals(key[i], tree3.insert(key[i], "s", copy[i]).key);
        }
        assertEquals(16, tree3.nUniqueKeys());
        assertEquals(680, tree3.size());
    }

    @org.junit.Test
    public void updateData() {
        for (int i : array) {
            tree2.updateData(i, i * 2);
            assertEquals(i * 2, tree2.lookup(i).data);
        }
    }

    @org.junit.Test
    public void remove1() {
        for (int i : array) {
            tree2.insertDuplicate(i, 5);
            tree2.remove(i, 1);
            assertEquals(5, tree2.lookup(i).count);
        }
        assertEquals(21, tree2.nUniqueKeys());
        assertEquals(105, tree2.size());
        assertEquals(5, tree2.remove(2, 10).count);
        assertEquals(20, tree2.nUniqueKeys());
        assertEquals(100, tree2.size());
        assertEquals(5, tree2.remove(24, 10).count);
        assertEquals(19, tree2.nUniqueKeys());
        assertEquals(95, tree2.size());
        assertEquals(25, tree2.getRoot().right.right.left.right.key);
        assertNull(tree2.getRoot().right.right.left.right.right);
        assertEquals(5, tree2.remove(20, 123).count);
        assertEquals(18, tree2.nUniqueKeys());
        assertEquals(90, tree2.size());
        assertEquals(23, tree2.getRoot().right.key);
        assertEquals(15, tree2.getRoot().right.left.key);
        assertEquals(29, tree2.getRoot().right.right.key);
        assertEquals(25, tree2.getRoot().right.right.left.key);
        assertEquals(30, tree2.getRoot().right.right.right.key);
        assertEquals(29, tree2.remove(29, 123).key);
        assertEquals(17, tree2.nUniqueKeys());
        assertEquals(85, tree2.size());
        assertEquals(30, tree2.getRoot().right.right.key);
        assertEquals(25, tree2.getRoot().right.right.left.key);
        assertEquals(30, tree2.remove(30, 123).key);
        assertEquals(16, tree2.nUniqueKeys());
        assertEquals(80, tree2.size());
        assertEquals(25, tree2.getRoot().right.right.key);
        assertEquals(25, tree2.remove(25, 123).key);
        assertEquals(15, tree2.nUniqueKeys());
        assertEquals(75, tree2.size());
        assertNull(tree2.getRoot().right.right);
        assertEquals(23,tree2.getRoot().right.key);
        assertEquals(23, tree2.remove(23, 123).key);
        assertEquals(14, tree2.nUniqueKeys());
        assertEquals(70, tree2.size());
        assertEquals(15,tree2.getRoot().right.key);
        assertEquals(13,tree2.getRoot().right.left.key);
        tree2.insert(17, 0, 1);
        assertEquals(17,tree2.getRoot().right.right.right.left.key);
        assertEquals(16,tree2.getRoot().right.right.key);
        assertEquals(5, tree2.remove(5, 123).key);
        assertEquals(3,tree2.getRoot().left.left.key);
        assertEquals(6,tree2.getRoot().left.key);
        assertEquals(7,tree2.getRoot().left.right.key);
        assertNull(tree2.getRoot().left.right.left);
        assertEquals(10, tree2.remove(10, 12312321).key);
        assertEquals(11, tree2.getRoot().key);
        assertEquals(6, tree2.getRoot().left.key);
        assertEquals(15, tree2.getRoot().right.key);
        assertNull(tree2.getRoot().right.left.left);
    }
    @org.junit.Test
    public void remove2(){
        for (int i = 0; i < key.length; i++) {
            tree3.insert(key[i], "s", 1);
            assertEquals(key[i], tree3.remove(key[i], 1).key);
        }
        assertEquals(340, tree3.size());
        tree3.remove(99, 2);
        tree3.remove(99, 10);
        assertEquals(copy.length - 1, tree3.nUniqueKeys());
        assertEquals(111, tree3.getRoot().key);
        assertEquals(6, tree3.getRoot().left.key);
        assertEquals(200, tree3.getRoot().right.key);
        assertNull( tree3.getRoot().right.left.left);
        tree3.remove(200, 1231);
        assertEquals(230, tree3.getRoot().right.key);
        tree3.remove(91,99);
        assertEquals(76, tree3.getRoot().left.right.key);

    }

    @org.junit.Test
    public void findExtreme() {
        assertEquals(30, tree2.findExtreme(true).key);
        assertEquals(2, tree2.findExtreme(false).key);
    }

    @Test
    public void iterator() {
        Iterator iterator2 = tree2.iterator();
        Arrays.sort(array);
        int index2 = 0;
        while (iterator2.hasNext()) {
            Object res = iterator2.next();
            assertEquals(new Integer(array[index2]), res);
            index2++;
        }
        int[] array1 = {1,2,3,3,3,3,4,5,6,7,7,7,7,7,7,7,8,21, 21, 21, 21,21, 21, 21, 21,55,55,55,55,55,55,99};
        for (int i : array1) tree1.insert(i, i, 1);
        Iterator iterator1 = tree1.iterator();
        int index1 = 0;
        while (iterator2.hasNext()) {
            assertEquals(new Integer(array1[index1]), iterator2.next());
            index1++;
        }
        tree3 = new DAFTree();
        int[] array3 = {1,1,1,1,1,1,1,1,1,1,2};
        for (int i : array3) tree3.insert(i, i, 1);
        Iterator iterator3 = tree3.iterator();
        int index3 = 0;
        while (iterator3.hasNext()) {
            assertEquals(new Integer(array3[index3]), iterator3.next());
            index3++;
        }
    }
}
