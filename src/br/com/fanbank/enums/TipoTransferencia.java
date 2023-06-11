package br.com.fanbank.enums;

public enum TipoTransferencia {

    DOC(1),
    TED(2),
    PIX(3),
    ENVELOPE(4),
    MALOTE(5);

    private int tipo;

    TipoTransferencia(int tipo) {
        this.tipo = tipo;
    }
    public int getTipo() {
        return tipo;
    }
}
