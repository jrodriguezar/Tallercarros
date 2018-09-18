package almacen;
/**
 *
 * @author Jhon
 */
import java.util.Scanner;

public class Espacio {
    
    private Producto producto;
    private int numero;

    public Espacio(int numero, Producto producto) {
        this.producto = producto;
        this.numero = numero;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public int getNumero() {
        return numero;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
