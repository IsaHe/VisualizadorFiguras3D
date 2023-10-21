import java.util.Arrays;

public class Linea {
    
    private int[] punto1;
    private int[] punto2;

    private int[] punto1_Proyectado;
    private int[] punto2_Proyectado;
    private final int[][] linea = new int[2][2];

    public Linea(int[] punto1, int[] punto2) {
        this.punto1 = punto1;
        this.punto2 = punto2;
    }

    public int[] getPunto1() {
        return punto1;
    }

    public void setPunto1(int[] punto1) {
        this.punto1 = punto1;
    }


    public int[] getPunto2() {
        return punto2;
    }

    public void setPunto2(int[] punto2) {
        this.punto2 = punto2;
    }

    public void setPunto1_Proyectado(int[] punto1_Proyectado) {
        this.punto1_Proyectado = punto1_Proyectado;
    }

    public void setPunto2_Proyectado(int[] punto2_Proyectado) {
        this.punto2_Proyectado = punto2_Proyectado;
    }

    public int[][] getLinea() {
        return linea;
    }

    public void setLinea() {

        linea[0] = punto1_Proyectado;
        linea[1] = punto2_Proyectado;

    }

    public void proyeccionEnPlano(int distanciaFocal) {

        int[] a ={Math.floorDiv(distanciaFocal * punto1[0] , distanciaFocal + punto1[2]),
                  Math.floorDiv(distanciaFocal * punto1[1] , distanciaFocal + punto1[2])};

        int[] b ={Math.floorDiv(distanciaFocal * punto2[0] , distanciaFocal + punto2[2]),
                  Math.floorDiv(distanciaFocal * punto2[1] , distanciaFocal + punto2[2])};

        setPunto1_Proyectado(a);
        setPunto2_Proyectado(b);
        setLinea();

    }

    public static Linea[] crearCubo(int size) {
        Linea[] cubo = new Linea[12];

        int[][] tablaDeVertices = {
                {size, size, size},
                {size, -size, size},
                {-size, -size, size},
                {-size, size, size},
                {size, size, -size},
                {size, -size, -size},
                {-size, -size, -size},
                {-size, size, -size},
        };

        cubo[0] = new Linea(tablaDeVertices[0], tablaDeVertices[1]);
        cubo[1] = new Linea(tablaDeVertices[1], tablaDeVertices[2]);
        cubo[2] = new Linea(tablaDeVertices[2], tablaDeVertices[3]);
        cubo[3] = new Linea(tablaDeVertices[3], tablaDeVertices[0]);
        cubo[4] = new Linea(tablaDeVertices[4], tablaDeVertices[5]);
        cubo[5] = new Linea(tablaDeVertices[5], tablaDeVertices[6]);
        cubo[6] = new Linea(tablaDeVertices[6], tablaDeVertices[7]);
        cubo[7] = new Linea(tablaDeVertices[7], tablaDeVertices[4]);
        cubo[8] = new Linea(tablaDeVertices[0], tablaDeVertices[4]);
        cubo[9] = new Linea(tablaDeVertices[1], tablaDeVertices[5]);
        cubo[10] = new Linea(tablaDeVertices[2], tablaDeVertices[6]);
        cubo[11] = new Linea(tablaDeVertices[3], tablaDeVertices[7]);

        return cubo;
    }
    public static Linea[] crearTriangulo(int size, int h) {
        Linea[] triangulo = new Linea[8];

        int[][] tablaDeVertices = {
                {0, -h, 0},
                {size, 0, size},
                {size, 0, -size},
                {-size, 0, -size},
                {-size, 0, size}
        };
        triangulo[0] = new Linea(tablaDeVertices[1], tablaDeVertices[2]);
        triangulo[1] = new Linea(tablaDeVertices[2], tablaDeVertices[3]);
        triangulo[2] = new Linea(tablaDeVertices[3], tablaDeVertices[4]);
        triangulo[3] = new Linea(tablaDeVertices[4], tablaDeVertices[1]);
        triangulo[4] = new Linea(tablaDeVertices[1], tablaDeVertices[0]);
        triangulo[5] = new Linea(tablaDeVertices[2], tablaDeVertices[0]);
        triangulo[6] = new Linea(tablaDeVertices[3], tablaDeVertices[0]);
        triangulo[7] = new Linea(tablaDeVertices[4], tablaDeVertices[0]);

        return triangulo;
    }

    @Override
    public String toString() {
        return "\nLinea => Punto 1: " + Arrays.toString(punto1) + " Punto 2: " + Arrays.toString(punto2);
    }
}