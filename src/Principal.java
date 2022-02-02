import java.util.Scanner;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        DBManager.initConecction();

        int option;

        do {
            System.out.println("\n1. Ver Paises");
            System.out.println("2. Agregar Pais");
            System.out.println("3. Editar Pais");
            System.out.println("4. Eliminarr Pais");
            System.out.println("5. Salir");
            option = scanner.nextInt();

            switch (option){

                case 1:
                    verPaises();
                    break;
                case 2:
                    agregarPais();
                    break;
                case 3:
                    editarPais();
                    break;
                case 4:
                    eliminarPais();
                    break;
                case 5:
                    DBManager.closeConnection();
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida, vuelva a intentar.");
                    continue;


            }
        }while (option != 5);

    }
    private static void verPaises() {

        DBManager.getCountries();
    }
    private static void agregarPais() {

        System.out.println("Ingrese nombre del pais: ");
        scanner.nextLine();
        String nombrePais = scanner.nextLine();
        System.out.println("Ingrese numero de poblacion: ");
        int poblacionPais = scanner.nextInt();
        System.out.println("Ingrese continente al que pertenece el pais: ");
        scanner.nextLine();
        String continentePais = scanner.nextLine();

        DBManager.insertCountry(nombrePais, poblacionPais, continentePais);

    }
    private static void editarPais() {
        DBManager.getCountries();

        System.out.println("Ingrese el ID del pais que desea editar: ");
        long id = scanner.nextLong();

        System.out.println("Nombre del pais: ");
        scanner.nextLine();
        String nombrePais = scanner.nextLine();
        System.out.println("Numero de poblacion: ");
        int poblacionPais = scanner.nextInt();
        System.out.println("Continente al que pertenece el pais: ");
        scanner.nextLine();
        String continentePais = scanner.nextLine();

        DBManager.updateCountries(id, nombrePais,poblacionPais, continentePais);

    }
    private static void eliminarPais() {

        DBManager.getCountries();

        System.out.println("Ingrese el id del pais que desea eliminar: ");
        long id = scanner.nextLong();

        DBManager.deleteCountry(id);
    }






}
