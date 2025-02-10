package MYSQL_ej4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartadoE {
    public static void main(String[] args) {
        try {
            Connection conex = ConexionMySql.conectar("practica_mysql");
            // preparamos consulta y la ejecutamos
            //String query = "SELECT NUMEM,NOMEM,SALAR FROM temple";
            String query = "SELECT NOMEM,SALAR\n" +
                    "FROM TEMPLE\n" +
                    "WHERE SALAR IN(SELECT COMIS FROM TEMPLE)\n" +
                    "ORDER BY NOMEM;\n";
            PreparedStatement instruccion = conex.prepareStatement(query);
            ResultSet resultado = instruccion.executeQuery();

            //analizamos el resultado de la consulta
            while (resultado.next()) {
                String nombre = resultado.getString(1);
                String salar = resultado.getString(2);

                System.out.println("Nombre:" + nombre + " - Salario:" + salar);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
