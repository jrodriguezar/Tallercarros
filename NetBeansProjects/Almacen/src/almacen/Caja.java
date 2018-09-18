package almacen;
/**
 *
 * @author Jhon
 */
import java.util.Scanner;

public class Caja {
    
    private Espacio[] espacio;
    private int numero;
    private String descripcion;

    public Caja(Espacio[] espacio, int numero, String descripcion) {
        this.espacio = espacio;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Espacio[] getEspacio() {
        return espacio;
    }

    public int getNumero() {
        return numero;
    }

    public void setEspacio(Espacio[] espacio) {
        this.espacio = espacio;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean buscar_producto(String producto){ 
        for (int  i = 0;  i < 7;  i++) {
            if(producto == espacio[i].getProducto().getDespcripcion()){
                System.out.println("Encontrado!!!");
                return true;
            }
        }
        return false;
    }
}
