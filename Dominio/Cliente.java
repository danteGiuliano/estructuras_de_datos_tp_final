package Dominio;


public class Cliente implements Comparable{
    String numero_dni,nombre, apellido, tipo_dni, numero_telefono, domicilio, fecha_nacimiento;
    
    
    public Cliente(String numero_dni, String nombre, String apellido, String tipo_dni, String numero_telefono,
                String domicilio, String fecha_nacimiento) {
                
        this.numero_dni          =   numero_dni;
        this.nombre              =   nombre;
        this.apellido            =   apellido;
        this.tipo_dni            =   tipo_dni;
        this.numero_telefono     =   numero_telefono;
        this.domicilio           =   domicilio;
        this.fecha_nacimiento    =   fecha_nacimiento;
    }

    public String get_numero_dni() {
        return numero_dni;
    }

    @Override
    public int hashCode() {
        int x = tipo_dni.charAt(0);
        int y = tipo_dni.charAt(1);
        int z = tipo_dni.charAt(2);

        int constante=Integer.parseInt(numero_dni);
        return ((x*constante)/y)+z*y;
    }
    public int hash(){
        return this.hashCode();

    }

    public void set_numero_dni(String numero_dni) {
        this.numero_dni = numero_dni;
    }

    public String get_nombre() {
        return nombre;
    }

    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }

    public String get_apellido() {
        return apellido;
    }

    public void set_apellido(String apellido) {
        this.apellido = apellido;
    }

    public String get_tipo_dni() {
        return tipo_dni;
    }

    public void set_tipo_dni(String tipo_dni) {
        this.tipo_dni = tipo_dni;
    }

    public String get_numero_telefono() {
        return numero_telefono;
    }

    public void set_numero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String get_domicilio() {
        return domicilio;
    }

    public void set_domicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String get_fecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void set_fecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String  toString(){
        return this.tipo_dni+this.numero_dni;
    }
    
  

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }

}