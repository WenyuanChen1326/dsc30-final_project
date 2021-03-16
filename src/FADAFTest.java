import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/*public class FADAFTest {
    FADAF<String,Integer> fTree;
    FADAF<Integer,Integer> fTree2;
    HashTable<Integer, String> table;
    DAFTree<Integer, String> tree;
    FADAF<String,Integer> fadaf;

    @Before
    public void setUp() throws Exception {
        fTree = new FADAF<>(15);
        fTree2 = new FADAF<>(15);
        table = new HashTable<>(15);
        tree = new DAFTree<>();
        fadaf = new FADAF<>(10);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void initiateExceptionTest() {
        fTree = new FADAF<>(1);
    }

    @org.junit.Test
    public void insertTest1() {
        fTree = new FADAF<>(15);
        fTree.insert("a", 10, 10);
        assertEquals(fTree.size(), 10);
        fTree.insert("as", 11, 20);
        assertEquals(fTree.size(), 30);
        fTree.insert("a", 1, 30);
        assertEquals(60, fTree.size());
    }

    @org.junit.Test
    public void insertTest2() {
        fTree = new FADAF<>(15);
        fTree.insert("a", 10, 10);
        assertEquals(fTree.size(), 10);
        fTree.insert("as", 11, 20);
        assertEquals(fTree.size(), 30);
        fTree.insert("a", 1, 30);
        assertEquals(60, fTree.size());
        fTree.insert("ass", 1, 40);
        assertEquals(100, fTree.size());
        fTree.insert("asss", 1, 50);
        assertEquals(150, fTree.size());
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 50);
        assertEquals(12, fTree.nUniqueKeys());
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void insertExceptionTest1() {
        fTree = new FADAF<>(15);
        fTree.insert(null, 2, 10);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void insertExceptionTest2() {
        fTree = new FADAF<>(15);
        fTree.insert("a", 2, 0);
    }

    @org.junit.Test
    public void nUniqueKeysTest1() {
        fTree = new FADAF<>(15);
        fTree.insert("a", 10, 10);
        assertEquals(fTree.size(), 10);
        fTree.insert("as", 11, 20);
        assertEquals(fTree.size(), 30);
        fTree.insert("a", 1, 30);
        assertEquals(fTree.size(), 60);
        assertEquals(fTree.nUniqueKeys(), 2);
    }

    @org.junit.Test
    public void nUniqueKeysTest2() {
        fTree = new FADAF<>(15);
        fTree.insert("a", 10, 10);
        assertEquals(fTree.size(), 10);
        fTree.insert("a", 11, 20);
        assertEquals(fTree.size(), 30);
        fTree.insert("a", 1, 30);
        assertEquals(60, fTree.size());
        fTree.insert("a", 1, 40);
        assertEquals(100, fTree.size());
        fTree.insert("a", 1, 50);
        assertEquals(150, fTree.size());
        fTree.insert("a", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 50);
        assertEquals(8, fTree.nUniqueKeys());
    }

    @org.junit.Test
    public void nUniqueKeysTest3() {
        fTree2 = new FADAF<>(15);
        fTree2.insert(10, 1, 20);
        fTree2.insert(2, 1, 10);
        fTree2.insert(3, 10, 5);
        fTree2.insert(21, 10, 5);
        fTree2.insert(5, 10, 5);
        fTree2.insert(11, 10, 5);
        fTree2.insert(6, 10, 5);
        fTree2.insert(3, 10, 5);
        assertEquals(7, fTree2.nUniqueKeys());
    }


    @org.junit.Test
    public void lookup() {
        fTree = new FADAF<>(15);
        fTree.insert("a", 10, 10);
        assertEquals(fTree.size(), 10);
        fTree.insert("as", 11, 20);
        assertEquals(fTree.size(), 30);
        fTree.insert("a", 1, 30);
        assertEquals(60, fTree.size());
        fTree.insert("ass", 1, 40);
        assertEquals(100, fTree.size());
        fTree.insert("asss", 1, 50);
        assertEquals(150, fTree.size());
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 50);
        assertEquals(12, fTree.nUniqueKeys());
        assertEquals(40, fTree.lookup("a"));
        assertEquals(50, fTree.lookup("12"));
    }

    @org.junit.Test
    public void lookupTest2() {
        fTree2 = new FADAF<>(15);
        fTree2.insert(10, 1, 20);
        fTree2.insert(2, 1, 10);
        fTree2.insert(3, 10, 5);
        fTree2.insert(21, 10, 5);
        fTree2.insert(5, 10, 5);
        fTree2.insert(11, 10, 5);
        fTree2.insert(6, 10, 5);
        fTree2.insert(3, 10, 5);
        assertEquals(7, fTree2.nUniqueKeys());
        assertEquals(10, fTree2.lookup(3));
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void lookupExceptionTest1() {
        fTree = new FADAF<>(15);
        fTree.lookup(null);
    }

    @org.junit.Test
    public void removeTest1() {
        fTree = new FADAF<>(15);
        fTree.insert("a", 10, 10);
        fTree.insert("as", 11, 20);
        fTree.insert("a", 1, 30);
        fTree.insert("ass", 1, 40);
        fTree.insert("asss", 1, 50);
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 50);
        assertTrue(fTree.remove("a",10));
        assertEquals(30, fTree.lookup("a"));
        assertTrue(fTree.remove("a",30));
        assertEquals(0, fTree.lookup("a"));
        assertEquals(11, fTree.nUniqueKeys());
        assertTrue(fTree.remove("as",19));
        assertEquals(1, fTree.lookup("as"));
    }


    @org.junit.Test
    public void removeTest2() {
        fTree.insert("a", 10, 10);
        fTree.insert("as", 11, 20);
        fTree.insert("a", 1, 30);
        fTree.insert("ass", 1, 40);
        fTree.insert("asss", 1, 50);
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 50);
        fTree.remove("a", 50);
        fTree.remove("as", 20);
        fTree.remove("ass", 40);
        fTree.remove("asss", 50);
        fTree.remove("assss", 50);
        fTree.remove("6", 50);
        fTree.remove("7", 50);
        fTree.remove("8", 50);
        fTree.remove("9", 50);
        fTree.remove("10", 50);
        fTree.remove("11", 50);
        fTree.remove("12", 50);
        assertEquals(0, fTree.lookup("a"));
        assertEquals(0, fTree.lookup("9"));
        assertEquals(0, fTree.nUniqueKeys());
    }


    @org.junit.Test(expected = NullPointerException.class)
    public void removeException1() {
        fTree.remove(null, 2);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void removeException2() {
        fTree.remove("a", 0);
    }

    @org.junit.Test
    public void removeAll() {
        fTree.insert("a", 10, 10);
        fTree.insert("as", 11, 20);
        fTree.insert("a", 1, 30);
        fTree.insert("ass", 1, 40);
        fTree.insert("asss", 1, 50);
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 50);
        assertTrue(fTree.removeAll("a"));
        assertEquals(0, fTree.lookup("a"));
        assertEquals(11, fTree.nUniqueKeys());
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void removeAllException1() {
        fTree.removeAll(null);
    }

    @org.junit.Test
    public void update() {
        fTree.insert("a", 10, 10);
        fTree.insert("as", 11, 20);
        fTree.insert("a", 1, 30);
        fTree.insert("ass", 1, 40);
        fTree.insert("asss", 1, 50);
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 5000000);
        assertTrue(fTree.update("a", 26));
        //assertEquals(fTree.tree.lookup("a").data, fTree.table.lookup("a").data);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void updateException1() {
        fTree.update(null, 2);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void updateException2() {
        fTree.update("a", null);
    }

    @org.junit.Test
    public void getAllKeys() {
        fTree.insert("a", 10, 10);
        fTree.insert("b", 10, 10);
        fTree.insert("love", 10, 10);
        LinkedList<String> result = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            result.add("a");
        }
        for (int i = 0; i < 10; i++) {
            result.add("b");
        }
        for (int i = 0; i < 10; i++) {
            result.add("love");
        }
        assertEquals(result, fTree.getAllKeys(true));
        assertEquals(10, fTree.lookup("a"));
    }

    @org.junit.Test
    public void getAllKeysTest2() {
        fTree.insert("a", 10, 10);
        fTree.insert("b", 10, 10);
        fTree.insert("love", 10, 10);
        LinkedList<String> result = new LinkedList<>();
        result.add("a");
        result.add("b");
        result.add("love");
        assertEquals(result, fTree.getAllKeys(false));
        assertEquals(10, fTree.lookup("a"));
    }

    @org.junit.Test
    public void getAllKeysTest3() {
        fTree.insert("a", 10, 1);
        fTree.insert("as", 11, 2);
        fTree.insert("a", 1, 3);
        fTree.insert("ass", 1, 4);
        fTree.insert("asss", 1, 5);
        fTree.insert("assss", 1, 5);
        //fTree.insert("6", 1, 5);
        //fTree.insert("7",1, 5);
        //fTree.insert("8", 1, 5);
        //fTree.insert("9", 1, 5);
        //fTree.insert("10", 1, 5);
        //fTree.insert("11", 1, 5);
        //fTree.insert("12", 1, 5000000);
        LinkedList<String> result = new LinkedList<>();
        //result.add("10");
        //result.add("11");
        //result.add("12");
        //result.add("6");
        //result.add("7");
        //result.add("8");
        //result.add("9");
        result.add("a");
        result.add("as");
        result.add("ass");
        result.add("asss");
        result.add("assss");
        assertEquals(result, fTree.getAllKeys(false));
        assertEquals(4, fTree.lookup("a"));
    }

    @org.junit.Test
    public void getAllKeysTest4() {
        fTree2 = new FADAF<>(15);
        fTree2.insert(10, 1, 20);
        fTree2.insert(2, 1, 10);
        fTree2.insert(3, 10, 5);
        fTree2.insert(21, 10, 4);
        fTree2.insert(5, 10, 5);
        fTree2.insert(11, 10, 5);
        fTree2.insert(6, 10, 5);
        fTree2.insert(3, 10, 5);
        assertEquals(7, fTree2.nUniqueKeys());
        assertEquals(10, fTree2.lookup(3));

        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            result.add(2);
        }
        for (int i = 0; i < 10; i++) {
            result.add(3);
        }
        for (int i = 0; i < 5; i++) {
            result.add(5);
        }
        for (int i = 0; i < 5; i++) {
            result.add(6);
        }
        for (int i = 0; i < 20; i++) {
            result.add(10);
        }
        for (int i = 0; i < 5; i++) {
            result.add(11);
        }
        for (int i = 0; i < 4; i++) {
            result.add(21);
        }

        assertEquals(result, fTree2.getAllKeys(true));
    }

    @org.junit.Test
    public void getUniqueKeysInRangeTest1() {
        fTree.insert("a", 10, 10);
        fTree.insert("b", 10, 10);
        fTree.insert("love", 10, 10);
        LinkedList<String> result = new LinkedList<>();
        result.add("b");
        assertEquals(result, fTree.getUniqueKeysInRange("a","love"));
        assertEquals(10, fTree.lookup("a"));
    }
    @org.junit.Test
    public void getUniqueKeysInRangeTest2() {
        fTree.insert("a", 10, 10);
        fTree.insert("b", 10, 10);
        fTree.insert("love", 10, 10);
        LinkedList<String> result = new LinkedList<>();
        assertEquals(result, fTree.getUniqueKeysInRange("b","love"));
        assertEquals(10, fTree.lookup("a"));
    }

    @org.junit.Test
    public void getUniqueKeysInRangeTest3() {
        fTree2 = new FADAF<>(15);
        fTree2.insert(10, 1, 20);
        fTree2.insert(2, 1, 10);
        fTree2.insert(3, 10, 5);
        fTree2.insert(21, 10, 4);
        fTree2.insert(5, 10, 5);
        fTree2.insert(11, 10, 5);
        fTree2.insert(6, 10, 5);
        fTree2.insert(3, 10, 5);
        assertEquals(7, fTree2.nUniqueKeys());
        assertEquals(10, fTree2.lookup(3));

        LinkedList<Integer> result = new LinkedList<>();
        result.add(5);
        result.add(6);
        result.add(10);
        result.add(11);
        assertEquals(result, fTree2.getUniqueKeysInRange(3, 21));

        LinkedList<Integer> result2 = new LinkedList<>();
        result2.add(6);
        result2.add(10);
        result2.add(11);
        result2.add(21);
        assertEquals(result2, fTree2.getUniqueKeysInRange(5, 24));
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void getUniqueKeysInRangeExceptionTest1() {
        fTree.getUniqueKeysInRange(null, "ass");
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void getUniqueKeysInRangeExceptionTest2() {
        fTree.getUniqueKeysInRange("as", null);
    }

    @org.junit.Test
    public void getMinKeyTest1() {
        fTree.insert("a", 10, 1);
        fTree.insert("as", 11, 2);
        fTree.insert("a", 1, 3);
        fTree.insert("ass", 1, 4);
        fTree.insert("asss", 1, 5);
        fTree.insert("assss", 1, 5);
        assertEquals("a", fTree.getMinKey());
    }

    @org.junit.Test
    public void getMinKeyTest2() {
        fTree.insert("a", 10, 10);
        fTree.insert("as", 11, 20);
        fTree.insert("a", 1, 30);
        fTree.insert("ass", 1, 40);
        fTree.insert("asss", 1, 50);
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 5000000);
        assertEquals("10", fTree.getMinKey());
    }
    @org.junit.Test
    public void getMaxKey() {
        fTree.insert("a", 10, 10);
        fTree.insert("as", 11, 20);
        fTree.insert("a", 1, 30);
        fTree.insert("ass", 1, 40);
        fTree.insert("asss", 1, 50);
        fTree.insert("assss", 1, 50);
        fTree.insert("6", 1, 50);
        fTree.insert("7",1, 50);
        fTree.insert("8", 1, 50);
        fTree.insert("9", 1, 50);
        fTree.insert("10", 1, 50);
        fTree.insert("11", 1, 50);
        fTree.insert("12", 1, 5000000);
        assertEquals("assss", fTree.getMaxKey());
    }



    //above are tests from lover


    //below are tests from bie ren
    @org.junit.Test
    public void removeTest00() {
        fadaf.insert("n", 1, 10);
        fadaf.insert("q", 3, 10);
        fadaf.insert("h", 2, 3);
        fadaf.insert("k", 10, 1);
        fadaf.insert("o", 7, 4);
        fadaf.insert("c", 4, 7);
        fadaf.insert("w", 10, 3);
        fadaf.insert("x", 10, 3);
        fadaf.remove("n", 5);
        assertTrue(fadaf.size() == 36);
        assertTrue(fadaf.nUniqueKeys() == 8);
        fadaf.remove("n", 5);
        System.out.println(fadaf.size());//32
        //assertTrue(fadaf.size() == 31);
        assertTrue(fadaf.nUniqueKeys() == 7);
        fadaf.remove("w", 3);
        System.out.println(fadaf.size());
        //assertTrue(fadaf.size() == 28);
        assertTrue(fadaf.nUniqueKeys() == 6);
        fadaf.remove("c", 7);
        System.out.println(fadaf.size());
        //assertTrue(fadaf.size() == 21);
        assertTrue(fadaf.nUniqueKeys() == 5);
    }

    @org.junit.Test
    public void getAllKeysTest00() {
        fadaf.insert("n", 1, 3);
        fadaf.insert("q", 3, 3);
        fadaf.insert("h", 2, 3);
        fadaf.insert("k", 10, 3);
        fadaf.insert("o", 7, 3);
        fadaf.insert("c", 4, 3);
        fadaf.insert("w", 10, 5);
        fadaf.insert("x", 10, 3);
        List<String> a = fadaf.getAllKeys(true);
        StringBuilder x = new StringBuilder();
        StringBuilder y = new StringBuilder();
        StringBuilder hy = new StringBuilder();
        for (String b: a) {
            x.append(b);
        }
        assertEquals("ccchhhkkknnnoooqqqwwwwwxxx", x.toString());
        List<String> z = fadaf.getUniqueKeysInRange("a", "z");
        for (String p: z) {
            hy.append(p);
        }
        assertEquals("chknoqwx", hy.toString());
        List<String> b = fadaf.getAllKeys(false);
        for (String c: b) {
            y.append(c);
        }
        assertEquals("chknoqwx", y.toString());
    }
}*/

