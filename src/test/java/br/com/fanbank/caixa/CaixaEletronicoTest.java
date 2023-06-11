package br.com.fanbank.caixa;

import br.com.fanbank.caixa.eletronico.CaixaEletronico;
import br.com.fanbank.conta.ContaAuxilio;
import br.com.fanbank.conta.ContaPF;
import br.com.fanbank.conta.ContaPJ;
import br.com.fanbank.conta.TransferenciaDTO;
import br.com.fanbank.enums.TipoTransferencia;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class CaixaEletronicoTest {

    @Test
    public void validaSaqueCaixaSaldoPositivo() throws Exception {

        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        Double valorSaque = caixaEletronico.sacar(1000.0, joaoSilva);
        Double esperado = 3000.0;
        assertEquals(esperado, joaoSilva.getSaldo());
    }

    @Test
    public void validaMensagemErroSaqueCaixaSaldoNegativo() throws Exception {
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        Exception exception = assertThrows(Exception.class, () ->{
                caixaEletronico.sacar(10000.0, joaoSilva);
        });

        String mensagemEsperada = "Você não possui saldo suficiente. Valor maior do que possui na conta.";
        String mensagemObtida = exception.getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
        // saque do Joao é maior do que ele possui em sua conta
    }

    @Test
    public void validaMensagemErroCaixaEletronicoMenorQueSaldoContaCliente() throws Exception {
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        Exception exception = assertThrows(Exception.class, () ->{
            caixaEletronico.sacar(100000.0, joaoSilva);
        });

        String mensagemEsperada = "Caixa eletronico como saldo menor que o desejável para o saque.";
        String mensagemObtida = exception.getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void validaDepositoCaixaEletronico() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        Double valorSaque = caixaEletronico.depositar(1000.0, joaoSilva);
        Double esperado = 5000.0;
        assertEquals(esperado, joaoSilva.getSaldo());
    }

    @Test
    public void validaDepositoNaoPermitidoSaldoIgualAZero() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        Exception exception = assertThrows(Exception.class, () ->{
            caixaEletronico.depositar(0.0, joaoSilva);
        });

        String mensagemEsperada = "Deposito abaixo do permitido.";
        String mensagemObtida = exception.getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    @Test
    public void validaDepositoNaoPermitidoSaldoMenorQueZero() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        Exception exception = assertThrows(Exception.class, () ->{
            caixaEletronico.depositar(-0.1, joaoSilva);
        });

        String mensagemEsperada = "Deposito abaixo do permitido.";
        String mensagemObtida = exception.getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    @Test
    public void validaTranferenciaEntreContasComTipoTransferenciaTed() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        ContaAuxilio maria = createContaClienteAuxilio();

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setOrigem(joaoSilva);
        transferencia.setDestino(maria);
        transferencia.setValor(500.0);
        transferencia.setTipoTransferencia(TipoTransferencia.TED);
        caixaEletronico.transferir(transferencia);

        Double esperadoMaria = 1500.0;
        Double esperadoJoao = 3497.0;
        assertEquals(esperadoMaria, maria.getSaldo());
        assertEquals(esperadoJoao, joaoSilva.getSaldo());
    }

    @Test
    public void validaTranferenciaEntreContasComTipoTransferenciaDoc() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        ContaAuxilio maria = createContaClienteAuxilio();

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setOrigem(joaoSilva);
        transferencia.setDestino(maria);
        transferencia.setValor(500.0);
        transferencia.setTipoTransferencia(TipoTransferencia.DOC);
        caixaEletronico.transferir(transferencia);

        Double esperadoMaria = 1500.0;
        Double esperadoJoao = 3490.0;
        assertEquals(esperadoMaria, maria.getSaldo());
        assertEquals(esperadoJoao, joaoSilva.getSaldo());
    }

    @Test
    public void validaTranferenciaEntreContasComTipoTransferenciaPix() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        ContaAuxilio maria = createContaClienteAuxilio();

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setOrigem(joaoSilva);
        transferencia.setDestino(maria);
        transferencia.setValor(500.0);
        transferencia.setTipoTransferencia(TipoTransferencia.PIX);
        caixaEletronico.transferir(transferencia);

        Double esperadoMaria = 1500.0;
        Double esperadoJoao = 3500.0;
        assertEquals(esperadoMaria, maria.getSaldo());
        assertEquals(esperadoJoao, joaoSilva.getSaldo());
    }

    @Test
    public void validaTranferenciaEntreContasComTipoTransferenciaEnvelope() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        ContaAuxilio maria = createContaClienteAuxilio();

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setOrigem(joaoSilva);
        transferencia.setDestino(maria);
        transferencia.setValor(500.0);
        transferencia.setTipoTransferencia(TipoTransferencia.ENVELOPE);
        caixaEletronico.transferir(transferencia);

        Double esperadoMaria = 1500.0;
        Double esperadoJoao = 3499.0;
        assertEquals(esperadoMaria, maria.getSaldo());
        assertEquals(esperadoJoao, joaoSilva.getSaldo());
    }

    @Test
    public void validaTranferenciaEntreContasComTipoTransferenciaMalote() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        ContaAuxilio maria = createContaClienteAuxilio();

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setOrigem(joaoSilva);
        transferencia.setDestino(maria);
        transferencia.setValor(500.0);
        transferencia.setTipoTransferencia(TipoTransferencia.MALOTE);
        caixaEletronico.transferir(transferencia);

        Double esperadoMaria = 1500.0;
        Double esperadoJoao = 3495.0;
        assertEquals(esperadoMaria, maria.getSaldo());
        assertEquals(esperadoJoao, joaoSilva.getSaldo());
    }

    @Test
    public void validaTransferenciaNaoPermitidaPorSaldoMenorQueValorAhTransferir() throws Exception{
        CaixaEletronico caixaEletronico = new CaixaEletronico(this.createContaBanco());
        ContaPF joaoSilva = createContaCliente();
        ContaAuxilio maria = createContaClienteAuxilio();

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setOrigem(joaoSilva);
        transferencia.setDestino(maria);
        transferencia.setValor(4000.1);

        Exception exception = assertThrows(Exception.class, () ->{
            caixaEletronico.transferir(transferencia);
        });

        String mensagemEsperada = "Valor da transferencia maior que o saldo atual.";
        String mensagemObtida = exception.getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
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

    public ContaAuxilio createContaClienteAuxilio(){
        ContaAuxilio maria = new ContaAuxilio();
        maria.setAgencia("0192");
        maria.setConta("12399-9");
        maria.setSaldo(1000.0);
        return maria;
    }
}
