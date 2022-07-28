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
        NodoAVL nuevaRaiz;
        if (raiz == null) {
            raiz = new NodoAVL(elemento);
        } else {
            nuevaRaiz = insertar_aux(this.raiz, elemento);
            if (nuevaRaiz != null) {
                this.raiz = nuevaRaiz;
                insercion = true;
            }
        }
        return insercion;
    }

    private NodoAVL insertar_aux(NodoAVL raiz, Comparable elemento) {
        NodoAVL insercion = null;
        int comparacion = elemento.compareTo(raiz.get_elemento());

        if (comparacion != 0) { // Busco en las sub-ramas para insertar
            if (comparacion < 0) {
                if (raiz.get_izquierdo() != null) {
                    insercion = insertar_aux(raiz.get_izquierdo(), elemento);
                    if (raiz.get_izquierdo() != insercion) { // Significa que hubo rotacion y mi sub-arbol izquierdo es
                                                             // distinto
                        raiz.set_izquierdo(insercion);
                        insercion = raiz;
                    }
                } else {
                    raiz.set_izquierdo(new NodoAVL(elemento));
                }
            } else {
                if (raiz.get_derecho() != null) {
                    insercion = insertar_aux(raiz.get_derecho(), elemento);
                    if (raiz.get_derecho() != insercion) { // Significa que hubo rotacion y mi sub-arbol derecho es
                                                           // distinto
                        raiz.set_derecho(insercion);
                        insercion = raiz;
                    }
                } else {
                    raiz.set_derecho(new NodoAVL(elemento));
                }
            }
            raiz.recalcular_altura();
            insercion = this.balancear_arbol(raiz);
        }
        return insercion;
    }

    /**
     * Balances
     * 
     * Padre 2 = Desbalanceado a Izquierda
     * Padre -2 = Desbalanceado a Derecha
     * __________________________________________________________________
     * | Balance padre | Balance Hijo | Signo | Rotacion |
     * |-------------------|---------------|----------|------------------|
     * | 2 | 1 o O | Igual | Simple Derecha |
     * |-------------------|---------------|----------|------------------|
     * | 2 | -1 | Distinto | Doble Izq-Der |
     * |-------------------|---------------|----------|------------------|
     * | -2 | -1 o 0 | Igual | Simple Izquierda |
     * |-------------------|---------------|----------|------------------|
     * | -2 | 1 | Distinto | Doble Der-Izq |
     * |___________________|_______________|__________|__________________|
     * 
     * @param raiz Nodo AVL
     */

    private NodoAVL balancear_arbol(NodoAVL raiz) {
        int balance = obtener_balance(raiz);
        if (balance == -2) {
            if (obtener_balance(raiz.get_derecho()) == 1) {
                // Rotacion doble a derecha-izquierda
                NodoAVL aux = rotar_derecha(raiz.get_derecho());
                raiz.set_derecho(aux);
                raiz = rotar_izquierda(raiz);
            } else {
                raiz = rotar_izquierda(raiz);
            }
        } else {

        }
        // Aplicar un else. /// Me genera rotaciones extra
        if (balance == 2) {

            if (obtener_balance(raiz.get_izquierdo()) == -1) {
                // Rotacion doble izquierda - derecha
                NodoAVL aux = rotar_izquierda(raiz.get_izquierdo());
                raiz.set_izquierdo(aux);
                raiz = rotar_derecha(raiz);
            } else {
                raiz = rotar_derecha(raiz);
            }
        }
        raiz.recalcular_altura();
        return raiz;
    }

    private int obtener_balance(NodoAVL raiz) {

        NodoAVL derecho = raiz.get_derecho();
        NodoAVL izquierdo = raiz.get_izquierdo();
        int altura_derecha, altura_izquierda;

        altura_izquierda = (izquierdo != null) ? izquierdo.get_altura() : -1;
        altura_derecha = (derecho != null) ? derecho.get_altura() : -1;

        return altura_izquierda - altura_derecha;
    }

    private NodoAVL rotar_izquierda(NodoAVL pivote) {

        NodoAVL hijo_derecho = pivote.get_derecho();

        NodoAVL temp = null;
        if (hijo_derecho != null) {
            temp = hijo_derecho.get_izquierdo();
        }
        hijo_derecho.set_izquierdo(pivote);
        pivote.set_derecho(temp);
        hijo_derecho.recalcular_altura();
        pivote.recalcular_altura();
        return hijo_derecho;
    }

    private NodoAVL rotar_derecha(NodoAVL pivote) {
        NodoAVL hijo_izquierdo = pivote.get_izquierdo();
        NodoAVL temp = null;
        if (hijo_izquierdo != null) {
            temp = hijo_izquierdo.get_derecho();
        }
        hijo_izquierdo.set_derecho(pivote);
        pivote.set_izquierdo(temp);
        hijo_izquierdo.recalcular_altura();
        pivote.recalcular_altura();
        return hijo_izquierdo;
    }

    /**
     * Eliminar
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
            // Esto es para no recorrer todo el arbol O(log n).
            if (temp < 0) {
                borrado = auxEliminar(elemento, actual.get_izquierdo(), actual);
            } else if (temp > 0) {
                borrado = auxEliminar(elemento, actual.get_derecho(), actual);
            } else {
                // Encontro al nodo.
                if (actual.get_izquierdo() == null && actual.get_derecho() == null) {
                    caso1(elemento, padre);
                } else {
                    if (actual.get_izquierdo() == null || actual.get_derecho() == null) {
                        caso2(elemento, actual, padre);
                    } else {
                        caso3(actual, actual.get_izquierdo(), actual.get_izquierdo());
                    }
                }
                actual.recalcular_altura();
                balancear_arbol(actual);
                borrado = true;
            }
        }
        return borrado;
    }

    // Caso 1 de eliminar.
    private void caso1(Comparable elemento, NodoAVL padre) {
        if (padre == null) {
            // Caso especial al intentar eliminar la raiz
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

    // Caso 2 de eliminar.
    private void caso2(Comparable elem, NodoAVL hijo, NodoAVL padre) {
        // Busco al candidato para reemplazar al nodo
        // almenos 1 sera null.
        NodoAVL der = hijo.get_derecho();
        NodoAVL izq = hijo.get_izquierdo();
        if (padre == null) {
            // Caso especial.
            if (der == null) {
                this.raiz = izq;
            } else {
                this.raiz = der;
            }
        } else {
            // Verifico la rama derecha o izquierda.
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

    private void caso3(NodoAVL raiz, NodoAVL padre, NodoAVL hijo) {
        NodoAVL candidato_A = hijo.get_derecho();

        if (candidato_A != null) {
            caso3(raiz, hijo, candidato_A);
        } else {
            raiz.set_elemento(hijo.get_elemento());
            NodoAVL hijo_derecho = candidato_A;
            if (raiz.get_izquierdo() == hijo) {
                raiz.set_izquierdo(hijo_derecho);
            } else {
                padre.set_izquierdo(hijo_derecho);
            }
        }
        raiz.recalcular_altura();
        balancear_arbol(raiz);

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
            r = aux.get_elemento();
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
            r = aux.get_elemento();
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
                    cadena += "[ HI: " + nodo.get_izquierdo().get_elemento().toString() + " ]";
                } else {
                    cadena += "[ HI: null ]";
                }
                if (nodo.get_derecho() != null) {
                    cadena += "[ HD: " + nodo.get_derecho().get_elemento().toString() + " ]" + "\n";
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
            hijo = new NodoAVL(aux.get_elemento(), clonarAux(aux.get_izquierdo()), clonarAux(aux.get_derecho()));
        }
        return hijo;
    }

    public boolean pertenece(Comparable elemento) {
        boolean flag = false;
        if (this.raiz != null && elemento != null) {
            flag = perteneceAux(this.raiz, elemento);
        }
        return flag;
    }

    private boolean perteneceAux(NodoAVL node, Comparable var) {
        boolean flag = false;
        if (node != null) {
            if (node.get_elemento().compareTo(var) == 0) {
                flag = true;
            } else {
                if (node.get_elemento().compareTo(var) < 0) {
                    flag = perteneceAux(node.get_derecho(), var);
                } else {
                    flag = perteneceAux(node.get_izquierdo(), var);
                }
            }
        }
        return flag;
    }

    public Comparable extraer_elemento(Object referencia) {
        Comparable elemento = null;
        if (this.raiz != null && referencia != null) {
            elemento = extraer_elemento_aux(this.raiz, referencia);
        }
        return elemento;
    }

    private Comparable extraer_elemento_aux(NodoAVL node, Object referencia) {
        Comparable elemento = null;
        if (node != null) {
            if (node.get_elemento().compareTo(referencia) == 0) {
                elemento = node.get_elemento();
            } else {
                if (node.get_elemento().compareTo(referencia) < 0) {
                    elemento = extraer_elemento_aux(node.get_derecho(), referencia);
                } else {
                    elemento = extraer_elemento_aux(node.get_izquierdo(), referencia);
                }
            }
        }
        return elemento;
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
        // Caso especial.
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
