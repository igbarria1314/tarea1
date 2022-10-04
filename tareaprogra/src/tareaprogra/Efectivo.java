package tareaprogra;

import java.util.Date;
public class Efectivo extends Pago{
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
