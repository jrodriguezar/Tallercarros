package almacen;
/**
 *
 * @author Jhon
 */
import java.util.Scanner;

public class Producto {
    
    private double valor;
    private String despcripcion;
    
    public Producto(String descripcion, int valor){
        this.valor = valor;
        this.despcripcion = descripcion;
    }
    
    public double getValor() {
        return valor;
    }

    public String getDespcripcion() {
        return despcripcion;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDespcripcion(String despcripcion) {
        this.despcripcion = despcripcion;
    }
    
}
