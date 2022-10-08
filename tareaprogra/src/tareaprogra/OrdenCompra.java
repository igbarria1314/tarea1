
package tareaprogra;

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
    public void newCliente(Cliente client, Direccion direc,int eleccion){
        usuario= client;
        dir= direc;
        elecDoc(dir.getDireccion,usuario.getRut,fecha,eleccion);
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
