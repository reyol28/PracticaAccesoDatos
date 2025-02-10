package MYSQL_ej4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApartadoJ
{
    public static void main(String[] args) {
        try
        {
            //primero hago la coexión
            Connection conex = ConexionMySql.conectar("practica_mysql");

            //hago el primer update table del apartado
            String update1="UPDATE TEMPLE\n" +
                    "    SET SALAR = SALAR * (1+?);\n";

            try(PreparedStatement ps1= conex.prepareStatement(update1)){
                //aquí pongo el parámetro que podría ser variable, de esta manera, si quisiera actualizar el valor del porcentaje de salario que aumenta puedo hacerlo modificando el valor de abajo
                ps1.setDouble(1,0.0533);

                int filasActualizadas = ps1.executeUpdate();
                System.out.println("Filas actualizadas del primer update: " + filasActualizadas);
            }

            //hago el segundo update table
            String update2="UPDATE TEMPLE\n" +
                    "    SET COMIS=COMIS* ? \n" +
                    "    WHERE NUMDE= (1+?) ;";

            try(PreparedStatement ps2= conex.prepareStatement(update2)){
                //aquí, igual que antes, vuelvo a poner parámetros variables para poder cambiar los datos que se cambian y en que porcentaje
                ps2.setDouble(1,0.0619);
                ps2.setInt(2,110);

                int filasActualizadas = ps2.executeUpdate();
                System.out.println("Filas actualizadas del segundo update: " + filasActualizadas);
            }
            System.out.println("\n--PROGRAMA FINALIZADO CON ÉXITO--");
        }
        catch(SQLException e)
        {
            System.out.println("Error al conectar.");
        }
    }

}
