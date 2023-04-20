package br.com.fanbank.caixa.eletronico;

import br.com.fanbank.conta.Conta;
import br.com.fanbank.conta.TransferenciaDTO;

import java.util.concurrent.ExecutionException;

public class CaixaEletronico extends Conta{

    private Double valorTotal;

    public CaixaEletronico(Double valorTotal){
        this.valorTotal = valorTotal;
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
            /**
             * @// TODO: 15/03/2023
             *
             * Validar quando houver saque maior do que o valor disponível no caíxa eletrônico.
             */
            throw new Exception("Valor maior do que possui na conta.");
        }

    }

    /**
     * @// TODO: 15/03/2023
     * Validar depósito de dinheiro na conta
     */
    public Double depositar(Double valor, Conta conta) throws Exception {
        if (valor <= 0 ) throw new Exception("Deposito abaixo do permitido");
        if (valor > 0){
            double valorDeposito = conta.getSaldo() + valor;
            conta.setSaldo(valorDeposito);
            return valor;
        }
        return valor;
    }

    /**
     * @// TODO: 15/03/2023
     * Implementar método para transferência entre contas
     */
    public Double transferir(TransferenciaDTO tranferencia) throws Exception {
        Conta origem =  tranferencia.getOrigem();
        Conta destina = tranferencia.getDestino();
        double valor = tranferencia.getValor();
        if (origem.getSaldo() < valor)throw new Exception("Valor da transferencia maior que o saldo atual");

        origem.setSaldo(origem.getSaldo() - valor);
        destina.setSaldo(destina.getSaldo() + valor);
        return valor;
        /**
         * @// TODO 13/04/2023
         *
          */
    }
}
