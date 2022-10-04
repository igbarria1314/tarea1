package tareaprogra;
public class DetalleOrden {
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
