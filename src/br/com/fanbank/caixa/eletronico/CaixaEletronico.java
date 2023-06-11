package br.com.fanbank.caixa.eletronico;

import br.com.fanbank.conta.Conta;
import br.com.fanbank.conta.TransferenciaDTO;
import br.com.fanbank.enums.TipoTransferencia;

import java.math.BigDecimal;

public class CaixaEletronico{

    private Conta conta;
    private Double valorTotal;

    public CaixaEletronico(Conta conta){
        this.conta = conta;
        this.valorTotal = conta.getSaldo();
    }

    public Double getValorTotal(){
        return this.valorTotal;
    }

    public Double sacar(Double valor, Conta conta) throws Exception {
        if (valorTotal < valor) throw new Exception("Caixa eletronico como saldo menor que o desejável para o saque.");
        if(valorTotal >= valor && valor <= conta.getSaldo()){
            double valorNovo = conta.getSaldo() - valor;
            conta.setSaldo(valorNovo);
            valorTotal -= valor;
            return valor;
        }else{
            throw new Exception("Você não possui saldo suficiente. Valor maior do que possui na conta.");
        }

    }

    public Double depositar(Double valor, Conta conta) throws Exception {
        if (valor <= 0 ) throw new Exception("Deposito abaixo do permitido.");
        if (valor > 0){
            double valorDeposito = conta.getSaldo() + valor;
            conta.setSaldo(valorDeposito);
            return valor;
        }
        return valor;
    }

    public Double transferir(TransferenciaDTO transferencia) throws Exception {

        Conta origem =  transferencia.getOrigem();
        Conta destina = transferencia.getDestino();
        double valor = transferencia.getValor();
        if (origem.getSaldo() < valor)throw new Exception("Valor da transferencia maior que o saldo atual.");

        if (transferencia.getTipoTransferencia() == TipoTransferencia.TED ){
            origem.setSaldo(origem.getSaldo() - valor -3.00);
            destina.setSaldo(destina.getSaldo() + valor);
        }

        if (transferencia.getTipoTransferencia() == TipoTransferencia.DOC ){
            origem.setSaldo(origem.getSaldo() - valor - 10.00);
            destina.setSaldo(destina.getSaldo() + valor);
        }

        if (transferencia.getTipoTransferencia() == TipoTransferencia.PIX ){
            origem.setSaldo(origem.getSaldo() - valor - 0.00);
            destina.setSaldo(destina.getSaldo() + valor);
        }

        if (transferencia.getTipoTransferencia() == TipoTransferencia.ENVELOPE ){
            origem.setSaldo(origem.getSaldo() - valor - 1.00);
            destina.setSaldo(destina.getSaldo() + valor);
        }

        if (transferencia.getTipoTransferencia() == TipoTransferencia.MALOTE ){
            origem.setSaldo(origem.getSaldo() - valor - 5.00);
            destina.setSaldo(destina.getSaldo() + valor);
        }

        return valor;
    }
}
