package estacionamiento;
/**
 *
 * @author Jhon
 */
import becker.robots.*;
import java.awt.Color;
import java.util.Scanner;

public class Escenario {

    private City ciudad;
    private Robot conductor;
    private Scanner teclado;
    private Thing[][] matriz;
    private Thing[] espera;
    private int zonas;
    private long[][] tiempos;
    private long[] tiemposE;
    private double ingresos_totales;
    
    public Escenario() {
        teclado = new Scanner(System.in);
        this.ciudad = new City();
        for (int i = 0; i <= 5; i++) {
            Wall lateral = new Wall(ciudad, i, 0, Direction.WEST);
            if (i < 5) {
                Wall lateralb = new Wall(ciudad, i, 0, Direction.EAST);
                Wall lateral1 = new Wall(ciudad, i, 1, Direction.EAST);
                Wall lateral1b = new Wall(ciudad, i, 1, Direction.WEST);
                Wall lateral2 = new Wall(ciudad, i, 2, Direction.WEST);
                Wall lateral2b = new Wall(ciudad, i, 2, Direction.EAST);
            }
        }
        for (int i = 0; i < 3; i++) {
            Wall arriba = new Wall(ciudad, 0, i, Direction.NORTH);
        }
        //camino y zona espera
        for (int i = 0; i < 9; i++) {
            Wall arriba = new Wall(ciudad, 5, i, Direction.SOUTH);
            if (i <= 7 && i > 2) {
                Wall arriba1 = new Wall(ciudad, 4, i, Direction.NORTH);
                Wall espacio = new Wall(ciudad, 4, i, Direction.EAST);
            }
        }
        Wall tope_inicio = new Wall(ciudad, 5, 8, Direction.EAST);
        //identificadores de zona
        Thing zona1 = new Thing(this.ciudad, -1, 0);
        zona1.getIcon().setColor(Color.WHITE);
        zona1.getIcon().setLabel("1");

        Thing zona2 = new Thing(this.ciudad, -1, 1);
        zona2.getIcon().setColor(Color.WHITE);
        zona2.getIcon().setLabel("2");

        Thing zona3 = new Thing(this.ciudad, -1, 2);
        zona3.getIcon().setColor(Color.WHITE);
        zona3.getIcon().setLabel("3");
        //entrada
        Thing entrada = new Thing(this.ciudad, 4, 8);
        entrada.getIcon().setColor(Color.CYAN);
        entrada.getIcon().setLabel("Entrada");

        this.conductor = new Robot(ciudad, 5, 8, Direction.WEST,0);
        this.conductor.setIcon(new carrico());
        this.conductor.setLabel("Conductor");
        matriz = new Thing[5][3];
        tiempos = new long[5][3];
        espera = new Thing[4];
        tiemposE = new long[4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("y:" + i + " " + "x:" + j);
                matriz[j][i] = null;
            }
        }
    }   

    public void mover(int pasos) {
        for (int i = pasos; i > 0; i--) {
            conductor.move();
        }
    }

    public void girar(int giros) {
        for (int i = giros; i > 0; i--) {
            conductor.turnLeft();
        }
    }

    public void giroDerecha() {
        girar(3);
    }

    public void giroIzquierda() {
        girar(1);
    }

    public void delante() {
        mover(1);
    }

    public int zona_libre() {
        int zona = 6;
        int mas_espacio = -1;
        int espacios[] = {0, 0, 0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (matriz[j][i] == null) {
                    espacios[i]++;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (espacios[i] >= mas_espacio) {
                mas_espacio = espacios[i];
            }
        }
        if(mas_espacio==0){
            return zona;
        }
        for (int i = 0; i < 3; i++) {
            if (espacios[i] == mas_espacio) {
                zona = i;
                return zona;
            }
        }
            return zona;
    }

    public int espacio_libre(int zona) {
        for (int i = 0; i < 5; i++) {
            if (matriz[i][zona] == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean buscar_placa(String placa) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (matriz[j][i] != null) {
                    if (matriz[j][i].getIcon().getLabel().equals(placa)) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    public int buscar_placa_zona(int zona, String placa) {
        for (int i = 0; i < 5; i++) {
            if (matriz[i][zona] != null && matriz[i][zona].getIcon().getLabel().equals(placa)) {
                return i;
            }

        }
        return -1;
    }

    public double factura(double tiempo_segundos) {
        double valor_pesos;
        valor_pesos = tiempo_segundos * 15;        
        return valor_pesos;
    }
    
    public boolean ingresar_vehiculo() {
        int zona = zona_libre();
        if(zona == 6){
            System.out.println("No hay espacion disponible.");
            return false;
        }
        System.out.println("ingrese placa del vehiculo (letra y numero): ");
        String placa = teclado.nextLine();
        System.out.println(placa);
        if (buscar_placa(placa) == true) {
            System.out.println("ya hay un vehiculo registrado con esa placa");
            return false;
        }else{
            Thing auto = new Thing(ciudad, 5, 8);
            auto.getIcon().setColor(Color.GRAY);
            auto.getIcon().setLabel(placa);
            conductor.pickThing();
        
            int espacio = espacio_libre(zona);
            System.out.println("ZONA:  " + zona);
            while (conductor.getAvenue() != zona) {
                conductor.move();
            }
            giroDerecha();
            while (conductor.getStreet() != espacio) {
                conductor.move();
            }
            conductor.putThing();
            girar(2);
            while (conductor.frontIsClear() == true) {
                conductor.move();
            }
            girar(1);
            while (conductor.frontIsClear() == true) {
                conductor.move();
            }
            girar(2);
            matriz[espacio][zona] = auto;
            tiempos[espacio][zona] = System.currentTimeMillis();
            return true;
        }
    }
    
    public boolean sacar_vehiculo() {
        int zona, espacio;
        String placa;
        double valor_a_pagar;
        System.out.println("ingrese la zona de parqueo de su vehiculo: ");
        zona = teclado.nextInt();
        System.out.println("ingrese placa del vehiculo (letra y numero): ");
        placa = teclado.next();
        espacio = buscar_placa_zona(zona, placa);

        if (buscar_placa(placa) == false) {
            System.out.println("el vehiculo no se encuentra en el parqueadero");
            return false;
        }
        
        if (espacio == -1) {
            System.out.println("el vehiculo no se encuentra en esa zona de parqueo");
            return false;
        }

        if (espacio == 4) {
            while (conductor.getAvenue() != zona) {
                conductor.move();
            }
            
            giroDerecha();
            while (conductor.getStreet() != espacio) {
                conductor.move();
            }

            conductor.pickThing();
            girar(2);
            while (conductor.frontIsClear()) {
                conductor.move();
            }
            
            girar(1);
            while (conductor.frontIsClear()) {
                conductor.move();
            }
            
            girar(2);

            matriz[espacio][zona] = null;
            tiempos[espacio][zona] = (System.currentTimeMillis() - tiempos[espacio][zona]) / 1000;
            valor_a_pagar = factura(tiempos[espacio][zona]);
            System.out.println("el valor a pagar es:" + valor_a_pagar );
            ingresos_totales+= valor_a_pagar;
            tiempos[espacio][zona] = 0;
            System.out.println("¡Gracias por su visita!");
            return true;
        }

        while (conductor.getAvenue() != zona) {
            conductor.move();
        }
        
        giroDerecha();
        mover(1);
        int contador_y = 4;
        int contador_espera = 0;
        boolean flag = true;
        while (flag) {
            if (!conductor.canPickThing()) {
                mover(1);
                contador_y--;
                System.out.println("No encontre nada que coger");
            } else {
                System.out.println("Placa: " + matriz[contador_y][zona].getIcon().getLabel());
                System.out.println("Placa buscada: " + placa);
                if (matriz[contador_y][zona].getIcon().getLabel().equals(placa)) {
                    System.out.println("Encontrado");
                    flag = false;
                    conductor.pickThing();
                    girar(2);
                    while(conductor.frontIsClear()){
                        mover(1);
                    }
                    System.out.println("gira a la derecha estupido");
                    girar(1);
                    while(conductor.frontIsClear()){
                        mover(1);
                    }
                    girar(2);
                    matriz[contador_y][zona] = null;
                    tiempos[contador_y][zona] = (System.currentTimeMillis() - tiempos[espacio][zona]) / 1000;
                    valor_a_pagar = factura(tiempos[espacio][zona]);
                    System.out.println("el valor a pagar es:" + valor_a_pagar );
                    ingresos_totales+= valor_a_pagar;
                    tiempos[contador_y][zona] = 0;
                    System.out.println("¡Gracias por su visita!");
                    System.out.println("ingresos hasta ahora" + ingresos_totales );
                    mover(5);
                    giroDerecha();
                    while(contador_espera>0){
                        mover(1);
                        conductor.pickThing();
                        girar(2);
                        mover(1);
                        girar(3);
                        int lugar = espacio_libre(zona);
                        System.out.println(lugar);
                        int in_espera = 0;
                        int movimiento = 0;
                        while (conductor.getAvenue() != zona) {
                            conductor.move();
                            movimiento++;
                        }
                        giroDerecha();
                        while (conductor.getStreet() != lugar) {
                            conductor.move();
                        }
                        matriz[conductor.getStreet()][conductor.getAvenue()] = espera[in_espera];
                        tiempos[conductor.getStreet()][conductor.getAvenue()] = tiemposE[in_espera];
                        espera[in_espera] = null;
                        tiemposE[in_espera] = 0;
                        conductor.putThing();
                        girar(2);
                        while (conductor.frontIsClear() == true) {
                            conductor.move();
                        }
                        girar(1);
                        mover(movimiento+1);
                        girar(1);
                        movimiento = 0;
                        contador_espera--;
                        in_espera++;
                    }
                    giroDerecha();
                    while (conductor.frontIsClear() == true) {
                        conductor.move();
                    }
                    girar(1);
                    mover(4);
                    conductor.putThing();
                    girar(2);
                    mover(4);
                    giroDerecha();
                    
                } else {
                    conductor.pickThing();
                    espera[contador_espera] = matriz[conductor.getStreet()][conductor.getAvenue()];
                    tiemposE [contador_espera] = tiempos[conductor.getStreet()][conductor.getAvenue()];
                    matriz[conductor.getStreet()][conductor.getAvenue()] = null;
                    tiempos[conductor.getStreet()][conductor.getAvenue()] = 0;
                    girar(2);
                    mover(5 - contador_y);
                    girar(1);
                    mover((3 - zona) + contador_espera);
                    girar(1);
                    mover(1);
                    conductor.putThing();                    
                    girar(2);
                    mover(1);
                    giroDerecha();
                    mover((3 - zona) + contador_espera);
                    contador_espera++;
                    giroDerecha();
                    contador_y--;
                    mover(4 - contador_y + 1);
                }
            }
        }
        return true;
    }
    
    public String mostrar_autos(){
        System.out.println("Ingrese la seccion que desea conocer: ");
        int seccion = teclado.nextInt();
        System.out.println("Losvehiculos en esta seccion son: ");
            for (int  j = 0;  j < 5;  j++) {
                if(matriz[j][seccion] !=null){
                    System.out.println(matriz[j][seccion].getIcon().getLabel());;
                }else{
                    System.out.println("No hay vehiculos en este espacio");
                }
            }
        return null;
    }
    
    public String mostrar_ing(){
        System.out.println("Los ingresos totales son: " + ingresos_totales + " dolares.");
        return null;
    }
}