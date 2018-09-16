package areafigura;

import becker.robots.*;
/**
 *
 * @author Jhon
 */
public class Areafigura {
    
    public static City ciudad;
    public static Robot robot;
    
    static int maxav;
    static int minav;
    static int maxstreet;
    static int minstreet;
    static int vertical;
    static int lateral;
    static int matriz[][];
    static int area = 0;

    public static void mover(int pasos) {
        for (int i = pasos; i > 0; i--) {
            robot.move();
        }
    }

    public static void girar(int giros) {
        for (int i = giros; i > 0; i--) {
            robot.turnLeft();
        }
    }

    public static void giroDerecha() {
        girar(3);
    }

    public static void giroIzquierda() {
        girar(1);
    }

    public static void adelante() {
        mover(1);
    }
    
    public static void rectangulo() {
        while (robot.canPickThing() == false) {
            if (robot.getAvenue() <= minav) {
                minav = robot.getAvenue();
            }

            if (robot.getAvenue() >= maxav) {
                maxav = robot.getAvenue();
            }

            if (robot.getStreet() <= minstreet) {
                minstreet = robot.getStreet();
            }

            if (robot.getStreet() >= maxstreet) {
                maxstreet = robot.getStreet();
            }

            giroDerecha();
            if (robot.frontIsClear() == true) {
                mover(1);
                continue;
            }
            giroIzquierda();
            if (robot.frontIsClear() == true) {
                mover(1);
                continue;
            }
            
            giroIzquierda();
            if (robot.frontIsClear() == true) {
                mover(1);
                continue;
            }
            
            giroIzquierda();
            mover(1);
        }

    }

    public static int area() {
        matriz = new int[vertical][lateral];
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < lateral; j++) {
                matriz[i][j] = 5;

            }
        }
        
        while (robot.canPickThing() == false) {
            
            matriz[robot.getStreet()- minstreet][robot.getAvenue()- minav]=0;
            Poner1();
            giroDerecha();
            Poner1();
  
            if (robot.frontIsClear() == true) {
                matriz[robot.getStreet()- minstreet][robot.getAvenue()- minav]=0;
                Poner1();
                mover(1);
                continue;
            }
            
            giroIzquierda();
            if (robot.frontIsClear() == true) {
                matriz[robot.getStreet()- minstreet][robot.getAvenue()- minav]=0;
                Poner1();
                mover(1);
                continue;
            }
            
            giroIzquierda();
            if (robot.frontIsClear() == true) {
                matriz[robot.getStreet()- minstreet][robot.getAvenue()- minav]=0;
                Poner1();
                mover(1);
                continue;
            }
            
            matriz[robot.getStreet()- minstreet][robot.getAvenue()- minav]=0;
            Poner1();
            giroIzquierda();
            mover(1);            
        }

        return area;
    }

    public static void Poner1() {
        if (robot.frontIsClear() == false) {
            if (robot.getDirection() == Direction.EAST) {
                matriz[(robot.getStreet() - minstreet)][(robot.getAvenue() - minav) + 1] = 1;
            }
            if (robot.getDirection() == Direction.WEST) {
                matriz[(robot.getStreet() - minstreet)][(robot.getAvenue() - minav) - 1] = 1;
            }
        }
    }

    public static void contar() {
        boolean cuento = false;
        for (int i = 0; i < vertical; i++) {
            int subarea=0;
            for (int j = 0; j < lateral; j++) {
                if (matriz[i][j] == 1 ) {
                    if(cuento){
                        area+=subarea; 
                        subarea=0;
                    }
                    
                    area++;
                    cuento = !cuento;
                }
                
                if (cuento && matriz[i][j] == 5) {
                    subarea++;
                }
                
                if (cuento && matriz[i][j] == 0) {
                    cuento = !cuento;
                }
            }            
        }
    }
    
    public static void main(String[] args) {
        ciudad = new City("Field.txt");
        ciudad.showThingCounts(true);
        robot = new Robot(ciudad, 10, 4, Direction.WEST, 10);
        minstreet = robot.getAvenue();
        minav = robot.getAvenue();
        maxstreet = robot.getStreet();
        maxav = robot.getStreet();
        
        rectangulo();
        
        while (robot.getDirection() != Direction.WEST) {
            girar(1);
        }
        
        mover(1);
        vertical = (maxstreet - minstreet) + 1;
        lateral = (maxav - minav) + 1;
        area();
        contar();
        System.out.println("el area de la figura es de: " + area + " metros cuadrados.");

    } 
}