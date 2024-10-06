package org.algo3;


import java.lang.reflect.Array;

import org.algo3.modelo.Chiste;
import org.algo3.modelo.Yayo;
import org.algo3.modelo.invitado.Invitado;
import org.algo3.modelo.proveedor.Proveedor;
import org.algo3.modelo.tiempo.Tiempo;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import org.mockito.internal.matchers.Equals;

public class YayoMejoradoTest {

    @Mock private Proveedor proveedorMock;
    @Mock private Invitado invitadoMock;
    @Mock private Tiempo tiempoMock;

    private AutoCloseable closeable;


    // Inicializamos cada mock
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
    public void cuandoSeInstanciaYayoNoTieneChistes(){
        // Arrange
        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        ArrayList<Chiste> chistes = yayo.todosLosChistes();

        //Assert
        Assert.assertEquals(0, chistes.size());
    }

    @Test
    public void yayoCuentaUnChisteDeProgramacionEnEspanniolSiElDiaEsPar(){

        // Arrange
        Chiste chisteFalso = new Chiste("Programming","hola pepe","Hola jose");
        when(proveedorMock.solicitarChiste("Programming", "es")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(2);

        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertTrue(chiste.tieneCategoria("Programming"));
    }

    @Test
    public void yayoInvocaAlProvedorParaSolicitarChisteDeProgramacion(){

        // Arrange
        Chiste chisteFalso = new Chiste("Programming","hola pepe","Hola jose");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Programming", "es")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(2);
        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        verify(YayoMejoradoTest.this.proveedorMock, times(1)).solicitarChiste("Programming", "es");
    }

    @Test
    public void yayoCuentaUnChisteDeNavidadEnInglesSiElDiaEsInpar(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);

        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertTrue(chiste.tieneCategoria("Christmas"));
    }

    @Test
    public void yayoInvocaAlProvedorParaSolicitarChisteDeNavidad(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        verify(YayoMejoradoTest.this.proveedorMock, times(1)).solicitarChiste("Christmas", "en");
    }

    @Test
    public void yayoCuentaUnChisteMalisimo(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(invitadoMock.puntuar(chisteFalso)).thenReturn(2);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertTrue(chiste.esMalo());

    }

    @Test
    public void yayoCuentaUnChisteBuenisimo(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(invitadoMock.puntuar(chisteFalso)).thenReturn(10);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertFalse(chiste.esMalo());

    }


    @Test 
    public void dosChistesComparanCualEsMejor(){
        //arrange
        Chiste unChisteFalso = new Chiste("Programming","por que los carteristas rompen tell dont ask","porque te sacan la billetera en vez de pedirte que se las des");
        Chiste otroChisteFalso = new Chiste("Programming", "porque es importante el manejo de errores", "preguntenle a Boeing");
        unChisteFalso.aplicarPuntaje(5);
        otroChisteFalso.aplicarPuntaje(10);

        //act
        int comparacion = unChisteFalso.compararCon(otroChisteFalso);
        

        //assert
        Assert.assertTrue(comparacion == -1);
    }

    @Test
    public void yayoCuentaSus2MejoresChistes(){
        //arrange
        Chiste unChisteFalso = new Chiste("Programming","por que los carteristas rompen tell dont ask","porque te sacan la billetera en vez de pedirte que se las des");
        Chiste otroChisteFalso = new Chiste("Programming", "porque es importante el manejo de errores", "preguntenle a Boeing");
        Chiste unTercerChisteFalso = new Chiste("Programming", "mi mama me dijo que soy alguien muy positivo", "dijo que soy un 0 a la izquierda");
       
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Programming", "es")).thenReturn(unChisteFalso).thenReturn(otroChisteFalso)
        .thenReturn(unTercerChisteFalso);

        when(invitadoMock.puntuar(unChisteFalso)).thenReturn(5);
        when(invitadoMock.puntuar(otroChisteFalso)).thenReturn(10);
        when(invitadoMock.puntuar(unTercerChisteFalso)).thenReturn(8);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(6);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);

        ArrayList chistesEsperados = new ArrayList<Chiste>();
        chistesEsperados.add(otroChisteFalso);
        chistesEsperados.add(unTercerChisteFalso);
        

        //act

        yayo.contarChiste(tiempoMock);
        yayo.contarChiste(tiempoMock);
        yayo.contarChiste(tiempoMock);
        
        ArrayList listaDeChistes = yayo.mejoresChistes(2);
        
        
        //asset
        Assert.assertEquals(chistesEsperados, listaDeChistes);


    }

    @Test
    public void yaCuentaUnChisteConElProveedorDelTxt(){
        
    }


    @Ignore("Test es ignorado")
    @Test
    public void testSame() {
        Assert.assertEquals(1, 1);
    }
}
