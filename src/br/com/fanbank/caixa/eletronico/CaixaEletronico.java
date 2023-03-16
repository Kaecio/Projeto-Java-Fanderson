package br.com.fanbank.caixa.eletronico;

import br.com.fanbank.conta.Conta;

public class CaixaEletronico {

    private Double valorTotal;

    public CaixaEletronico(Double valorTotal){
        this.valorTotal = valorTotal;
    }

    public Double getValorTotal(){
        return this.valorTotal;
    }

    public Double sacar(Double valor, Conta conta) throws Exception {
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
    public void depositarDinheiro(Double valor, Conta conta){
        conta.setSaldo(valor);
    }

    /**
     * @// TODO: 15/03/2023
     * Implementar método para transferência entre contas
     */
}
