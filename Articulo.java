
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
public class Pago{
    private float monto;
    private Date fecha;
    public Pago(float m, Date f){
        monto=m;
        fecha=f;
    }
}
public class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    public Tarjeta(String t,String numT){
        tipo=t;
        numTransaccion= numT;
    }
}
public class Transferencia extends Pago{
    private String banco;
    private String numCuenta;
    public Transferencia(String bank,String nc){
        banco=bank;
        numCuenta=nc;
    }  
}
public class Efectivo extends Pago{
    private float pagoDelCliente;
    public Efectivo(float p){
        pagoDelCliente= p;
    }
    public float calcDevolucion(){
        float pagoTotal=monto-PagoDelCliente;
        return pagoTotal;
    }
}

