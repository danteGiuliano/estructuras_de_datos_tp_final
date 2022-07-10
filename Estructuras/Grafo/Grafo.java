package Estructuras.Grafo;

public class Grafo {

    private NodoVertice inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertar_vertice(Object elemento) {
        boolean insercion = false;
        NodoVertice repetido = buscar_nodo(elemento);
        if (repetido == null) {
            this.inicio = new NodoVertice(elemento, this.inicio);
            insercion = true;
        }
        return insercion;
    }

    /**
     * Busca si existe el Nodo Vertice en mi grafo
     * 
     * @param elemento
     * @return null o nodo vertice
     */

    private NodoVertice buscar_nodo(Object elemento) {
        NodoVertice nodo_encontrado = this.inicio;

        while (nodo_encontrado != null && !nodo_encontrado.equals(elemento)) {
            nodo_encontrado = nodo_encontrado.get_siguiente_vertice();
        }
        return nodo_encontrado;
    }

    public boolean insertar_arco(Object vertice_x, Object vertice_y, Object etiqueta) {
        boolean insercion = true;

        NodoVertice nodo_x = buscar_nodo(vertice_x);
        NodoVertice nodo_y = buscar_nodo(vertice_y);

        if (nodo_x != null && nodo_y != null) {
            NodoAdyacente ultimo_x = ultimo_adyacente(nodo_x);
            NodoAdyacente ultimo_y = ultimo_adyacente(nodo_y);

            NodoAdyacente referencia_x = new NodoAdyacente(nodo_x, etiqueta);
            NodoAdyacente referencia_y = new NodoAdyacente(nodo_y, etiqueta);

            if (ultimo_x != null) {
                ultimo_x.set_nodo_adyacente(referencia_y);
            } else {
                nodo_x.set_primer_nodo(referencia_y);
            }
            if (ultimo_y != null) {
                ultimo_y.set_nodo_adyacente(referencia_x);
            } else {
                nodo_y.set_primer_nodo(referencia_x);
            }

        }
        return insercion;

    }

    /**
     * 
     * Busca el ultimo nodo adyacente de un nodo vertice Orden O(n)
     * 
     * @param nodo
     * @return null o ultimo nodo adyacente
     */

    private NodoAdyacente ultimo_adyacente(NodoVertice nodo) {

        NodoAdyacente ultimo = nodo.get_primer_nodo();
        boolean es_ultimo = false;
        while (ultimo != null && !es_ultimo) {
            if (ultimo.get_nodo_adyacente() == null) {
                es_ultimo = true;
            } else {
                ultimo = ultimo.get_nodo_adyacente();
            }
        }
        return ultimo;
    }



    /**
     * Metodo de debug
     * 
     * Orden O(n²)
     * 
     * 
     */

    public String toString() {
        String salida = "";

        if (this.inicio != null) {

            salida += "[ Vertices ] : [Etiqueta,Vertice] \n";

            NodoVertice nodo_vertice = this.inicio;

            while (nodo_vertice != null) {
                salida += "[ " + nodo_vertice.get_elememento() + " ] :";

                NodoAdyacente nodo_adyacente = nodo_vertice.get_primer_nodo();
                while (nodo_adyacente != null) {
                    salida += "[ " + nodo_adyacente.get_etiqueta().toString() + " , "
                            + nodo_adyacente.get_vertice().toString() + " ] ";
                    nodo_adyacente = nodo_adyacente.get_nodo_adyacente();
                }
                salida += "\n";
                nodo_vertice = nodo_vertice.get_siguiente_vertice();
            }

        }

        return salida;
    }

}