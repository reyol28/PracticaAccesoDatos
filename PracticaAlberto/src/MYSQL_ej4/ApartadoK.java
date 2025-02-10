package MYSQL_ej4;

import java.sql.*;

public class ApartadoK
{
    public static void main(String[] args) {
        try{
            //hago la conexion
            Connection conex=ConexionMySql.conectar("practica_mysql");
            //hago primero un drop procedure en caso de que exista para evitar errores
            String dropProcedure="DROP PROCEDURE IF EXISTS MostrarEmpleadosPorDepartamento";

            //luego almaceno el procedure en otro string
            String procedure="CREATE PROCEDURE MostrarEmpleadosPorDepartamento(IN p_NUMDE INT) "
                    + "BEGIN "
                    + "    SELECT NUMEM,NOMEM,SALAR,COMIS FROM TEMPLE WHERE NUMDE = p_NUMDE; "
                    + "END";


            try(Statement st= conex.createStatement()){
                //ejecuto los scripts de antes con (Statement).execute(SQL command)
                st.execute(dropProcedure);
                System.out.println("Procedure eliminado con éxito (si existía).");


                st.execute(procedure);
                System.out.println("Procedure creado satisfactoriamente.");

            }

            //para probar si ha funcionado hago un call del procedimiento, que en sqldevelopper es como ejecutar el procedimiento


            try(CallableStatement stmt = conex.prepareCall("CALL MostrarEmpleadosPorDepartamento(?)")) {

                stmt.setInt(1, 110); // Pasar el número de departamento como parámetro


                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Número empleado: " +rs.getInt(1)+"--- Nombre: " + rs.getString(2)+"--- Salario: " + rs.getString(3)+"--- Comisión: " + rs.getString(4));
                }
            }

        }catch(SQLException e)
        {
            System.out.println("Error al conectar");
        }
    }
}
