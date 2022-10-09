package tareaprogra;
import java.util.Date;
public class Cliente{
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
