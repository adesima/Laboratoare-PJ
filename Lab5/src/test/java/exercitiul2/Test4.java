package exercitiul2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test4 {
    @Test
    public void testCmmmc() {
        PerecheNumere p = new PerecheNumere(2, 3);
        assertEquals(6, p.cmmmc());

        p = new PerecheNumere(10, 15);
        assertEquals(30, p.cmmmc());

        p = new PerecheNumere(4, 6);
        assertEquals(12, p.cmmmc());
    }
}
