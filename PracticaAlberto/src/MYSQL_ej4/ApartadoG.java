package MYSQL_ej4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartadoG
{
    public static void main(String[] args) {
        try {
            Connection conex = ConexionMySql.conectar("practica_mysql");
            // preparamos consulta y la ejecutamos
            //String query = "SELECT NUMEM,NOMEM,SALAR FROM temple";
            String query = "SELECT E.EXTEL, COUNT(E.NOMEM) / COUNT(DISTINCT E.EXTEL) AS PROMEDIO_EMPLEADOS\n" +
                    "FROM `practica_mysql`.`TEMPLE` E JOIN `practica_mysql`.`TDEPTO` D ON E.NUMDE = D.NUMDE\n" +
                    "WHERE D.PRESU > 100\n" +
                    "GROUP BY E.EXTEL\n" +
                    "ORDER BY E.EXTEL;\n";
            PreparedStatement instruccion = conex.prepareStatement(query);
            ResultSet resultado = instruccion.executeQuery();

            //analizamos el resultado de la consulta
            while (resultado.next()) {
                String extTel = resultado.getString(1);
                String promedioEmp = resultado.getString(2);


                System.out.println("Extensión telefónica:" + extTel + " - Promedio empleados:" + promedioEmp);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
