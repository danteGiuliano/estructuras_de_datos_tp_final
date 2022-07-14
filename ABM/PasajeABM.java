package ABM;

import java.util.Map;
import java.util.Scanner;

import Estructuras.Lista.Lista;
import ORM.ORM;

public class PasajeABM {
    //"El hash map se define para los pasajes la clave son los clientes cada"

    //HashMap claveCliente , Lista pasajes
    static Scanner edat = new Scanner(System.in);
    static String formato_menu = "%20s %60s";

    static Map<String,Lista> map = ORM.get_pasajes();
    public static void main(String[] args) {
        System.out.println(map.get("DNI40807582").toString());
        menu();
    }

    public static void menu() {

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("|                                 Pasaje Menu                                        |");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Comprar pasaje");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Modificar pasaje");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "Cancelar Pasaje");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "Ver pasajes Cliente");
            System.out.println("");
            System.out.format(formato_menu, "5 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (opcion(edat.nextLine()));
    }

    public static boolean opcion(String opcion) {
        boolean sesion = true;
        int opcion_numerica = 0;
        try {
            opcion_numerica = Integer.parseInt(opcion);
            switch (opcion_numerica) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    sesion = false;
                    break;
                default:
                    System.out.println("Error de opcion ingrese una opcion valida");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("");
        }
        return sesion;
    }
    public static void comprar_pasaje(){
        
    }

}
