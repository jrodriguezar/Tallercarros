package persona;
/**
 *
 * @author Jhon
 */
public class Main {
    public static void main(String[] args){
        Rueda[] llantas = new Rueda[4];
        for (int i = 0; i < 4; i++) {
            llantas[i] = new Rueda("Michelin", "GT", 18);
        }
        Rueda[] ruedas = new Rueda[4];
        for (int i = 0; i < 4; i++) {
            ruedas[i] = new Rueda("Mercedez", "AGV", 17);
        }
        Chasis chasis = new Chasis("Monocasco");
        Chasis chasis1 = new Chasis("Independiente");
        Motor motor = new Motor("Mercedez", 900, 4);
        Motor motor1 = new Motor("Mercedez", 900, 6);
        Carro[] carros = new Carro[2];
        Carro carro1 = new Carro("A4", llantas, "Monocasco", motor);
        Carro carro2 = new Carro("A3", ruedas, "Independiente", motor1);
        carros [0]= carro1;
        carros [1]= carro2;
        Persona man = new Persona(carros, "Juan Perez", 18);
        man.Listar(2);
    }
}