public class FADAFTest {

    FADAF fadaf1 = new FADAF(20);
    FADAF fadaf2 = new FADAF(10);
    int[] key = {99, 6, 91, 76, 65, 51, 41, 55, 200, 150, 111, 164, 300, 250, 230, 310};
    int[] copy = {3, 2, 5, 38, 3, 2, 2, 2, 3, 2, 100, 15, 9, 100, 50, 4};
    @Before
    public void setUp() {
        for (int i = 0; i < key.length; i++) {
            fadaf1.insert(key[i],key[i],copy[i]);
        }
    }

    @org.junit.Test
    public void size() {
        assertEquals(340, fadaf1.size());
    }

    @org.junit.Test
    public void nUniqueKeys() {
        assertEquals(key.length, fadaf1.nUniqueKeys());
    }

    @org.junit.Test
    public void insert() {
        for (int i = 0; i < key.length; i++) {
            assertTrue(fadaf2.insert(key[i],key[i],copy[i]));
            assertFalse(fadaf2.insert(key[i],121,copy[i]));
        }
        assertEquals(680, fadaf2.size());
        assertEquals(key.length, fadaf2.nUniqueKeys());
        for (int i = 0; i < key.length; i++) {
            assertEquals(copy[i] * 2, fadaf2.lookup(key[i]));
        }
    }
    @org.junit.Test
    public void lookup() {
        for (int i = 0; i < key.length; i++) {
            assertEquals(copy[i], fadaf1.lookup(key[i]));
        }
    }

