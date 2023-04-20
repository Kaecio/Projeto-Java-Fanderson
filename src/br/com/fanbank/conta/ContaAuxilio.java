package br.com.fanbank.conta;

public class ContaAuxilio extends Conta{

    private String numeroAuxilio;

    private String nomeCliente;

    public String getNumeroAuxilio() {
        return numeroAuxilio;
    }

    public void setNumeroAuxilio(String numeroAuxilio) {
        this.numeroAuxilio = numeroAuxilio;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

}
