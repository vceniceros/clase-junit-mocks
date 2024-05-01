package org.algo3;

import org.algo3.modelo.Chiste;
import org.algo3.modelo.Yayo;
import org.algo3.modelo.invitado.Invitado;
import org.algo3.modelo.proveedor.Proveedor;
import org.algo3.modelo.proveedor.ProveedorWeb;
import org.algo3.modelo.tiempo.Tiempo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
public class YayoIntegrationTest {

    @Mock
    private Proveedor proveedorMock;
    @Mock private Invitado invitadoMock;
    @Mock private Tiempo tiempoMock;

    private AutoCloseable closeable;

    @Before
    public void beforeEach(){
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.reset(proveedorMock, invitadoMock, tiempoMock);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void yayoSolicitaAlProovedorWebChistesDeProgramacion(){

        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(2);
        Proveedor proveedorWeb = new ProveedorWeb();
        Yayo yayo = new Yayo(proveedorWeb, invitadoMock);

        Chiste chiste = yayo.contarChiste(tiempoMock);

        Assert.assertTrue(chiste.tieneCategoria("Programming"));
    }
















}