    @org.junit.Test
    public void remove() {
        for (int i = 0; i < key.length; i++) {
            assertTrue(fadaf1.remove(key[i], 1));
            assertEquals(copy[i] - 1, fadaf1.lookup(key[i]));
        }
        assertEquals(16, fadaf1.nUniqueKeys());

        for (int i = 0; i < key.length; i+=2) {
            assertTrue(fadaf1.remove(key[i], 9999));
            assertEquals(0, fadaf1.lookup(key[i]));
        }
    }

    @org.junit.Test
    public void removeAll() {
        for (int i = 0; i < key.length; i+=2) {
            assertTrue(fadaf1.removeAll(key[i]));
            assertEquals(0, fadaf1.lookup(key[i]));
        }
        assertEquals(key.length / 2, fadaf1.nUniqueKeys());
        for (int i = 1111; i < 1177; i++) {
            assertFalse(fadaf1.removeAll(i));
        }
    }

    @org.junit.Test
    public void update() {
        fadaf1.update(99, 21839);
        //assertEquals(21839,fadaf1.getRoot().data);
        assertFalse(fadaf1.update(123121321, 12));
    }

    @org.junit.Test
    public void getAllKeys() {
        Arrays.sort(key);
        for (int i = 0; i < key.length; i++) {
            assertEquals(key[i], fadaf1.getAllKeys(false).get(i));
        }
        int[] key = {99, 6, 91, 76, 65, 51, 41, 55, 200, 150, 111, 164, 300, 250, 230, 310};
        ArrayList<Integer> dup = new ArrayList<>();
        for (int i = 0; i < fadaf1.nUniqueKeys(); i++) {
            int counter = copy[i];
            while (counter > 0) {
                dup.add(key[i]);
                counter--;
            }
        }
        Collections.sort(dup);
        for (int i = 0; i < fadaf1.size(); i++) {
            assertEquals(dup.get(i), fadaf1.getAllKeys(true).get(i));
        }
    }
    @org.junit.Test
    public void getUniqueKeysInRange() {
        int[] test1 = {65, 76, 91, 99, 111, 150, 164, 200};
        List res1 = fadaf1.getUniqueKeysInRange(64, 210);
        for (int i = 0; i<test1.length; i++) {
            assertEquals(test1[i], res1.get(i));
        }
        Arrays.sort(key);
        List res2 = fadaf1.getUniqueKeysInRange(-1231, 21210);
        for (int i = 0; i < key.length; i++) {
            assertEquals(key[i], res2.get(i));
        }

        List res3 = fadaf1.getUniqueKeysInRange(2131, 21210);
        assertEquals(0, res3.size());
        List res4 = fadaf1.getUniqueKeysInRange(65, 66);
        assertEquals(0, res4.size());
    }

    @org.junit.Test
    public void getMinKey() {
        assertEquals(6, fadaf1.getMinKey());
    }

    @Test
    public void getMaxKey() {
        assertEquals(310, fadaf1.getMaxKey());
    }
}
