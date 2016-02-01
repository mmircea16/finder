package fourholescenter;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FourHolesFinderTest {

    @Test
    public void test() {
        float[] x = new float[]{180.01f,120.04f, 181.13f, 119.88f };
        float[] y = new float[]{ 49.89f,-10.24f, -13.21f, 50.02f};
        FourHolesCenterFinder finder = new FourHolesCenterFinder(x, y);

        finder.getOrder();
        float[] expectedResult = {149.57f, 19.36f};
        assertArrayEquals(finder.getCenter(), expectedResult, 0.01f);
    }
}
