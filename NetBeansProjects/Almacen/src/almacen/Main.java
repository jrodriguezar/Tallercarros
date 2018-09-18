package almacen;
/**
 *
 * @author Jhon
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Almacen almacen = new Almacen();
        int u=0;
        int op = 0;
        almacen.sacar_estante();
        Scanner teclado = new Scanner(System.in);      
        while(op != 3){
            System.out.println("Escoja una opción:  ");
            System.out.println("1:Ingresar un nuevo producto");
            System.out.println("2: Realizar pedido");
            System.out.println("5:terminar la op");
            op = teclado.nextInt();
            switch(op){
            }
        }
        
    }
}
