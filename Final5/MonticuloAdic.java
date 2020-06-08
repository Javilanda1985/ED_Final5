package Final5;

public class MonticuloAdic {
    static final int TAMINI = 20;
    private int numElem;
    private int[] v;

    public MonticuloAdic() {
        numElem = 0;
        v = new int[TAMINI];
    }

    public static int padre(int i) {
        return (i - 1) / 2;
    }

    public static int hijoIzq(int i) {
        return (2 * i + 1);
    }

    public static int hijoDer(int i) {
        return (2 * i + 1) + 1;
    }

    public void insertar(int clave) {
        if (monticuloLleno()) {
            ampliar();
        }
        v[numElem] = clave;
        flotar(numElem);
        numElem++;
    }

    private boolean monticuloLleno() {
        return (numElem == v.length);
    }

    private void ampliar() {
        int[] anteriorV = v;
        v = new int[numElem + TAMINI];
        for (int j = 0; j < numElem; j++)
            v[j] = anteriorV[j];
    }

    private void flotar(int i) {
        int nuevaClave = v[i];
        while ((i > 0) && (v[padre(i)] > nuevaClave)) {
            v[i] = v[padre(i)]; // baja el padre al hueco
            i = padre(i); // sube un nivel en el árbol
        }
        v[i] = nuevaClave; // sitúa la clave en su posición
    }

    public int buscarMinimo() throws Exception {
        if (esVacio())
            throw new Exception("Acceso a montículo vacío");
        return v[0];
    }

    public boolean esVacio() {
        return numElem == 0;
    }

    public int eliminarMinimo() throws Exception {
        if (esVacio())
            throw new Exception("Acceso a montículo vacío");
        int menor;
        menor = v[0];
        v[0] = v[numElem - 1];
        criba(0);
        numElem--;
        return menor;
    }

    public void criba(int raiz) {
        boolean esMonticulo;
        int hijo;
        esMonticulo = false;
        while ((raiz < numElem / 2) && !esMonticulo) {
            // determina el índice del hijo menor
            if (hijoIzq(raiz) == (numElem - 1)) // único descendiente
                hijo = hijoIzq(raiz);
            else {
                if (v[hijoIzq(raiz)] < v[hijoDer(raiz)])
                    hijo = hijoIzq(raiz);
                else
                    hijo = hijoDer(raiz);
            }
            // compara raiz con el menor de los hijos
            if (v[hijo] < v[raiz]) {
                int t = v[raiz];
                v[raiz] = v[hijo];
                v[hijo] = t;
                raiz = hijo; /* continua por la rama de claves mínimas */
            } else
                esMonticulo = true;
        }
    }

    public static void criba2(int v[], int raiz, int ultimo) {
        boolean esMonticulo;
        int hijo;
        int numElem = ultimo + 1;
        esMonticulo = false;
        while ((raiz < numElem / 2) && !esMonticulo) {
            // determina el índice del hijo mayor
            if (MonticuloBinario.Monticulo.hijoIzq(raiz) == (numElem - 1))
                hijo = MonticuloBinario.Monticulo.hijoIzq(raiz);
            else {
                if (v[MonticuloBinario.Monticulo.hijoIzq(raiz)] > v[MonticuloBinario.Monticulo.hijoDer(raiz)])
                    hijo = MonticuloBinario.Monticulo.hijoIzq(raiz);
                else
                    hijo = Monticulo.hijoDer(raiz);
            }
            // compara raiz con el mayor de los hijos
            if (v[hijo] > v[raiz]) {
                int t = v[raiz];
                v[raiz] = v[hijo];
                v[hijo] = t;
                raiz = hijo; /* continua por la rama de claves máximas */
            } else
                esMonticulo = true;
        }
    }

    public static void ordenacionMonticulo(int v[], int n) {
        int j;
        for (j = n / 2; j >= 0; j--)
            criba2(v, j, n - 1);
        for (j = n - 1; j >= 1; j--) {
            int t;
            t = v[0];
            v[0] = v[j];
            v[j] = t;
            criba2(v, 0, j - 1);
        }
    }

    public void imprimir() {
        System.out.print("\nMontículo: ");
        for (int i=0; i<numElem; i++) {
            System.out.print(v[i] + " ");
        }
    }

    public void ordenar() {
        ordenacionMonticulo( v, numElem);
    }

    public void dibujar(String prefijo, boolean esCola, int n) {
        if (n < numElem) {
            System.out.println(prefijo + (esCola ? "└── " : "├── ") + v[n]);
            dibujar(prefijo + (esCola ? "    " : "│   "), false, hijoIzq(n));
            dibujar(prefijo + (esCola ? "    " : "│   "), true, hijoDer(n));
        }
    }

    public void dibujar() {
        System.out.println();
        dibujar("", true, 0);
    }
}
