package exercitiul2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test2 {
    @Test
    public void testAcelasiNumarCifrePare() {
        PerecheNumere p = new PerecheNumere(246, 864);
        assertTrue(p.acelasiNumarCifrePare());

        p = new PerecheNumere(1234, 5678);
        assertTrue(p.acelasiNumarCifrePare());

        p = new PerecheNumere(123, 456);
        assertFalse(p.acelasiNumarCifrePare());
    }
}
