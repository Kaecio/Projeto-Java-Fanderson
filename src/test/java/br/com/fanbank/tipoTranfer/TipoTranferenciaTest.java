package br.com.fanbank.tipoTranfer;

import br.com.fanbank.enums.TipoTransferencia;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class TipoTranferenciaTest {

    @Test
    public void validaEnumTipoTransferencia() throws ExecutionException{
        TipoTransferencia[] tipoTransferencia = TipoTransferencia.values();

        for (TipoTransferencia tipo : tipoTransferencia) {
            assertNotNull(tipo);
            assertTrue(tipo instanceof TipoTransferencia);
            assertNotNull(tipo.name());
            assertNotNull(tipo.toString());
        }
    }
}
