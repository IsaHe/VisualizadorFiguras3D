package v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerificarGrafosIsomorfosTest {

    VerificarGrafosIsomorfos grafo = new VerificarGrafosIsomorfos();

    int[][] grafoBase = {
        {0, 2, 0, 0},
        {2, 0, 1, 0},
        {0, 1, 0, 2},
        {0, 0, 2, 0}
    };
    int[][] grafo_a_modificar = {
        {0, 1, 0, 2},
        {1, 0, 2, 0},
        {0, 2, 0, 0},
        {2, 0, 0, 0}
    };
    int[][] grafoExamenA1 = {
            {0, 1, 0, 0, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {0, 0, 1, 2, 1},
            {0, 1, 0, 1, 0}
    };
    int[][] prueba = {
            {0, 0, 0, 0, 1},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {0, 0, 1, 2, 1},
            {1, 1, 0, 1, 0}
    };

    @Test
    void rotarGrafoBase() {

        int[][] probarGrafoInvertido = grafo.invertirGrafo(grafoBase);

        for (int i = 0; i < probarGrafoInvertido.length; i++) {
            for (int j = 0; j < probarGrafoInvertido.length; j++) {
                assertEquals(grafoBase[i][j], probarGrafoInvertido[j][i]);
            }
        }

    }

    @Test
    void rotarGrafo_a_Modificar() {

        int[][] probarGrafoInvertido = grafo.invertirGrafo(grafo_a_modificar);

        for (int i = 0; i < probarGrafoInvertido.length; i++) {
            for (int j = 0; j < probarGrafoInvertido.length; j++) {
                assertEquals(grafo_a_modificar[i][j], probarGrafoInvertido[j][i]);
            }
        }

    }

    @Test
    void permutarGrafoBase() {
        int[][] grafoPermutado = {
                {0, 0, 2, 0},
                {0, 0, 1, 2},
                {2, 1, 0, 0},
                {0, 2, 0, 0}
        };

        int[][] probarGrafoPermutado = grafo.permutarFilas_y_Columnas(grafoBase, 1, 2);

        for (int i = 0; i < grafoPermutado.length; i++) {
            for (int j = 0; j < grafoPermutado.length; j++) {
                assertEquals(grafoPermutado[i][j], probarGrafoPermutado[i][j]);
            }
        }
    }

    @Test
    void permutarGrafo_a_Modificar() {
        int[][] grafoPermutado = {
                {0, 0, 1, 2},
                {0, 0, 2, 0},
                {1, 2, 0, 0},
                {2, 0, 0, 0}
        };
        int[][] probarGrafoPermutado = grafo.permutarFilas_y_Columnas(grafo_a_modificar, 1, 2);

        for (int i = 0; i < grafoPermutado.length; i++) {
            for (int j = 0; j < grafoPermutado.length; j++) {
                assertEquals(grafoPermutado[i][j], probarGrafoPermutado[i][j]);
            }
        }
    }

    @Test
    void tienenQueTenerLosMismosGrados() {
        assertTrue(grafo.tienenLosMismosGrados(grafoBase, grafo_a_modificar));
    }

    @Test
    void tienenQueTenerLosMismosVertices() {
        assertTrue(grafo.tienenElMismoNumeroDeVertices(grafoBase, grafo_a_modificar));
    }

    @Test
    void tienenQueTenerLasMismasAristas() {
        assertTrue(grafo.tienenElMismoNumeroDeAristas(grafoBase, grafo_a_modificar));
    }

    @Test
    void listaGradosVerticesBase() {
        int[] gradosVertices = {2, 3, 3, 2};
        int[] gradoVerticesBase = grafo.gradosDeLosVertices(grafoBase);

        for (int i = 0; i < gradosVertices.length; i++) {
            assertEquals(gradosVertices[i], gradoVerticesBase[i]);
        }
    }

    @Test
    void listaGradosVerticesModificar() {
        int[] gradosVertices = {3, 3, 2, 2};
        int[] gradoVerticesModificar = grafo.gradosDeLosVertices(grafo_a_modificar);

        for (int i = 0; i < gradosVertices.length; i++) {
            assertEquals(gradosVertices[i], gradoVerticesModificar[i]);
        }
    }

    @Test
    void verificarGradosVerticesBase() {
        int[] gradosVerticesBase = grafo.gradosDeLosVertices(grafoBase);
        int[] gradosQueDeberianSer = {2, 3, 3, 2};

        for (int i = 0; i < gradosVerticesBase.length; i++) {
            assertEquals(gradosVerticesBase[i], gradosQueDeberianSer[i]);
        }
    }

    @Test
    void verificarGradosVerticesModificar() {
        int[] gradosVerticesModificar = grafo.gradosDeLosVertices(grafo_a_modificar);
        int[] gradosQueDeberianSer = {3, 3, 2, 2};

        for (int i = 0; i < gradosVerticesModificar.length; i++) {
            assertEquals(gradosVerticesModificar[i], gradosQueDeberianSer[i]);
        }
    }

    @Test
    void verificarNumeroPermutaciones() {
        int numeroPermutaciones = grafo.numeroDePermutacionesMenosUno(grafo.gradosDeLosVertices(grafoExamenA1));
        assertEquals(1, numeroPermutaciones);
    }

    @Test
    void verificarEliminarElemento() {
        int[] array = {1, 2, 3, 4, 5};
        int[] arrayEsperado = {1, 2, 4, 5};
        int[] arrayResultado = grafo.eliminarElemento(array, 3);
        for (int i = 0; i < arrayEsperado.length; i++) {
            assertEquals(arrayEsperado[i], arrayResultado[i]);
        }
    }

    @Test
    void verificarEliminarElementoArray() {
        int[][] array = {
                {1, 2},
                {2, 1},
                {3, 3},
                {4, 4},
                {5, 5}
        };
        int[][] arrayEsperado = {
                {1, 2},
                {2, 1},
                {4, 4},
                {5, 5}
        };

        int[] aEliminar = {3, 3};

        int[][] arrayResultado = grafo.eliminarElemento(array, aEliminar);
        for (int i = 0; i < arrayEsperado.length; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(arrayEsperado[i][j], arrayResultado[i][j]);
            }
        }
    }


}