
package tareaprogra;

import java.util.ArrayList;
import java.util.Date;
class Articulo {
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;
    public Articulo(String alias, String que, float caro, float kg){
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
    public String ToString(){
        return "Articulo: "+ nombre+"\nDescripcion: "+ descripcion+"\nPrecio:"+precio+"\nPeso:"+peso+"kg";
    }
}
class DetalleOrden {
    private int cantidad;
    private Articulo Objeto;
    public DetalleOrden(Articulo cosa, int valor){
        cantidad = valor;
        Objeto = cosa;
    }
    public float CalcPrecio(){
        return Objeto.getPrecio()*cantidad;
    }
    public float Iva(){
        float iva;
        iva=(float) (CalcPrecio()*0.19);
        return iva;
    }
    public float CalcPrecioSinIVA(){
        return CalcPrecio()-Iva();
    }
    public float CalcPeso(){
        
        return Objeto.getPeso() * cantidad;
    }
    public String ToString(){
        return "Cantidad: "+cantidad+"\nArticulo: "+Objeto.ToString();
    }
}
class OrdenCompra{
    private Date fecha;
    private String estado;
    private ArrayList<DetalleOrden> Lista;
    private ArrayList<Pago> Pagos;
    private int cantidad;
    private Cliente usuario;
    private Direccion dir;
    private Boleta bol;
    private Factura Fac;
    private float total;
    private float deuda;
    
    public OrdenCompra(Date fecha){
        Lista = new ArrayList();
        cantidad = 0;
        this.fecha = fecha; 
        estado = "Ingresando Articulos";
        total = 0;
        Pagos = null;
    }
    public void newOrden(Articulo nuevo, int cantidad){
        if(estado == "Ingresando Articulos"){
            Lista.add(new DetalleOrden(nuevo, cantidad));
            this.cantidad++;
            
        }
    }
    public void elecDoc(String dato, String carnet, Date calen, int eleccion){
        switch(eleccion){
            case 1:
                Fac = new Factura(dato,carnet,calen);
                bol = null;
                
            case 2:
                bol = new Boleta(dato,carnet,calen);
                Fac = null;
        }
    }
    public void newCliente(String nom, String r, String direc,int eleccion){
        usuario= new Cliente(nom,r);
        dir= new Direccion(direc);
        elecDoc(direc,r,fecha,eleccion);
    }
    public void Pagotrans(Transferencia count){
        Pagos.add(new Pago(count.getMonto(),count.getFecha()));
        status(count.getMonto());
    }
    public void finDetalles(){
        estado = "En Progreso";
        Pagos = new ArrayList();
        deuda = CalcPrecio();
    }
    public void status(float resta){
        deuda = deuda - resta;
        if(total <= 0){
            estado = "Completado";
            
        }
        
    }
    public void pagEfectivo(Efectivo billetes){
        Pagos.add(new Pago(billetes.getMonto(),billetes.getFecha()));
        float comprobar = deuda-billetes.getMonto();
        if(comprobar <= 0){
            billetes.calcDevolucion(comprobar);
        }
        status(billetes.getMonto());
        
    }
    public void pagTarjeta(Tarjeta tarjet){
        Pagos.add(new Pago(tarjet.getMonto(),tarjet.getFecha()));
        status(tarjet.getMonto());
    }
    public float CalcPrecio(){
        float result = 0;
        for (int i = 0; i < cantidad; i++) {
            result = result +Lista.get(i).CalcPrecio();
        }
        total = result;
        return result;
    }
    public float Iva(){
        float iva = CalcPrecio()*0.19f;
        return iva;
    }
    public float CalcPrecioSinIVA(){
        return CalcPrecio()-Iva();
    }
    
