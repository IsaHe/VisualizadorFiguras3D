package v3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class VerificarGrafosIsomorfos {


    public int totalDeAristasDelGrafo(int[][] grafo) {

        int total = 0;

        for (int i : gradosDeLosVertices(grafo)) {
            total += i;
        }

        return total/2;
    }

    public boolean tienenElMismoNumeroDeVertices(int[][] grafoBase, int[][] grafo_a_modificar) {
        return grafoBase.length == grafo_a_modificar.length;
    }

    public boolean tienenElMismoNumeroDeAristas(int[][] grafoBase, int[][] grafo_a_modificar) {
        return totalDeAristasDelGrafo(grafoBase) == totalDeAristasDelGrafo(grafo_a_modificar);
    }

    public boolean tienenLosMismosGrados(int[][] grafoBase, int[][] grafo_a_modificar) {

        // Se da por hecho que tienen el mismo número de vertices

        int[] listaGradosBase = gradosDeLosVertices(grafoBase);
        int[] listaGradosModificar = gradosDeLosVertices(grafo_a_modificar);

        for (int grado : listaGradosBase) {

            if (verSiExiste(grado, listaGradosModificar)) {
                listaGradosModificar = eliminarElemento(listaGradosModificar, grado);
            }

        }

        return listaGradosModificar.length == 0;
    }

    public int[] eliminarElemento(int[] listaGrados, int grado) {
        return Arrays.stream(listaGrados)
                .filter(i -> i != grado)
                .toArray();
    }

    public int[][] eliminarElemento(int[][] listaGradosConIndices, int[] idxGrdBucar) {

        int[][] resultado = new int[listaGradosConIndices.length - 1][2];
        int idx = 0;

        for (int[] idxGrd : listaGradosConIndices) {
            if (!Arrays.equals(idxGrd, idxGrdBucar)) {
                resultado[idx] = idxGrd;
                idx++;
            }
        }
        return resultado;
    }

    public boolean verSiExiste(int grado, int[] listaGrados) {

        for (int gradoDeLista : listaGrados) {

            if (gradoDeLista == grado) {
                return true;
            }

        }

        return false;
    }

    public int[] gradosDeLosVertices(int[][] grafo) {

        int temp = 0;
        int[] listaDeGrados = new int[grafo.length];

        for (int i = 0; i < grafo.length; i++) {

            for (int j = 0; j < grafo.length; j++) {

                temp += grafo[i][j];

            }

            listaDeGrados[i] = temp;
            temp = 0;

        }

        return listaDeGrados;
    }

    public boolean verSiSonIsomorfos(int[][] grafoBase, int[][] grafo_a_modificar) {

//        HashMap<Integer, Integer> gradosRepetidos = repeticionesDeGrados(gradosDeLosVertices(grafoBase));

        if(tienenElMismoNumeroDeVertices(grafoBase, grafo_a_modificar)
                && tienenElMismoNumeroDeAristas(grafoBase, grafo_a_modificar)
                && tienenLosMismosGrados(grafoBase, grafo_a_modificar)) {

            grafo_a_modificar = ponerLosGradosEnOrden(grafoBase, grafo_a_modificar);

            if (sonIguales(grafoBase, grafo_a_modificar)) {
                return true;
            } else {
                for(int i = 0; i<numeroDePermutacionesMenosUno(gradosDeLosVertices(grafoBase)); i++) {


                    grafo_a_modificar = permutarFilas_y_Columnas(grafo_a_modificar, 0, 1);


                    if (sonIguales(grafoBase, grafo_a_modificar)) {
                        return true;
                    }
                }
            }

        }

        return false;

    }

    public long factorial(long numero) {

        if (numero == 0) {
            return 1;
        } else if (numero == 1) {
            return 1;
        } else if (numero == 2) {
            return 2;
        }

        return numero * factorial(numero - 1);
    }

    public int numeroDePermutacionesMenosUno(int[] listaDeGrados) {

        int total = 1;

        for (int[] idxGrd : gradosVerticesConIndices(listaDeGrados)) {
            
            total *= factorial(idxGrd[1]);

        }

        return total-1;
    }

    public int[][] ponerLosGradosEnOrden(int[][] grafoBase, int[][] grafo_a_modificar) {

        HashMap<Integer, Integer> posicionesBase = posicionesDeLosGrados(gradosDeLosVertices(grafoBase));
        HashMap<Integer, Integer> posicionesModificar = posicionesDeLosGrados(gradosDeLosVertices(grafo_a_modificar));

        for (int grado : posicionesModificar.keySet()) {

            if (!Objects.equals(posicionesModificar.get(grado), posicionesBase.get(grado))) {

                grafo_a_modificar = permutarFilas_y_Columnas(grafo_a_modificar, posicionesModificar.get(grado), posicionesBase.get(grado));
                posicionesModificar.put(grado, posicionesBase.get(grado));

            }

        }

        return grafo_a_modificar;

    }

    public HashMap<Integer, Integer> posicionesDeLosGrados(int[] listaGrados) {

            HashMap<Integer, Integer> posiciones = new HashMap<>();

            for (int i = 0; i < listaGrados.length; i++) {

                posiciones.put(listaGrados[i], i);

            }

            return posiciones;
    }

    public boolean sonIguales(int[][] grafoBase, int[][] grafo_a_modificar) {

        for (int i = 0; i < grafoBase.length; i++) {

            for (int j = 0; j < grafoBase.length; j++) {

                if (grafoBase[i][j] != grafo_a_modificar[i][j]) {
                    return false;
                }

            }

        }

        return true;
    }

    public int[][] gradosVerticesConIndices(int[] listaDeGrados) {

        int[][] gradosConIndices = new int[listaDeGrados.length][2];

        for (int i = 0; i < listaDeGrados.length; i++) {

            gradosConIndices[i][0] = listaDeGrados[i];
            gradosConIndices[i][1] = i;

        }

        return gradosConIndices;

    }

    public int[][] permutarFilas_y_Columnas(int[][] grafo, int indice1, int indice2) {

        grafo = permutar(grafo, indice1, indice2);
        grafo = invertirGrafo(grafo);

        grafo = permutar(grafo, indice1, indice2);
        grafo = invertirGrafo(grafo);

        return grafo;
    }

    public int[][] invertirGrafo(int[][] grafo) {

        int tamanyoGrafo = grafo.length;
        int[][] grafoInvertido = new int[tamanyoGrafo][tamanyoGrafo];
        int[] temp = new int[tamanyoGrafo];

        for (int j = 0; j < tamanyoGrafo; j++) {

            for (int i = 0; i < tamanyoGrafo; i++) {
                temp[i] = grafo[i][j];
            }

            grafoInvertido[j] = temp;
            temp = new int[tamanyoGrafo];

        }

        return grafoInvertido;
    }

    public int[][] permutar(int[][] grafo, int indice1, int indice2) {

        int[] hold = grafo[indice1];
        grafo[indice1] = grafo[indice2];
        grafo[indice2] = hold;

        return grafo;
    }

}
