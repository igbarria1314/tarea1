package tareaprogra;

import java.util.Date;
public class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    public Tarjeta(String t,String numT,float m, Date f){
        super(m,f);
        tipo=t;
        numTransaccion= numT;
    }
}
