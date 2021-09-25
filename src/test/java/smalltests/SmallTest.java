package smalltests;

import org.junit.Assert;
import org.junit.Test;

public class SmallTest {

    @Test
    public void Test1() {
        System.out.println("Test 1 PASSED");
    }

    @Test
    public void Test2() {
        System.out.println("Test 2 fail");
//        Assert.fail();
    }

}
