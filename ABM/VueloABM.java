package ABM;

import java.util.Scanner;

public class VueloABM{

    static String formato_menu = "%20s %60s";
    static Scanner edat = new Scanner(System.in);

     public static void main(String[] args) {
        menu();
     }



    public static void menu() {

        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("|                                 Vuelo Menu                                        |");
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Cargar Vuelo");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Seleccionar Vuelo");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "volver atras");
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
                seleccionar_vuelo();
                    break;
                case 3:
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


    private static void seleccionar_vuelo() {
       // Vuelo vuelo = validar_vuelo();
        do {
            System.out.println("_____________________________________________________________________________________");
            System.out
                    .println("                  Cliente " );
            System.out.println("_____________________________________________________________________________________");
            System.out.printf(formato_menu, "N°", "OPCION");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.format(formato_menu, "1 |", "Modificar Estado pasaje");
            System.out.println("");
            System.out.format(formato_menu, "2 |", "Ver informacion pasaje");
            System.out.println("");
            System.out.format(formato_menu, "3 |", "Eliminar pasaje");
            System.out.println("");
            System.out.format(formato_menu, "4 |", "volver atras");
            System.out.println("");
            System.out.println("____________________________________________________________________________________");
            System.out.print("Opcion ->");
        } while (true);

    }
}