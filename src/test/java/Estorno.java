import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;

class Estorno {
    static Stream<Arguments> valoresEstorno() {
        return Stream.of(
                Arguments.of(100.0, 10.0, 110.0),
                Arguments.of(0.0, 5.0, 5.0),
                Arguments.of(50.0, 0.01, 50.01));
    }

    @ParameterizedTest
    @MethodSource("valoresEstorno")
    void refundComCarteiraValida(double inicial, double valor, double saldoEsperado) {
        DigitalWallet dw = new DigitalWallet("John Doe", inicial);
        dw.verify();

        dw.refund(valor);
        assertEquals(saldoEsperado, dw.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0 })
    void deveLancarExcecaoParaRefundInvalido(double valor) {
        DigitalWallet dw = new DigitalWallet("John Doe", 0);
        dw.verify();

        assertThrows(IllegalArgumentException.class, () -> dw.refund(valor));
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        DigitalWallet dw = new DigitalWallet("John Doe", 1000);

        assertThrows(IllegalStateException.class, () -> dw.refund(10));
    }
}
