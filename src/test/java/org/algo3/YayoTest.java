package org.algo3;



import org.algo3.modelo.Chiste;
import org.algo3.modelo.Yayo;
import org.junit.*;


import java.util.ArrayList;

public class YayoTest {

    @BeforeClass
    public static void beforeAll(){
        System.out.println("BEFORE ALL - Esta funcion se corre antes de correr todos los test");
    }

    // ATENCION - static
    @Before
    public void beforeEach(){
        System.out.println("BEFORE EACH - Esta funcion se corre antes de correr cada uno de los test!");
    }

    // ATENCION - static
    @AfterClass
    public static void afterAll(){
        System.out.println("AFTER ALL - Esta funcion se corre luego de correr todos los test");
    }

    @After
    public void afterEach(){
        System.out.println("AFTER EACH - Esta funcion se corre luego de correr cada uno de los test!");
    }

    @Test
    public void cuandoSeInstanciaYayoNoTieneChistes(){
        // Arrange
        Yayo yayo = new Yayo();

        //Act
        ArrayList<Chiste> chistes = yayo.todosLosChistes();

        //Assert
        Assert.assertEquals(0, chistes.size());
    }

    /*Como podemos testear esto?*/
    @Test
    public void yayoCuentaUnChisteDeProgramacionEnEspanniol(){
        // Arrange
        Yayo yayo = new Yayo();

        //Act
        Chiste chiste = yayo.contarChiste();

        //Assert
        Assert.assertEquals(true, chiste.tieneCategoria("Programming"));
    }

    /*Como podemos testear esto sin depender de la fecha?*/
    @Test
    public void yayoCuentaUnChisteDeNavidadEnIngles(){
        // Arrange
        Yayo yayo = new Yayo();

        //Act
        Chiste chiste = yayo.contarChiste();

        //Assert
        Assert.assertEquals(true, chiste.tieneCategoria("Christmas"));
    }

    @Test
    public void yayoCuentaUnChisteMalisimo(){
        // Arrange
        Yayo yayo = new Yayo();

        //Act
        Chiste chiste = yayo.contarChiste();

        //Assert
        Assert.assertEquals(true,chiste.esMalo());

    }

    @Ignore("Test es ignorado")
    @Test
    public void testSame() {
        Assert.assertEquals(1, 1);
    }
}
