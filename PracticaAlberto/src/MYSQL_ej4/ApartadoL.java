package MYSQL_ej4;

import java.sql.*;

public class ApartadoL
{
    public static void main(String[] args) {
        try{
            //hago la conexion
            Connection conex=ConexionMySql.conectar("practica_mysql");
            //hago primero un drop procedure en caso de que exista para evitar errores
            String dropProcedure="DROP PROCEDURE IF EXISTS ContarEmpleadosSinComision";

            //luego almaceno el procedure en otro string
            String procedure="CREATE PROCEDURE ContarEmpleadosSinComision() "
                    + "BEGIN "
                    + "    SELECT COUNT(NUMEM) FROM TEMPLE WHERE COMIS IS NULL; "
                    + "END";


            try(Statement st= conex.createStatement()){
                //ejecuto los scripts de antes con (Statement).execute(SQL command)
                st.execute(dropProcedure);
                System.out.println("Procedure eliminado con éxito (si existía).");


                st.execute(procedure);
                System.out.println("Procedure creado satisfactoriamente.");

            }



            //hago el call del procedure para ver si ha funcionado
            try(CallableStatement stmt = conex.prepareCall("CALL ContarEmpleadosSinComision")) {

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Número Total de Empleados Sin Comision: " +rs.getInt(1));
                }
            }

        }catch(SQLException e)
        {
            System.out.println("Error al conectar");
        }
    }
}
