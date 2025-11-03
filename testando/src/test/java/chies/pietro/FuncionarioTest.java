package chies.pietro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    // -------------------------
    // PARTIÇÕES DE EQUIVALÊNCIA
    // -------------------------

    @Test
    void deveLancarExcecaoQuandoSalarioInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Funcionario(-1000));
    }

    @Test
    void deveCalcularCorretamenteParaFaixaAte2500() {
        Funcionario f = new Funcionario(2000);
        assertEquals(90.0, f.getINSS(), 0.01);         // 4.5% de 2000
        assertEquals(0.0, f.getIRPF(), 0.01);          // sem IR
        assertEquals(1910.0, f.getSalarioLiquido(), 0.01);
    }

    @Test
    void deveCalcularCorretamenteParaFaixaEntre2500e5000() {
        Funcionario f = new Funcionario(4000);
        assertEquals(180.0, f.getINSS(), 0.01);
        assertEquals(180.0, f.getIRPF(), 0.01);
        assertEquals(3640.0, f.getSalarioLiquido(), 0.01);
    }

    @Test
    void deveCalcularCorretamenteParaFaixaAcimaDe5000() {
        Funcionario f = new Funcionario(7000);
        assertEquals(225.0, f.getINSS(), 0.01);       // teto do INSS
        assertEquals(540.0, f.getIRPF(), 0.01);
        assertEquals(6235.0, f.getSalarioLiquido(), 0.01);
    }

    // -----------------
    // VALORES LIMITES
    // -----------------

    @Test
    void deveLancarExcecaoQuandoSalarioIgualAZero() {
        assertThrows(IllegalArgumentException.class, () -> new Funcionario(0));
    }

    @Test
    void deveCalcularCorretamenteLogoAcimaDoZero() {
        Funcionario f = new Funcionario(1);
        assertEquals(0.045, f.getINSS(), 0.0001);
        assertEquals(0.0, f.getIRPF(), 0.0001);
        assertEquals(0.955, f.getSalarioLiquido(), 0.0001);
    }

    @Test
    void deveCalcularCorretamenteNoLimiteDoIR() {
        Funcionario f = new Funcionario(2500);
        assertEquals(112.5, f.getINSS(), 0.01);
        assertEquals(0.0, f.getIRPF(), 0.01);
        assertEquals(2387.5, f.getSalarioLiquido(), 0.01);
    }

    @Test
    void deveCalcularCorretamenteLogoAcimaDoLimiteDoIR() {
        Funcionario f = new Funcionario(2500.01);
        assertEquals(112.5, f.getINSS(), 0.01);
        assertEquals(0.0012, f.getIRPF(), 0.0001);
        assertEquals(2387.5088, f.getSalarioLiquido(), 0.001);
    }

    @Test
    void deveCalcularCorretamenteNoLimiteDoINSS() {
        Funcionario f = new Funcionario(5000);
        assertEquals(225.0, f.getINSS(), 0.01);
        assertEquals(300.0, f.getIRPF(), 0.01);
        assertEquals(4475.0, f.getSalarioLiquido(), 0.01);
    }

    @Test
    void deveCalcularCorretamenteLogoAcimaDoLimiteDoINSS() {
        Funcionario f = new Funcionario(5000.01);
        assertEquals(225.0, f.getINSS(), 0.01);
        assertEquals(300.0012, f.getIRPF(), 0.001);
        assertEquals(4475.0088, f.getSalarioLiquido(), 0.001);
    }
}
