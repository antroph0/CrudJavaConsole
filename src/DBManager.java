import java.sql.*;

public class DBManager {

    private static final String user = "postgres";
    private static final String pass = "1234";
    private static Connection connection;

    public static void initConecction(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Paises", user,pass);
            System.out.println("Conexion exitosa.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void insertCountry(String countryName, int countryPopulation, String countryContinent){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO paises (nombre, poblacion, continente) VALUES (?,?,?)");
            statement.setString(1, countryName);
            statement.setInt(2, countryPopulation);
            statement.setString(3, countryContinent);

            statement.execute();

            System.out.println("\n Se ha agregado un nuevo pais.");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void getCountries(){

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Paises ORDER BY id");
            ResultSet resultSet = statement.executeQuery();

            System.out.println("id |  nombre pais     |  poblacion    |  continente");
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String countryName = resultSet.getString("nombre");
                int countryPopulation = resultSet.getInt("poblacion");
                String countryContinent = resultSet.getString("continente");
                System.out.println("");
                System.out.println(id + "    \t" + countryName + "        \t" + countryPopulation + "        \t" + countryContinent);

            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateCountries(long id, String editCountryName, int editCountryPopulation, String editCountryContinent){

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Paises SET nombre=?, poblacion=?, continente=? WHERE id=?");
            statement.setString(1, editCountryName);
            statement.setInt(2, editCountryPopulation);
            statement.setString(3, editCountryContinent);

            statement.setLong(4, id);

            int row = statement.executeUpdate();
            if (row == 0){
                System.out.println("No existe el ID en la base de datos");
            }else {
                System.out.println("Actualizado correctamente.");
            }


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCountry(long id){

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM Paises WHERE id=?");
            statement.setLong(1, id);

            int row = statement.executeUpdate();

            if(row != 0){
                System.out.println("Se ha eliminado el registro con exito");
            }else {
                System.out.println("El id ingresado no existe en la base de datos, verificar. ");
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection(){
        try {
            connection.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}






















