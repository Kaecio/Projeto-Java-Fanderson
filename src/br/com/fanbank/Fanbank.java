package br.com.fanbank;

import br.com.fanbank.caixa.eletronico.CaixaEletronico;
import br.com.fanbank.conta.ContaPF;

public class Fanbank {

    public static void main(String[] args) {

        /**
         * @// TODO: 15/03/2023
         * Criar novo tipo de conta
         */
        System.out.println("Iniciando Sistema");
        CaixaEletronico caixaEletronico = new CaixaEletronico(1000.0);
        System.out.println("Valor total do caixa eletrônico: "+caixaEletronico.getValorTotal());

        //Cliente efetua saque

        ContaPF joaoDaSilva = new ContaPF();
        joaoDaSilva.setCpf("428.555.532.99");
        joaoDaSilva.setAgencia("0192");
        joaoDaSilva.setConta("12312-2");
        joaoDaSilva.setSaldo(400.0);

        //João da Silva efetua Saque
        try {
            double total = caixaEletronico.sacar(11100.0, joaoDaSilva);
            System.out.println("Saque realizado, total na conta do cliente é:"+joaoDaSilva.getSaldo() + "- total sacado: R$: "+total);
            System.out.println("Valor total atualizado caixa eletrônico: "+caixaEletronico.getValorTotal());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
