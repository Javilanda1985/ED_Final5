package Final5;

public class Menu {
    public static void main(String[] s) {
        MonticuloAdic cp = new MonticuloAdic();
        int [] d = {18, 11, 22, 33, 11, 34, 44, 2, 8, 11};
        System.out.print("Números: ");
        for (int i = 0; i < d.length; i++) {
            System.out.print(" " + d[i]);
            cp.insertar(d[i]);
        }
        cp.imprimir();
        cp.dibujar();
        cp.ordenar();
        cp.imprimir();
        cp.dibujar();
    }
}
