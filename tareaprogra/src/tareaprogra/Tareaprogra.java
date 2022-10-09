package tareaprogra;

import java.util.ArrayList;
import java.util.Date;

public class Tareaprogra{

    public static void main(String[] args) {
        
        Date fecha = new Date(122,9,1,4,7,15);
        OrdenCompra boleta = new OrdenCompra(fecha);
        Articulo yogurth= new Articulo("yogurth","yogurth frutilla",390f,0.02f);
        Articulo papas = new Articulo("Papas Fritas","Papas Fritas Krispo", 750f, 0.03f);
        Articulo Polystation = new Articulo("Polystation","Consola Clasica 100% real",250000,1.5f);
        Articulo Poleron = new Articulo("Poleron", "/ Talla: L / Poleron de Among Us", 60000F,0.25f);
        Articulo Chalas = new Articulo("Chalas", "/ Talla: 40 / Calidad dudosa", 5000,0.1f);
        Cliente daniel= new Cliente("Daniel Montero", "9.116.942-0");
        Direccion dirDaniel= new Direccion("Avenida Los Olmos 247");
        boleta.newCliente(daniel, dirDaniel, 2);
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
        Cliente chelo= new Cliente("El Chelo", "11.432.627 - 7");
        Direccion dirChelo= new Direccion("Las Torres 1598");
        factura1.newCliente(chelo,dirChelo ,1);
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
        boleta2.newCliente(daniel,dirDaniel, 2);
        boleta2.newOrden(Poleron, 1);
        boleta2.newOrden(Chalas,2);
        boleta2.finDetalles();
        Efectivo lucas = new Efectivo(80000,dia);
        boleta2.pagEfectivo(lucas);
        System.out.println( boleta2.ToString());
    }
    
}
