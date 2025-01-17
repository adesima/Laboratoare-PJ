package exercitiul2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1 {
    @Test
    public void testSuntConsecutiveFibonacci() {
        PerecheNumere p = new PerecheNumere(3, 5);
        assertTrue(p.suntConsecutiveFibonacci());

        p = new PerecheNumere(21, 34);
        assertTrue(p.suntConsecutiveFibonacci());

        p = new PerecheNumere(5, 6);
        assertFalse(p.suntConsecutiveFibonacci());
    }
}
