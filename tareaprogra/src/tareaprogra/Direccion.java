package tareaprogra;

import java.util.Date;
public class Direccion{
    private String direccion;
    public Direccion(String d){
        direccion=d;
    }
    public getDireccion(){
        return direccion;
    }
    public String ToString(){
        return "Direccion:" + direccion;
    }
}
