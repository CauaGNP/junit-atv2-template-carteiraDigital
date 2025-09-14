import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;

public class Pagamento {

    @ParameterizedTest
    @CsvSource({
            "200, 120 ,true",
            "200, 140 ,true"
    })
    void pagamentoComCarteiraVerificadaENaoBloqueada(double inicial, double valor, boolean esperado) {
        DigitalWallet dw = new DigitalWallet("John Doe", inicial);
        dw.verify();
        assumeTrue(dw.isVerified(), "Erro!! A conta não é verificada.");

        assertEquals(true, dw.pay(valor));
    }

    @ParameterizedTest
    @ValueSource(doubles = { 110, 220 })
    void deveLancarExcecaoParaPagamentoInvalido(double valor) {
        DigitalWallet dw = new DigitalWallet("John Doe", 100);

        assertThrows(IllegalStateException.class, () -> dw.pay(valor));
        assertEquals(100, dw.getBalance());
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {

        DigitalWallet dw = new DigitalWallet("John Doe", 20);
        dw.lock();
        dw.verify();

        // Aqui a conta está bloqueada e verificada;
        assertThrows(IllegalStateException.class, () -> dw.pay(2));

        dw.lock();
        dw.verify();

        // Já aqui a conta não está bloqueada, mas, não está verificada;
        assertThrows(IllegalStateException.class, () -> dw.pay(2));
    }
}
