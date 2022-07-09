package Estructuras.Lista;

public class Lista {

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object elemen, int ind) {
        boolean flag = true;
        int r = 1;
        if (ind < 1 || ind > this.longitud + 1) {
            flag = false;
        } else {
            if (this.cabecera == null) {
                this.cabecera = new Nodo(elemen, null);
            }
            if (ind == 1) {
                this.cabecera = new Nodo(elemen, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < ind - 1) {
                    aux = aux.get_enlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elemen, aux.get_enlace());
                aux.set_enlace(nuevo);
            }
            this.longitud++;
        }
        return flag;
    }
    // Longitud devuelve la posicion actual en el caso base longitud es 0.

    public int longitud() {
        return this.longitud;
    }

    public boolean eliminar(int indice) {
        boolean flag = true;
        if (indice > 0 && indice <= this.longitud) {
            int aux = 2;// suponiendo la lista tiene al menos un elemento
            Nodo r = this.cabecera;
            if (indice == 1) {
                this.cabecera = r.get_enlace();
            } else {
                while (aux <= indice - 1) {
                    r = r.get_enlace();
                    aux++;
                }
                if (aux + 1 == indice) {
                    r.set_enlace(null);
                } else {
                    r.set_enlace((r.get_enlace()).get_enlace());
                }
            }
            this.longitud--;

        } else {
            flag = false;
        }
        return flag;
    }

    public boolean esVacia() {
        return this.longitud == 0;
    }

    public Object recuperar(int indice) {
        int r = 1;// Supongo que la lista ya tiene un elemento
        Object obtener = null;
        if (indice > 0 && indice <= this.longitud) {
            Nodo aux = this.cabecera;
            while (indice != r) {
                aux = aux.get_enlace();
                r++;
            }
            obtener = aux.get_elemento();
        }
        return obtener;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public int localizar(Object elem) {
        int pos = 1;// Supongo que la lista tiene 1 elemento.
        Nodo aux = this.cabecera;
        while (aux != null) {
            if (aux.get_elemento().equals(elem)) {
                aux = null;
            } else {
                aux = aux.get_enlace();
                pos++;
            }
        }
        return pos;
    }

    public String toString() {
        String cad = "Lista vacia";
        Nodo aux = this.cabecera;
        int r = 1, i = this.longitud;
        if (this.longitud > 0) {
            cad = "[";
            while (r <= i && aux != null) {
                cad += aux.get_elemento().toString() + ",";
                aux = aux.get_enlace();
                r++;
            }
            cad = cad.substring(0, cad.length() - 1);
            cad += "]";
        }
        return cad;
    }

    public Lista clone() {
        Lista aux = new Lista();
        aux = cloneAux(aux, this.cabecera);
        aux.longitud = this.longitud;
        return aux;
    }

    private Lista cloneAux(Lista aux, Nodo ins) {
        if (ins != null) {
            aux = cloneAux(aux, ins.get_enlace());
            Nodo r = new Nodo(ins.get_elemento(), aux.cabecera);
            aux.cabecera = r;
        }
        return aux;
    }
}
