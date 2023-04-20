package br.com.fanbank.conta;

import br.com.fanbank.enums.TipoTransferencia;

import java.time.LocalDateTime;

// VO visual object
// DTO data transfer object
public class TransferenciaDTO {

    Conta origem;
    Conta destino;
    TipoTransferencia tipoTransferencia;
    double valor;
    LocalDateTime data;

    public Conta getOrigem() {
        return origem;
    }

    public void setOrigem(Conta origem) {
        this.origem = origem;
    }

    public Conta getDestino() {
        return destino;
    }

    public void setDestino(Conta destino) {
        this.destino = destino;
    }

    public TipoTransferencia getTipoTransferencia() {
        return tipoTransferencia;
    }

    public void setTipoTransferencia(TipoTransferencia tipoTransferencia) {
        this.tipoTransferencia = tipoTransferencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
