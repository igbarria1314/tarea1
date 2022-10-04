package tareaprogra;

import java.util.Date;
public class DocumentoTributario {
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
