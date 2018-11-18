package atenea.fiuba.algoIII.ageoOfEmpires;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ArmaDeAsedioTest {

    private ArmaDeAsedio crearArmaDeAsedio(){

        int vidaMaxima = 150;
        return new ArmaDeAsedio(vidaMaxima);
    }

    @Test
    public void estaMontada_luegoDeLaCreacion_DevuelveFalse(){

        // Arrange
        int vidaMaxima = 150;

        // Act
        ArmaDeAsedio armaDeAsedio = new ArmaDeAsedio(vidaMaxima);
        boolean estaMontada = armaDeAsedio.estaMontada();

        // Assert
        Assert.assertEquals(false, estaMontada);
    }

    @Test
    public void estaMontada_luegoDeMontarla_DevuelveTrue(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();

        // Act
        armaDeAsedio.montar();
        boolean estaMontada = armaDeAsedio.estaMontada();

        // Assert
        Assert.assertEquals(true, estaMontada);

    }

    @Test
    public void estaMontada_luegoDeDesmontarla_DevuelveFalse(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();

        // Act
        armaDeAsedio.desmontar();
        boolean estaMontada = armaDeAsedio.estaMontada();

        // Assert
        Assert.assertEquals(false, estaMontada);

    }

    @Test
    public void atacar_CuandoEstaMontada_NoLanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();

        // Act
        armaDeAsedio.atacar();
    }

    @Test(expected = OperacionInvalidaDadoElEstadoActualDelObjetoExcepcion.class)
    public void atacar_CuandoNoEstaMontada_LanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();

        // Act
        armaDeAsedio.atacar();
    }


    @Test(expected = OperacionInvalidaDadoElEstadoActualDelObjetoExcepcion.class)
    public void mover_CuandoEstaMontada_LanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();

        // Act
        armaDeAsedio.mover();
    }

    @Test
    public void mover_CuandoNoEstaMontada_NoLanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();

        // Act
        armaDeAsedio.mover();
    }

    @Test
    public void atacar_APlazaCentral_LeProduceDanio75(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();

        int vidaInicialPlazaCentral = 450;
        PlazaCentral plazaCentral = new PlazaCentral(vidaInicialPlazaCentral, Mockito.mock(UnidadesFabrica.class));

        int danioEsperado = 75;

        // Act
        armaDeAsedio.atacar(plazaCentral);
        int danioProducido = vidaInicialPlazaCentral - plazaCentral.getVida();

        // Assert
        Assert.assertEquals(danioEsperado, danioProducido);
    }

    @Test
    public void atacar_ACuartel_LeProduceDanio75(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();

        int vidaMaximaCuartel = 250;
        int vidaInicialCuartel = 250;
        Cuartel cuartel = new Cuartel(vidaMaximaCuartel, vidaInicialCuartel, Mockito.mock(UnidadesFabrica.class));

        int danioEsperado = 75;

        // Act
        armaDeAsedio.atacar(cuartel);
        int vidaFinalCuartel = cuartel.getVida();
        int danioProducido = vidaInicialCuartel- vidaFinalCuartel;

        // Assert
        Assert.assertEquals(danioEsperado, danioProducido);

    }

    @Test
    public void atacar_ACastillo_LeProduceDanio75(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();

        int vidaMaximaCastillo = 1000;
        int vidaInicialCastillo = 1000;
        Castillo castillo = new Castillo(vidaMaximaCastillo, vidaInicialCastillo, Mockito.mock(UnidadesFabrica.class));

        int danioEsperado = 75;

        // Act
        armaDeAsedio.atacar(castillo);
        int vidaFinalCastillo = castillo.getVida();
        int danioProducido = vidaInicialCastillo- vidaFinalCastillo;

        // Assert
        Assert.assertEquals(danioEsperado, danioProducido);

    }
}