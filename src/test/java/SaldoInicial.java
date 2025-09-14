import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

class SaldoInicial {

    @Test
    void deveConfigurarSaldoInicialCorreto() {
        DigitalWallet dw = new DigitalWallet("John Doe", 0);

        assertEquals(0, dw.getBalance());
    }

    @Test
    void deveLancarExcecaoParaSaldoInicialNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DigitalWallet("John Doe", -6);
        });
    }
}