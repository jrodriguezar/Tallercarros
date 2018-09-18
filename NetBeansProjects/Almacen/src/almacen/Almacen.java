package almacen;
/**
 *
 * @author Jhon
 */
import becker.robots.*;
import java.awt.Color;
import java.util.Scanner;

public class Almacen {
    private City almacen;
    private Robot empleado;
    private Robot[] robot;
    private Scanner teclado;
    private Estante[][] matriz;
    
    public Almacen(){
        teclado = new Scanner(System.in);
        this.almacen = new City();
        for (int  i = 0;  i < 5;  i++) {
            Wall norte = new Wall(almacen, 0, i, Direction.NORTH);
            if(i<4){
            Wall pared = new Wall(almacen, 3, i, Direction.SOUTH);
            }
        }   
        
        int num = 1;
        robot = new Robot[3];
        for (int  i = 0;  i < 7;  i++) {
            Wall norte = new Wall(almacen, i, 0, Direction.WEST);
            if(i<4){
                Wall muro = new Wall(almacen, i, 4, Direction.EAST);
            }
            
            if(i>3){
                Wall paren = new Wall(almacen, i, 0, Direction.SOUTH);
                for (int j = 0; j < 3; j++) {
                Robot reobot = new Robot(almacen, i, 0, Direction.EAST,0);
                reobot.setIcon(new Robotamazon());
                reobot.setLabel("Robot" + " " + num);
                robot[j] = reobot;
                }
                num++;
            }
        }
        matriz = new Estante[5][4];
        int a = 1;
        for (int  i = 0;  i < 5;  i++) {
            for (int  j = 0;  j < 4;  j++) {
                Thing zona3 = new Thing(this.almacen, j, i);
                Producto chip = new Producto("sddfs", 123);
                int k=0;
                Espacio[] espacio = new Espacio[7];
                while(k<7){
                    espacio[k] = new Espacio(1, chip);
                    k++;
                }
                Caja[] caja1 = new Caja[3];
                int n = 0;
                while(n<3){
                    caja1[n] = new Caja(espacio, 1, "tecnologia");
                    n++;
                }
                Estante estante1 = new Estante(caja1, "estante" + a, 1, zona3);
                matriz[i][j] = estante1;
                zona3.getIcon().setColor(Color.GREEN);
                zona3.getIcon().setLabel(estante1.getDescripcion());
                a++;
            }
        }
        
        this.empleado = new Robot(almacen, 5, 8, Direction.WEST,0);
        this.empleado.setIcon(new Trabajador());
        this.empleado.setLabel("Empleado");
    }
    
    public void mover(int pasos) {
        for (int i = pasos; i > 0; i--) {
            empleado.move();
        }
    }

    public void girar(int giros) {
        for (int i = giros; i > 0; i--) {
            empleado.turnLeft();
        }
    }

    public void giroDerecha() {
        girar(3);
    }

    public void giroIzquierda() {
        girar(1);
    }
    
    public boolean buscar_producto(String Producto){
        for (int  i = 0;  i < 5;  i++) {
            for (int  j = 0;  j < 4;  j++) {
                if (matriz[i][j].buscar_producto(Producto)) {
                    System.out.println("El producto ha sido encontrado");
                    return true;
                }
                System.out.println("El producto no se encuentra en el inventario");
            }
        }
        return false;
    }
    
    public boolean ingresar_producto(){
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = teclado.nextLine();
        teclado.nextLine();
        if (buscar_producto(nombre)){
            System.out.println("El producto ya se encuentra en el inventario.");
            return false;
        }else{
            System.out.println("Ingrese el valor del producto: ");
            int valor = teclado.nextInt();
            ///falta acomodar los moviemientos del robot ;v
            
            return true;
        }
    }
    
    public void sacar_estante(){
        //robot.move();
    }
    
}
