import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;

class Deposito {

    @ParameterizedTest
    @ValueSource(doubles = { 90, 10, 59 })
    void deveDepositarValoresValidos(double amount) {

        DigitalWallet dw = new DigitalWallet("John Doe", 0);
        dw.deposit(amount);
        assertEquals(amount, dw.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = { -90 })
    void deveLancarExcecaoParaDepositoInvalido(double amount) {

        DigitalWallet dw = new DigitalWallet("John Doe", 0);
        assertThrows(IllegalArgumentException.class, () -> dw.deposit(amount));
    }
}