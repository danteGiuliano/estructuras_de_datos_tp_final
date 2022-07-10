package Estructuras.AVL;

import Estructuras.Lista.Lista;

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

    private int obtener_balance(NodoAVL raiz) {

        NodoAVL derecho = raiz.get_derecho();
        NodoAVL izquierdo = raiz.get_izquierdo();
        int altura_derecha, altura_izquierda;

        altura_izquierda = (izquierdo != null) ? izquierdo.get_altura() : -1;
        altura_derecha = (derecho != null) ? derecho.get_altura() : -1;

        return altura_izquierda - altura_derecha;
    }


    private void rotar_izquierda(NodoAVL pivote) {
        NodoAVL hijo_derecho = pivote.get_derecho();
        NodoAVL temp = hijo_derecho.get_izquierdo();
        hijo_derecho.set_izquierdo(pivote);
        pivote.set_derecho(temp);

        hijo_derecho.recalcular_altura();
        pivote.recalcular_altura();
    }

    private void rotar_derecha(NodoAVL pivote) {
        NodoAVL hijo_izquierdo = pivote.get_izquierdo();
        NodoAVL temp = hijo_izquierdo.get_derecho();
        hijo_izquierdo.set_derecho(pivote);
        pivote.set_izquierdo(temp);

        hijo_izquierdo.recalcular_altura();
        pivote.recalcular_altura();
    }

    /**
     *                             Eliminar 
     * 
     */
    public boolean eliminar(Comparable elem) {
        boolean borrado = false;
        if (this.raiz != null) {
            borrado = auxEliminar(elem, this.raiz, null);
        }
        return borrado;
    }

    private boolean auxEliminar(Comparable elemento, NodoAVL actual, NodoAVL padre) {
        boolean borrado = false;
        if (actual != null) {
            int temp = elemento.compareTo(actual.get_elemento());
            //Esto es para no recorrer todo el arbol O(log n).
            if (temp < 0) {
                borrado = auxEliminar(elemento, actual.get_izquierdo(), actual);
            } else if (temp > 0) {
                borrado = auxEliminar(elemento, actual.get_derecho(), actual);
            } else {
                //Encontro al nodo.     
                if (actual.get_izquierdo() == null && actual.get_derecho() == null) {
                    caso1(elemento, padre);
                } else {
                    if (actual.get_izquierdo() == null || actual.get_derecho() == null) {
                        caso2(elemento, actual, padre);
                    } else {
                        caso3(actual);
                    }
                }
                actual.recalcular_altura();
                balancear_arbol(actual);
                borrado = true;
            }
        }
        return borrado;
    }

    //Caso 1 de eliminar.
    private void caso1(Comparable elemento, NodoAVL padre) {
        if (padre == null) {
            //Caso especial al intentar eliminar la raiz
            this.raiz = null;
        } else {
            int temp = elemento.compareTo(padre.get_elemento());
            if (temp < 0) {
                padre.set_izquierdo(null);
            } else {
                padre.set_derecho(null);
            }
        }

    }

    //Caso 2 de eliminar.
    private void caso2(Comparable elem, NodoAVL hijo, NodoAVL padre) {
        //Busco al candidato para reemplazar al nodo
        //almenos 1 sera null.
        NodoAVL der = hijo.get_derecho();
        NodoAVL izq = hijo.get_izquierdo();
        if (padre == null) {
            //Caso especial.
            if (der == null) {
                this.raiz = izq;
            } else {
                this.raiz = der;
            }
        } else {
            //Verifico la rama derecha o izquierda.
            int temp = elem.compareTo(padre.get_elemento());
            if (temp < 0) {
                if (izq == null) {
                    padre.set_izquierdo(der);
                } else {
                    padre.set_izquierdo(izq);
                }
            } else {
                if (izq == null) {
                    padre.set_derecho(der);
                } else {
                    padre.set_derecho(izq);
                }
            }
        }

    }

    /**
     * Usar el candidato A (El mayor del subarbol izquierdo de N, siendo N el
     * nodo a eliminar).
     *
     * @param actual envia el nodo a eliminar.
     */
    private void caso3(NodoAVL actual) {
        NodoAVL nodoA = actual.get_izquierdo(), nodoPadreA = actual;
        while (nodoA.get_derecho() != null) {
            nodoPadreA = nodoA;
            nodoA = nodoA.get_derecho();
        }
        actual.set_elemento(nodoA.get_elemento());
        NodoAVL hijoDer = nodoA.get_derecho();
        if (actual.get_izquierdo() == nodoA) {
            actual.set_izquierdo(hijoDer);
        } else {
            nodoPadreA.set_izquierdo(hijoDer);
        }
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Comparable minimoElemento() {
        Comparable r = null;
        if (this.raiz != null) {
            NodoAVL aux = this.raiz;
            while (aux.get_izquierdo() != null) {
                aux = aux.get_izquierdo();
            }
            r=aux.get_elemento();
        }
        return r;
    }
    public Comparable maximoElemento() {
        Comparable r = null;
        if (this.raiz != null) {
            NodoAVL aux = this.raiz;
            while (aux.get_derecho() != null) {
                aux = aux.get_derecho();
            }
            r=aux.get_elemento();
        }
        return r;
    }
    public String toString() {
        String cadena;
        cadena = imprimir(this.raiz);
        return cadena;
    }

    private String imprimir(NodoAVL nodo) {
        String cadena = "";
        if (nodo != null) {
            if (nodo.get_derecho() != null || nodo.get_izquierdo() != null) {
                cadena += "Nodo: " + nodo.get_elemento();
                if (nodo.get_izquierdo() != null) {
                    cadena += "[ HI: " + nodo.get_izquierdo().get_elemento() + " ]";
                } else {
                    cadena += "[ HI: null ]";
                }
                if (nodo.get_derecho() != null) {
                    cadena += "[ HD: " + nodo.get_derecho().get_elemento() + " ]" + "\n";
                } else {
                    cadena += "[ HD: null ] \n";
                }
                cadena += imprimir(nodo.get_izquierdo());
                cadena += imprimir(nodo.get_derecho());
            } else {
                cadena += "[ Hoja: " + nodo.get_elemento() + "]" + "\n";
            }
        }
        return cadena;
    }

    /**
     * Reutilizo el clone de Arbol Binario Orden O(n)
     */
    public ArbolAVL clone() {
        ArbolAVL nuevo = new ArbolAVL();
        nuevo.raiz = clonarAux(this.raiz);
        return nuevo;
    }

    private NodoAVL clonarAux(NodoAVL aux) {
        NodoAVL hijo = null;
        if (aux != null) {
            hijo = new NodoAVL(aux.get_elemento(),clonarAux(aux.get_izquierdo()),clonarAux(aux.get_derecho()));
        }
        return hijo;
    }

    /**
     * El metodo listar accede a la ultima posicion de la rama izquierda del
     * arbol para poder obtener el minimo elemento y listarlo en inorden
     * izquierdo-Padre-Derecho.
     *
     * Orden O(n)
     *
     * @return una lista con los minimos elementos en inorden
     */
    public Lista listar() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoAVL node, Lista lista) {
        if (node != null) {
            listarAux(node.get_derecho(), lista);
            lista.insertar(node.get_elemento(), 1);
            listarAux(node.get_izquierdo(), lista);
        }
    }

    /**
     * Devuelve una lista tomando desde el menor elemento (Si lo encuentra)
     * hasta el mayor elemento (Si lo encuetra)
     *
     * @param min un elemento perteneciente al Arbol
     * @param max un elemento perteneciente al Arbol
     * @return una lista vacia o de elementos de [Menor,Mayor]
     */
    public Lista listarRango(Comparable min, Comparable max) {
        Lista lista = new Lista();
        //Caso especial.
        if (min != null && max != null) {
            listarRangoAux(this.raiz, lista, min, max);
        }
        return lista;
    }

    private void listarRangoAux(NodoAVL node, Lista lista, Comparable min, Comparable max) {
        if (node != null) {
            Comparable elemento = node.get_elemento();
            int izq = elemento.compareTo(max);
            int der = elemento.compareTo(min);
            if (izq < 0) {
                listarRangoAux(node.get_derecho(), lista, min, max);
            }
            if (der >= 0 && izq <= 0) {
                lista.insertar(elemento, 1);
            }
            if (der > 0) {
                listarRangoAux(node.get_izquierdo(), lista, min, max);
            }
        }
    }



}