    public void prtDoc(boolean result){
        if(result = true){
            ToString();
        }
    }
    public void ToString(){
        if(Fac != null && bol == null){
            System.out.println(Fac.ToString());
        }else{
            System.out.println(bol.ToString());
        }
        
        System.out.println(usuario.ToString()+ "\n" + dir.ToString() + "/ Estado:" + estado);
        
        for (int i = 0; i < cantidad; i++) {
            System.out.println(Lista.get(i).ToString());
        }
        
        System.out.println("Total:" + total + "\nTotal sin IVA:" + CalcPrecioSinIVA() + "\nIVA:" + Iva()+"\n /Registro de Pagos/");
        for (int i = 0; i < Pagos.size() ; i++) {
            System.out.println(Pagos.get(i).ToString());
        }
        
        
    }
}
class Pago{
    private float monto;
    private Date fecha;
    public Pago(float m, Date f){
        monto=m;
        fecha=f;
    }
    public float getMonto(){
        return monto;
    }
    public Date getFecha(){
        return fecha;
    }
    public String ToString(){
        return "Monto:"+monto+"\nFecha:"+fecha;
    }
}
class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    public Tarjeta(String t,String numT,float m, Date f){
        super(m,f);
        tipo=t;
        numTransaccion= numT;
    }
}
class Transferencia extends Pago{
    private String banco;
    private String numCuenta;
    public Transferencia(String bank,String nc,float m, Date f){
        super(m,f);
        banco=bank;
        numCuenta=nc;
    }  
}
class Efectivo extends Pago{
    private float pagoDelCliente;
    public Efectivo(float p,float m, Date f){
        super(m,f);
        pagoDelCliente= p;
    }
    public float calcDevolucion(float p){
        
        if(p<0){
            p = p *-1;
        }
        return p;
    }
}
class Cliente{
    private String nombre;
    private String rut;
    public Cliente(String nom,String r){
        nombre=nom;
        rut=r;
    }
    public String getRut(){
        return rut;
    }
    public String ToString(){
        return "/Nombre del Cliente:" + nombre +"/ RUT:"+ rut+"/";
    }
}
class Direccion{
    private String direccion;
    public Direccion(String d){
        direccion=d;
    }
    public String ToString(){
        return "Direccion:" + direccion;
    }
}
class DocumentoTributario {
    private String numero;
    private String rut;
    private Date fecha;
    private float pagos;
    public DocumentoTributario(String dato, String carnet, Date calen){   
        numero = dato;
        rut = carnet;
        fecha = calen;
    }
    public String getNum(){
        return numero;
    }
    public String getRut(){
        return rut;
    }
    public Date getFecha(){
        return fecha;
    }
    public String ToString(){
     return "Numero:"+numero+"\nRut:"+rut+"\nFecha:"+fecha;
    }
}
class Boleta extends DocumentoTributario{
    public Boleta(String dato, String carnet, Date calen){
        super(dato,carnet,calen);
    }   
}
class Factura extends DocumentoTributario{
    public Factura(String dato, String carnet, Date calen){   
        super(dato,carnet,calen);
    }
}
public class Tareaprogra{

    public static void main(String[] args) {
        
        Date fecha = new Date(122,9,1,4,7,15);
        OrdenCompra boleta = new OrdenCompra(fecha);
        
        Articulo yogurth= new Articulo("yogurth","yogurth frutilla",390f,0.02f);
        Articulo papas = new Articulo("Papas Fritas","Papas Fritas Krispo", 750, 0.03f);
        
        boleta.newCliente("Nekotina", "9.116.942-0", "Tus muertos a caballo 247", 2);
        boleta.newOrden(papas, 3);
        boleta.newOrden(yogurth,4);
        boleta.finDetalles();
        boleta.ToString();
        Date primerpago= new Date(122,6,1,9,7,30);
        Transferencia cuentarut = new Transferencia("Banco Estado", "9.116.942-0",3810f,primerpago);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        boleta.ToString();
        
        /*String prueba=alDetalle.ToString();
        System.out.println(prueba+"\n");
        Date fecha= new Date(120,5,3,10,5,6);
        DocumentoTributario documento= new DocumentoTributario("23","213333000",fecha);
        String doc=documento.ToString();
        System.out.println(doc+"\n");
        Pago precio= new Pago(400,fecha);
        String test=precio.ToString();
        System.out.println(test+"\n");*/
    }
    
}
