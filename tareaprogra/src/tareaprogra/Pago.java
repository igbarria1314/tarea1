package tareaprogra;

import java.util.Date;

public class Pago {
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
