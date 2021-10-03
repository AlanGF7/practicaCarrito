package datos;

public class Articulos {

    private int id;
    private String nombre;
    private int cantidad;

    //Vacio 
    public Articulos(){}
    
    //Agregar producto
    public Articulos(int id, String nombre, int cantidad){
        this.id = id;
        this.nombre = nombre; 
        this.cantidad = cantidad;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
    
}
