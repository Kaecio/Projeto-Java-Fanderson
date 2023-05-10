package br.com.fanbank.caixa;

import br.com.fanbank.caixa.eletronico.CaixaEletronico;
import br.com.fanbank.conta.ContaPF;
import br.com.fanbank.conta.ContaPJ;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CaixaEletronicoTest {

    @Test
    public void validaSaqueCaixaSaldoPositivo() throws Exception {

        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        Double valorSaque = caixaEletronico.sacar(1000.0, joaoSilva);
        Double esperado = 3000.0;
        assertEquals(esperado, joaoSilva.getSaldo());

    }

    public ContaPJ createContaBanco(){
        ContaPJ fanbank = new ContaPJ();

        fanbank.setCnpj("1208931902");
        fanbank.setAgencia("1212");
        fanbank.setConta("1239-9");
        fanbank.setSaldo(10000.0);

        return fanbank;
    }

    public ContaPF createContaCliente(){
        ContaPF joaoDaSilva = new ContaPF();
        joaoDaSilva.setCpf("428.555.532.99");
        joaoDaSilva.setAgencia("0192");
        joaoDaSilva.setConta("12312-2");
        joaoDaSilva.setSaldo(4000.0);
        return joaoDaSilva;
    }

}
