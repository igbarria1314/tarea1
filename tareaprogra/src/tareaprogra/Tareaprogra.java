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
        return "Articulo: "+ nombre+"\nDescripcion: "+ descripcion+"\nPrecio:"+precio+"\nPeso:"+peso+"kg\n";
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
        return "Cantidad: "+cantidad+"\n"+Objeto.ToString();
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
    
    public void finDetalles(){
        estado = "En Progreso";
        Pagos = new ArrayList();
        deuda = CalcPrecio();
    }
    public void status(float resta){
        deuda = deuda - resta;
        if(deuda <= 0f){
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
    public void pagoTrans(Transferencia count){
        Pagos.add(new Pago(count.getMonto(),count.getFecha()));
        status(count.getMonto());
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
    
    /*public void prtDoc(boolean result){
        if(result = true){
            ToString();
        }
    }*/
    public String ToString(){
        String completo;
        if(Fac != null && bol == null){
            completo = Fac.ToString();
        }else{
            completo = bol.ToString();
        }
        
        completo = completo + "\n" + usuario.ToString()+ "\n" + dir.ToString() + "/ Estado:" + estado+"\n";
        
        for (int i = 0; i < cantidad; i++) {
            completo = completo + Lista.get(i).ToString();
        }
        completo = completo + "Total:" + total + "\nTotal sin IVA:" + CalcPrecioSinIVA() + "\nIVA:" + Iva()+"\n /Registro de Pagos/\n";
        int derp = Pagos.size();
        for (int i = 0; i < derp ; i++) {
            completo = completo + Pagos.get(i).ToString();
        }
        
        return completo;
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
        return "Monto:"+monto+"\nFecha:"+fecha+"\n";
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
    
    public Efectivo(float p, Date f){
        super(p,f);
    }
    public float calcDevolucion(float p){
        
        if(p<0){
            p = p *-1;
        }
        System.out.println("Devolucion:" + p + "\n");
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
        Articulo papas = new Articulo("Papas Fritas","Papas Fritas Krispo", 750f, 0.03f);
        Articulo Polystation = new Articulo("Polystation","Consola Clasica 100% real",250000,1.5f);
        Articulo Poleron = new Articulo("Poleron", "/ Talla: L / Poleron de Among Us", 60000F,0.25f);
        Articulo Chalas = new Articulo("Chalas", "/ Talla: 40 / Calidad dudosa", 5000,0.1f);
        
        boleta.newCliente("Daniel Montero", "9.116.942-0", "Avenida Los Olmos 247", 2);
        boleta.newOrden(papas, 3);
        boleta.newOrden(yogurth,4);
        boleta.finDetalles();
        String prueba1 = boleta.ToString();
        System.out.println(prueba1);
        Date primerpago= new Date(122,9,1,9,7,30);
        Transferencia cuentarut = new Transferencia("Banco Estado", "9.116.942-0",3810f,primerpago);
        boleta.pagoTrans(cuentarut);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        prueba1 = boleta.ToString();
        System.out.println(prueba1);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        Date fechapol = new Date(122,11,12,15,7,30);
        OrdenCompra factura1 = new OrdenCompra(fechapol);
        factura1.newCliente("El Chelo", "11.432.627 - 7", "Las Torres 1598",1);
        factura1.newOrden(Polystation, 1);
        factura1.finDetalles();
        Date pago1 = new Date(122,11,13,9,30,0);
        Date pago2 = new Date(122,11,20,10,5,0);
        Tarjeta Santander = new Tarjeta("Banco Santander","123456",100000f,pago1);
        Tarjeta Santander2 = new Tarjeta("Banco Santander","123456",150000f,pago2);
        factura1.pagTarjeta(Santander);
        factura1.pagTarjeta(Santander2);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(factura1.ToString());
        Date dia = new Date(123,2,9,18,27,12);
        
        System.out.println();
        System.out.println();
        System.out.println();
        OrdenCompra boleta2 = new OrdenCompra(dia);
        boleta2.newCliente("Daniel Montero", "9.116.942-0", "Avenida Los Olmos 247", 2);
        boleta2.newOrden(Poleron, 1);
        boleta2.newOrden(Chalas,2);
        boleta2.finDetalles();
        Efectivo lucas = new Efectivo(80000,dia);
        boleta2.pagEfectivo(lucas);
        System.out.println( boleta2.ToString());
        
    }
    
}
