import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class HashTableTest {

    HashTable ht1;
    HashTable ht2;
    HashTable ht3;


    @Before
    public void setUp() throws Exception {
        ht1 = new HashTable(10);
        ht2 = new HashTable(20);
        ht3 = new HashTable(100);
    }

    @Test
    public void insert() {
        for (int i = 0; i < 15; i++) {
            assertTrue(ht1.insert(String.valueOf(i), "num" + i));
        }
        assertEquals(15, ht1.size());
        assertEquals(40, ht1.capacity());
        for (int i = 0; i < 55; i++) {
            assertTrue(ht3.insert("STRING" + i, "num" + i));
        }
        assertEquals(55, ht3.size());
        assertEquals(100, ht3.capacity());
        for (int i = 0; i < 15; i++) {
            assertFalse(ht1.insert(String.valueOf(i), "num" + i));
            assertEquals("num" + i, ht1.lookup(String.valueOf(i)));
        }
    }

    @Test
    public void update() {
        for (int i = 0; i < 15; i++) {
            ht1.insert(String.valueOf(i), "num" + i);
            ht1.update(String.valueOf(i), "num" + 2*i);
            assertEquals("num" + 2*i,ht1.lookup(String.valueOf(i)));
        }
        for (int i = 0; i < 55; i++) {
            ht3.insert("STRING" + i, "num" + i);
            ht3.update("STRING" + i, "num" + i + 1);
            assertEquals("num" + i + 1, ht3.lookup("STRING" + i));
        }
    }

    @Test
    public void delete() {
        for (int i = 0; i < 15; i++) {
            assertTrue(ht1.insert(String.valueOf(i), "num" + i));
            assertTrue(ht1.delete(String.valueOf(i)));
            assertNull(ht1.lookup(String.valueOf(i)));
            assertFalse(ht1.delete(String.valueOf(i)));
        }

    }
}
/*public class HashTableTest {
    HashTable<String, Integer> table1;
    HashTable<Character, Double> table2;
    HashTable<int[], String[]> table3;
    HashTable<Integer, Integer> table4;

    @Before
    public void setUp() {
        table1 = new HashTable<>(12);
        table2 = new HashTable<>(12);
        table3 = new HashTable<>(12);

        assertNotNull(table1);
        assertNotNull(table2);
        assertNotNull(table3);

        table1.insert("Kevin", 21);
        table1.insert("Wendy", 1972);
        table1.insert("An", 1964);
        table1.insert("Joy", 20);
        table1.insert("Linda", 20021010);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsIAE() {
        table4 = new HashTable<>(3);
        table4 = new HashTable<>(4);
    }

    @Test(expected = NullPointerException.class)
    public void insertTestNPE() {
        table1.insert(null, 10);
        table1.insert("k", null);
        table1.insert(null, null);
    }

    @Test
    public void insertTest() {
        assertEquals(5, table1.size());
        assertFalse(table1.insert("Kevin", 18));
        assertFalse(table1.insert("Wendy", 2000));
        assertFalse(table1.insert("An", 1964));
        assertFalse(table1.insert("Joy", 20));
        assertFalse(table1.insert("Linda", 20021010));
        assertEquals(5, table1.size());

        table1.insert("ABC", 20);
        table1.insert("STR", 21);
        table1.insert("Zeta MS-006", 87);

        assertEquals(8, table1.size());
        table1.insert("ZZeta MS-007", 89);
        assertEquals(table1.capacity(), 12);
        table1.insert("Resize since 9 elements before" +
                "insert gives 9/12 larger than 2/3", 0);
        assertEquals(table1.capacity(), 24);

        assertEquals(0, table2.size());
        table2.insert('a', 1.0);
        assertEquals(1, table2.size());
    }

    @Test(expected = NullPointerException.class)
    public void deleteNPE() {
        table1.delete(null);
    }

    @Test
    public void deleteTest() {
        assertEquals(5, table1.size());
        assertTrue(table1.delete("Kevin"));
        assertEquals(4, table1.size());
        assertTrue(table1.delete("Wendy"));
        assertEquals(3, table1.size());
        assertTrue(table1.delete("An"));
        assertEquals(2, table1.size());
        assertFalse(table1.delete("Not in here"));
        assertEquals(2, table1.size());

        assertEquals(0, table2.size());
        assertFalse(table2.delete('C'));
    }

    @Test(expected = NullPointerException.class)
    public void updateTestNPE() {
        table1.update(null, 22);
        table1.update("Kevin", null);
        table1.update(null, null);
    }

    @Test
    public void updateTest() {
        assertFalse(table1.update("No such key", 12));
        assertNotEquals(null, table1.lookup("Kevin"));
        assertEquals(new Integer(21), table1.lookup("Kevin"));
        assertTrue(table1.update("Kevin", 2000));
        assertEquals(new Integer(2000), table1.lookup("Kevin"));
        assertFalse(table2.update('1', 10.0));
    }

    @Test(expected = NullPointerException.class)
    public void lookupNPE() {
        table1.lookup(null);
    }

    @Test
    public void lookupTest() {
        assertNotEquals(null, table1.lookup("Kevin"));
        assertEquals(new Integer(21), table1.lookup("Kevin"));

        assertNull(table2.lookup('G'));
        assertNull(table2.lookup('U'));
        assertNull(table2.lookup('N'));
        table2.insert('G', 1.0);
        table2.insert('U', 99.9);
        table2.insert('N', 0.762);
        assertEquals(new Double(1.0), table2.lookup('G'));
        assertEquals(new Double(99.9), table2.lookup('U'));
        assertEquals(new Double(0.762), table2.lookup('N'));
    }

    @Test
    public void sizeTest() {
        // table 1
        assertEquals(5, table1.size());
        String[] moreKeys = {"lmao", "lmao1", "lmao2", "lmao3", "lmao4"};
        Integer[] moreData = {233, 2333, 23333, 233333, 2333333};
    for (int i = 0; i < moreData.length; i++) {
        table1.insert(moreKeys[i], moreData[i]);
    }

    assertEquals(10, table1.size());

    // table 2
    Character[] moreCharKey = new Character[256];
    Double[] moreDouble = new Double[256];

    for (int i = 0; i < 256; i++) {
        moreCharKey[i] = (char) i;
        moreDouble[i] = (double) i;
    }

    for (int i = 0; i < moreCharKey.length; i++) {
        table2.insert(moreCharKey[i], moreDouble[i]);
    }

    for (Character character : moreCharKey) {
        assertNotEquals(null, table2.lookup(character));
    }

    assertEquals(256, table2.size());
    assertEquals(384, table2.capacity());
}
    @Test
    public void capacityTest() {
        Character[] moreCharKey = new Character[256];
        Double[] moreDouble = new Double[256];

        for (int i = 0; i < 256; i++) {
            moreCharKey[i] = (char) i;
            moreDouble[i] = (double) i;
        }

        for (int i = 0; i < 10; i++) {
            table2.insert(moreCharKey[i], moreDouble[i]);
        }

        assertEquals(24, table2.capacity());
        assertEquals(10, table2.size());

        for (int i = 10; i < 18; i++) {
            table2.insert(moreCharKey[i], moreDouble[i]);
        }

        assertEquals(48, table2.capacity());
        assertEquals(18, table2.size());
    }
}*/
