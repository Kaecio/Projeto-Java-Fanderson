package br.com.fanbank;

import br.com.fanbank.caixa.eletronico.CaixaEletronico;
import br.com.fanbank.conta.*;
import br.com.fanbank.enums.TipoTransferencia;

import java.time.LocalDateTime;

public class Fanbank {

    public static void main(String[] args) {

        System.out.println("Iniciando Sistema");

        ContaPJ fanbank = new ContaPJ();

        fanbank.setCnpj("1208931902");
        fanbank.setAgencia("1212");
        fanbank.setConta("1239-9");
        fanbank.setSaldo(100000.0);

        CaixaEletronico caixaEletronico = new CaixaEletronico(fanbank);

        System.out.println("Valor total do caixa eletrônico: " + caixaEletronico.getValorTotal());

        //Cliente efetua saque

        ContaAuxilio maria = new ContaAuxilio();
        maria.setNomeCliente("Maria");
        maria.setNumeroAuxilio("0001");
        maria.setAgencia("192");
        maria.setConta("3344-2");
        maria.setSaldo(100.0);

        ContaPF joaoDaSilva = new ContaPF();
        joaoDaSilva.setCpf("428.555.532.99");
        joaoDaSilva.setAgencia("0192");
        joaoDaSilva.setConta("12312-2");
        joaoDaSilva.setSaldo(4000.0);


        //transferencia

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setOrigem(joaoDaSilva);
        transferencia.setDestino(maria);
        transferencia.setTipoTransferencia(TipoTransferencia.TED);
        transferencia.setValor(500.0);
        transferencia.setData(LocalDateTime.now());

        //João da Silva efetua Saque
        try {
            double sacar = caixaEletronico.sacar(3000.0, joaoDaSilva);
            System.out.println("Saque realizado, total na conta do cliente é: " + joaoDaSilva.getSaldo() + " - total sacado: R$ "+sacar);
            double depositado = caixaEletronico.depositar(20.0, joaoDaSilva);
            System.out.println("------------------------------------------------");
            System.out.println("Deposito realizado, total na conta do cliente é: " + joaoDaSilva.getSaldo() + " - total depositado: R$ "+depositado);
            System.out.println("------------------------------------------------");
            System.out.println("Saldo conta " + maria.getNomeCliente() + " é: " + maria.getSaldo());
            double tranferencia = caixaEletronico.transferir(transferencia);
            System.out.println("Transferencia realizada, total na conta do cliente é: " + maria.getSaldo() + " - total transferido: R$ " + tranferencia);
            System.out.println("Saldo conta " + maria.getNomeCliente() + " é: " + maria.getSaldo());
            System.out.println("------------------------------------------------");
            System.out.println("Valor total atualizado caixa eletrônico: " + caixaEletronico.getValorTotal());
            System.out.println("------------------------------------------------");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}
