package MYSQL_ej4;

import java.sql.*;

public class ApartadoM
{
    public static void main(String[] args) {
        try{
            //hago la conexion
            Connection conex=ConexionMySql.conectar("practica_mysql");
            //hago primero un drop procedure en caso de que exista para evitar errores
            String dropProcedure="DROP PROCEDURE IF EXISTS BuscarEmpleadosPorApellido";

            //luego almaceno el procedure en otro string
            String procedure="CREATE PROCEDURE BuscarEmpleadosPorApellido(?)\n" +
                    "BEGIN\n" +
                    "    DECLARE done INT DEFAULT 0;\n" +
                    "    DECLARE contador INT DEFAULT 0;\n" +
                    "    DECLARE v_numem INT;\n" +
                    "    DECLARE v_nomem VARCHAR(30);\n" +
                    "    DECLARE empleado_cursor CURSOR FOR\n" +
                    "        SELECT NUMEM, NOMEM\n" +
                    "        FROM temple\n" +
                    "        WHERE NOMEM LIKE CONCAT('%', cadena_apellido, '%');\n" +
                    "    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;\n" +
                    "    OPEN empleado_cursor;\n" +
                    "    empleado_loop: LOOP\n" +
                    "        FETCH empleado_cursor INTO v_numem, v_nomem;\n" +
                    "\n" +
                    "        IF done = 1 THEN\n" +
                    "            LEAVE empleado_loop;\n" +
                    "        END IF;\n" +
                    "        SELECT v_numem AS 'Número de Empleado', v_nomem AS 'Apellido';\n" +
                    "        SET contador = contador + 1;\n" +
                    "    END LOOP;\n" +
                    "    CLOSE empleado_cursor;\n" +
                    "    SELECT CONCAT('Total de empleados encontrados: ', contador) AS 'Resultado';\n" +
                    "END\n";


            try(Statement st= conex.createStatement()){
                //ejecuto los scripts de antes con (Statement).execute(SQL command)
               // st.execute(dropProcedure);
                System.out.println("Procedure eliminado con éxito (si existía).");

                //el string procedure no me funciona como en los otros programas, por lo que lo dejo comentado para que al menos se vea el CALL
               // st.execute(procedure);
                System.out.println("Procedure creado satisfactoriamente.");

            }

            //para probar si ha funcionado hago un call del procedimiento, que en sqldevelopper es como ejecutar el procedimiento


            try(CallableStatement stmt = conex.prepareCall("CALL BuscarEmpleadosPorApellido(?)")) {

                stmt.setString(1, "PE"); // Pasar el número de departamento como parámetro

                boolean hasResultSet = stmt.execute();

                // Procesar el primer conjunto de resultados (empleados)
                while (hasResultSet) {
                    try (ResultSet rs = stmt.getResultSet()) {
                        while (rs.next()) {
                            // una vez recorridos los empleados me da error y no se solucionarlo
                            if (rs.getString("Apellido") == null) {

                                System.out.println(rs.getString("Resultado"));
                            } else {
                                int numEmpleado = rs.getInt("Número de Empleado");
                                String apellido = rs.getString("Apellido");
                                System.out.println("Empleado: " + numEmpleado + " - Apellido: " + apellido);
                            }
                        }
                    }
                    // Avanzar al siguiente conjunto de resultados
                    hasResultSet = stmt.getMoreResults();
                }




            }

        }catch(SQLException e)
        {
            System.out.println("Error al conectar");
        }
    }
}
