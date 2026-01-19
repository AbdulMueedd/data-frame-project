import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataObjectTest {

    @Test
public void testValue() {
    DataObject obj = new IntObject(10);
    assertEquals(10, obj.getValue());
}

@Test
public void testCompareToIntObject() {
    IntObject obj1 = new IntObject(10);
    IntObject obj2 = new IntObject(20);
    IntObject obj3 = new IntObject(10);
    assertTrue(obj1.compareTo(obj3) == 0);
    assertTrue(obj1.compareTo(obj2) < 0);
    assertTrue(obj2.compareTo(obj1) > 0);
}

@Test
public void testCompareToDoubleObject() {
    DoubleObject obj1 = new DoubleObject(10.5);
    DoubleObject obj2 = new DoubleObject(20.5);
    DoubleObject obj3 = new DoubleObject(10.5);
    assertTrue(obj1.compareTo(obj3) == 0);
    assertTrue(obj1.compareTo(obj2) < 0);
    assertTrue(obj2.compareTo(obj1) > 0);
}

@Test
public void testCompareToCharacterObject() {
    CharacterObject obj1 = new CharacterObject('a');
    CharacterObject obj2 = new CharacterObject('b');
    CharacterObject obj3 = new CharacterObject('a');
    assertTrue(obj1.compareTo(obj3) == 0);
    assertTrue(obj1.compareTo(obj2) < 0);
    assertTrue(obj2.compareTo(obj1) > 0);
}

@Test
public void testCompareToStringObject() {
    StringObject obj1 = new StringObject("hello");
    StringObject obj2 = new StringObject("world");
    StringObject obj3 = new StringObject("hello");
    assertTrue(obj1.compareTo(obj3) == 0);
    assertTrue(obj1.compareTo(obj2) < 0);
    assertTrue(obj2.compareTo(obj1) > 0);
}
}