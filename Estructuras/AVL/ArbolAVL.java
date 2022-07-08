package Estructuras.AVL;

public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
    }

    /**
     * El metodo insertar de AVL es de tipo 0(log n)
     * una vez insertado el nodo, se realiza un balance, aplicando
     * rotaciones en caso de ser necesario
     * 
     * @param elemento
     * @return true/false
     */
    public boolean insertar(Comparable elemento) {
        boolean insercion = true;
        if (raiz == null) {
            raiz = new NodoAVL(elemento);
        } else {
            insercion = insertar_aux(this.raiz, elemento);
        }
        return insercion;
    }

    private boolean insertar_aux(NodoAVL raiz, Comparable elemento) {
        boolean insercion = true;
        int comparacion = elemento.compareTo(raiz.get_elemento());

        if (comparacion == 0) {
            insercion = false;
        } else { // Busco en las sub-ramas para insertar
            if (comparacion < 0) {
                if (raiz.get_izquierdo() != null) {
                    insercion = insertar_aux(raiz.get_izquierdo(), elemento);
                } else {
                    raiz.set_izquierdo(new NodoAVL(elemento));
                }
            } else {
                if (raiz.get_derecho() != null) {
                    insercion = insertar_aux(raiz.get_derecho(), elemento);
                } else {
                    raiz.set_derecho(new NodoAVL(elemento));
                }
            }
            if (insercion) { // se verifica que hubo insercion
                this.raiz.recalcular_altura();
                this.balancear_arbol(raiz);
            }
        }
        return insercion;
    }

    /**
     * Balances 
     * 
     *  Padre  2 = Desbalanceado a Izquierda
     *  Padre -2 = Desbalanceado a Derecha
     *  __________________________________________________________________
     *  | Balance padre     | Balance Hijo  | Signo    | Rotacion         |
     *  |-------------------|---------------|----------|------------------|
     *  |       2           |     1 o O     |  Igual   | Simple Derecha   |
     *  |-------------------|---------------|----------|------------------|
     *  |       2           |       -1      | Distinto |  Doble Izq-Der   |
     *  |-------------------|---------------|----------|------------------|
     *  |      -2           |     -1 o 0    |  Igual   | Simple Izquierda |
     *  |-------------------|---------------|----------|------------------|
     *  |      -2           |        1      | Distinto |  Doble Der-Izq   |
     *  |___________________|_______________|__________|__________________|
     * 
     * @param raiz Nodo AVL
     */

    private void balancear_arbol(NodoAVL raiz) {

        int balance = obtener_balance(raiz);

        if (balance < -2) { 
            if (obtener_balance(raiz.get_derecho()) == 1) {
                // Rotacion doble a derecha-izquierda
                rotar_izquierda(raiz.get_derecho());
                rotar_derecha(raiz);
            } else {
                rotar_izquierda(raiz);
            }
        }
        if (balance > 2) { 
            if (obtener_balance(raiz.get_izquierdo()) == -1) {
                // Rotacion doble izquierda - derecha
                rotar_derecha(raiz.get_izquierdo());
                rotar_izquierda(raiz);
            } else {
                rotar_derecha(raiz);
            }

        }
    }

    private void rotar_izquierda(NodoAVL pivote){
        NodoAVL hijo_derecho = pivote.get_derecho();
        NodoAVL temp = hijo_derecho.get_izquierdo();
        hijo_derecho.set_izquierdo(pivote);
        pivote.set_derecho(temp);
    }
    private void rotar_derecha(NodoAVL pivote){
        NodoAVL hijo_izquierdo = pivote.get_izquierdo();
        NodoAVL temp = hijo_izquierdo.get_derecho();
        hijo_izquierdo.set_derecho(pivote);
        pivote.set_izquierdo(temp);
    }

    private int obtener_balance(NodoAVL raiz) {

        NodoAVL derecho = raiz.get_derecho();
        NodoAVL izquierdo = raiz.get_izquierdo();
        int altura_derecha, altura_izquierda;

        altura_izquierda = (izquierdo != null) ? izquierdo.get_altura() : -1;
        altura_derecha = (derecho != null) ? derecho.get_altura() : -1;

        return altura_izquierda - altura_derecha;
    }

}
