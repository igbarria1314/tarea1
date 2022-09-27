
package tarea1;

public class Articulo {
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;
    
    public Articulo(float kg, String alias, String que, float caro){
        peso = kg;
        nombre = alias;
        descripcion = que;
        precio = caro;
    }
    
    public float getPrecio(){
        return precio;
    }
    public float getPeso(){
        return peso;
    }
            
}
