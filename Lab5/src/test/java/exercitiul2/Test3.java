package exercitiul2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test3 {
    @Test
    public void testSumaCifrelorEgala() {
        PerecheNumere p = new PerecheNumere(123, 321);
        assertTrue(p.sumaCifrelorEgala());

        p = new PerecheNumere(214, 322);
        assertTrue(p.sumaCifrelorEgala());

        p = new PerecheNumere(123, 214);
        assertFalse(p.sumaCifrelorEgala());
    }
}
