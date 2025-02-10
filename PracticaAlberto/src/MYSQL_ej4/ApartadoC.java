package MYSQL_ej4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartadoC {
    public static void main(String[] args) {
        try {
            Connection conex= ConexionMySql.conectar("practica_mysql");
            // preparamos consulta y la ejecutamos
            //String query = "SELECT NUMEM,NOMEM,SALAR FROM temple";
            String query = "SELECT NUMDE, AVG(timestampdiff(YEAR,FECIN,CURDATE())) AS MEDIA_AÑOS\n" +
                    "FROM `practica_mysql`.`TEMPLE`\n" +
                    "WHERE NUMDE IN(111,112)\n" +
                    "GROUP BY NUMDE;\n";
            PreparedStatement instruccion = conex.prepareStatement(query);
            ResultSet resultado = instruccion.executeQuery();

            //analizamos el resultado de la consulta
            while (resultado.next()) {
                String numero=resultado.getString(1);
                String avg=resultado.getString(2);

                System.out.println("Numero:"+numero+" - AVG:"+avg);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}