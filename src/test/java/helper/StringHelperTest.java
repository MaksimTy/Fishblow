package helper;

import model.InvalidValuesException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class StringHelperTest {

    @Before
    public void setUp() {
//todo два набора тестовых данных для отработки успешного
        //todo считывания и срабатывания exceptions
    }

    @After
    public void tearDown() {

    }

    @Test(expected = InvalidValuesException.class)
    public void TestSetArrayWithException() throws InvalidValuesException {

/*        StringHelper sh = new StringHelper();
        sh.setArray("12n58 3 - 12\n89A2.3kk8,7");
        final int[] expecteds = {-12, 58, -3, 78, 2, 3, 8, 7};
        final int[] actual = sh.getArray();
        assertArrayEquals(expecteds, actual);*/
    }

    @Test
    public void TestSetArray() throws InvalidValuesException {

/*        StringHelper sh = new StringHelper();
        sh.setArray("12n58 3- - 12\n89A2.3kk8,7");
        final int[] expecteds = {12, 58, 3, 12, 89, 2, 3, 8, 7};
        final int[] actual = sh.getArray();
        assertArrayEquals(expecteds, actual);*/
    }


}