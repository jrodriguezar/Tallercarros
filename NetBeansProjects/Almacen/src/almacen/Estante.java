package almacen;
/**
 *
 * @author Jhon
 */
import becker.robots.*;
import java.util.Scanner;

public class Estante {
    
    private Caja[] caja;
    private int numero;
    private String descripcion;
    private Thing thing;

    public Estante(Caja[] caja, String descripcion, int numero, Thing thing) {
        this.caja = caja;
        this.descripcion = descripcion;
        this.numero = numero;
        this.thing = thing;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public Caja[] getCaja() {
        return caja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCaja(Caja[] caja) {
        this.caja = caja;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }
    
    public boolean buscar_producto(String Producto){
         for (int  i = 0;  i < 3;  i++) {
             if(caja[i].buscar_producto(Producto)){
                 return true;
             }
         }
         return false;
     }
}
