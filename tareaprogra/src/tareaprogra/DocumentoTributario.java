
package tareaprogra;
import java.util.Date;
class DocumentoTributario {
    protected String numero;
    protected String rut;
    protected Date fecha;
    
    public DocumentoTributario(String dato, String carnet, Date calen){
        
        numero = dato;
        rut = carnet;
        fecha = calen;
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