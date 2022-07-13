package Estructuras.AVL;

public class Test {
    static ArbolAVL avl = new ArbolAVL();
    static ArbolAVL avl3 = new ArbolAVL();
    static ArbolAVL avl2 = new ArbolAVL();

    public static void main(String[] args) {
        rotacionesDobles2();
    }

    private static void rotacionesDerechas(){
        avl.insertar(1);
        avl.insertar(2);
        avl.insertar(3);
        avl.insertar(4);
        avl.insertar(5);
        avl.insertar(6);
        avl.insertar(7);
        avl.insertar(8);
        avl.insertar(9);
        avl.insertar(10);
        avl.insertar(11);
        avl.insertar(12);

        System.out.println(avl.toString());
        avl.eliminar(1);
        avl.eliminar(2);
        avl.eliminar(8);    
        avl.eliminar(9);    
        avl.eliminar(10);    
        System.out.println(avl.toString());

    }

    private static void rotacionesIzquierdas() {
        avl3.insertar(40);
        avl3.insertar(39);
        avl3.insertar(38);
        System.out.println(avl3.toString());

    }

    private static void rotacionesDobles() {
        avl2.insertar(40);
        avl2.insertar(60);
        avl2.insertar(59);
        System.out.println(avl2.toString());
    }
    private static void rotacionesDobles2() {
        avl2.insertar(40);
        avl2.insertar(20);
        avl2.insertar(25);
        System.out.println(avl2.toString());
    }
}
