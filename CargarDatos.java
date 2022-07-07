
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CargarDatos {

    /**
     *  
     * Algoritmo extra para leer/cargar/guardar informacion en el JSON
     * 
     */



    static String ruta = System.getProperty("user.dir") + "/data.json";
    static JSONParser parser = new JSONParser();
    static JSONObject data;
    static JSONArray vuelos;
    static JSONArray aeropuertos;
    static JSONArray clientes;
    static JSONArray pasajes;

    static Scanner edat = new Scanner(System.in);

    private static void leer_json() throws IOException, org.json.simple.parser.ParseException {

        data         =        (JSONObject) parser.parse(new FileReader(ruta));
        vuelos       =        (JSONArray) data.get("Vuelos");
        aeropuertos  =        (JSONArray) data.get("Aeropuertos");
        clientes     =        (JSONArray) data.get("Clientes");
        pasajes      =        (JSONArray) data.get("Pasajes");

    }

    public static void main(String[] args) {

        System.out.println("Algoritmo para cargar informacion de data.json");
        cargar_info();
       // mostrar_consola(aeropuertos);
       // mostrar_consola(clientes);
        
    }

    public static void cargar_info() {
        try {
            leer_json();
            cargar_cliente();
            cargar_Aeropuerto();
        } 
        catch (FileNotFoundException f) {}
        catch (IOException e) {System.err.println("Archivo no existe");} 
        catch (org.json.simple.parser.ParseException e) {e.printStackTrace();}

    }
   
    public static void mostrar_consola(JSONArray data){
        data.forEach(e->{
            JSONObject elemento = (JSONObject)e;
            System.out.println(elemento);
        });
    }

    public static void cargar_vuelo(){

        String empresas []={"Aerolineas Argentinas","Jet Smart" ,"Fly Bondi","Air Canada"};
        String codigoEmpresas[]={"AA","JS","FB","AC"};
        
        int opcion_elegida;
        String codigo_elegido;

        System.out.println("Seleccione una empresa disponible");
        for (int i = 0; i < empresas.length; i++) {
            System.out.println(i+"-"+empresas[i]);
        }
        opcion_elegida=edat.nextInt();
        if(opcion_elegida>=0 && opcion_elegida <empresas.length){
            codigo_elegido=codigoEmpresas[opcion_elegida];
        }   
       
     
        
        

    }

    public static void cargar_cliente(){

        String nombre,numero_documento,tipo_dni,apellido,domicilio,telefono,fecha_nacimiento;


        System.out.println("ingrese un nombre");
        nombre=edat.next();
        System.out.println("ingrese un apellido");
        apellido=edat.next();
        System.out.println("Ingrese un numero de documento");
        numero_documento=edat.next();
        System.out.println("Ingrese un tipo de documento");
        tipo_dni=edat.next();
        System.out.println("ingrese un domicilio");
        domicilio=edat.next();
        System.out.println("ingrese un telefono");
        telefono=edat.next();
        System.out.println("ingrese una fecha dd/mm/aaaa");
        fecha_nacimiento=edat.next();

        System.out.println("Su informacion es: \n"+
                            " Nombre: "+nombre+"\n Apellido: "+apellido+
                            "\n Tipo DNI: "+tipo_dni+"\n Nro DNI: "+numero_documento+
                            "\n Domicilio: "+domicilio+"\n Telefono: "+telefono);

        System.out.println("Desea aceptarla? true/false");
        if(edat.nextBoolean()){
            JSONObject cliente = new JSONObject();
            cliente.put("nombre", nombre);
            cliente.put("apellido",apellido);
            cliente.put("tipo_dni",tipo_dni);
            cliente.put("numero_dni",numero_documento);
            cliente.put("domicilio",domicilio);
            cliente.put("numero_telefono", telefono);
            cliente.put("fecha_nacimiento",fecha_nacimiento);

            clientes.add(cliente);
            guardar_info();

        }else{
            System.out.println("Informacion rechazada");
        }

    }

    public static void menu_carga(){
        System.out.println("ingrese alguna opcion de carga");

    }

    public static void cargar_Aeropuerto(){
        String nombre,codigo,telefono;

        System.out.println("ingrese el nombre de aeropuerto");
        nombre=edat.nextLine();
        System.out.println("Ingrese su codigo unico de 3 letras");
        codigo=edat.nextLine();
        System.out.println("Ingrese su numero telefonico EJ: 299-473459");
        telefono=edat.nextLine();
        
        System.out.println("Su informacion es: \n Nombre: "+nombre+"\n Codigo: "+codigo+"\n telefono: "+telefono);
        System.out.println("Desea aceptarla? true/false");

        if(edat.nextBoolean()){
            JSONObject aeropuerto = new JSONObject();

            aeropuerto.put("codigo", codigo);
            aeropuerto.put("nombre", nombre);
            aeropuerto.put("telefono", telefono);

            aeropuertos.add(aeropuerto);
            guardar_info();
           
        }else{
            System.out.println("Informacion rechazada");
        }

    }


    private static void guardar_info(){
        try{
            FileWriter fr= new FileWriter(ruta);
            fr.write(data.toJSONString());
            fr.flush();
            fr.close();
        }
        catch (FileNotFoundException f) {}
        catch (IOException e) {System.err.println("Archivo no existe");}
    }
}