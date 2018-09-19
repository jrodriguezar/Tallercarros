package persona;
/**
 *
 * @author Jhon
 */
public class Persona {
    private Carro[] carros;
    private String nombre;
    private int edad;

    public Persona(Carro[] carros, String nombre, int edad) {
        this.carros = carros;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Carro[] getCarros() {
        return carros;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setCarros(Carro[] carros) {
        this.carros = carros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public void Listar(int num_carros){
        for (int i = 0; i < num_carros; i++) {
            System.out.println("Los datos de su " + this.carros[i].getNumserie() + " son:");
            System.out.println("Numero de serie: " + this.carros[i].getNumserie());
            System.out.println("Marca motor: " + this.carros[i].getMotor().getMarca());
            System.out.println("Marca de ruedas: " + this.carros[i].mostrardatosrueda());
            System.out.println("Tipo de chasis: " + this.carros[i].getChasis().getSerie());
        }
    }
}