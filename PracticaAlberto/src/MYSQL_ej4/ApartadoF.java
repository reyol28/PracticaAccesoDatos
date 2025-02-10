package MYSQL_ej4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartadoF
{
    public static void main(String[] args) {
        try {
            Connection conex = ConexionMySql.conectar("practica_mysql");
            // preparamos consulta y la ejecutamos
            //String query = "SELECT NUMEM,NOMEM,SALAR FROM temple";
            String query = "SELECT D.NUMDE, SUM(E.SALAR) AS SALARIOS_TOTALES, SUM(E.COMIS) AS COMISIONES_TOTALES, SUM(E.NUMHI) AS HIJOS_TOTALES\n" +
                    "FROM `practica_mysql`.`TEMPLE` E JOIN `practica_mysql`.`TDEPTO` D ON E.NUMDE=D.NUMDE\n" +
                    "WHERE D.TIDIR='F'\n" +
                    "GROUP BY D.NUMDE;\n";
            PreparedStatement instruccion = conex.prepareStatement(query);
            ResultSet resultado = instruccion.executeQuery();

            //analizamos el resultado de la consulta
            while (resultado.next()) {
                String numDep = resultado.getString(1);
                String salTot = resultado.getString(2);
                String comTot = resultado.getString(3);
                String hijosTot = resultado.getString(4);

                System.out.println("Número departamento:" + numDep + " - Salarios totales:" + salTot+ " - Comisiones totales:"+ comTot+ " - Hijos totales:"+ hijosTot);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
