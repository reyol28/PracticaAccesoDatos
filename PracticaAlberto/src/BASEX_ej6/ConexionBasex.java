package BASEX_ej6;

import org.basex.core.*;
import org.basex.core.cmd.*;

import java.io.IOException;

public class ConexionBasex {
    public static void main(String[] args) {
        String rutaXML = "biblioteca.xml"; //ruta del xml (como he subido el xml al proyecto solo pongo el nombre)
        String xPath = "/biblioteca/libro"; //consulta XPath
        String xQuery = "for $libro in //libro[fechaPublicacion/@año = \"1973\"]\n" +
                "return $libro/titulo"; //consulta XQuery

        //si quieres realizar otras consultas simplemente cambia el texto de los strings de querys de arriba
        //o crea uno nuevo y ejecuta una nueva consulta en dentro del bloque try-catch con executeXQueryQuery
        try {
            // Inicializa BaseX
            Context context = new Context();

            //cargo el xml en una base de datos temporal
            new CreateDB("Biblioteca", rutaXML).execute(context);

            //ejecutar consulta XPath
            System.out.println("Resultados de XPath:");
            consultaXPath(context, xPath);

            //ejecutar consulta XQuery
            System.out.println("\nResultados de XQuery:");
            consultaXQuery(context, xQuery);


            context.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consultaXPath(Context context, String query) throws BaseXException {
        String fullQuery = query;
        System.out.println(new XQuery(fullQuery).execute(context));
    }

    private static void consultaXQuery(Context context, String query) throws BaseXException {
        System.out.println(new XQuery(query).execute(context));
    }
}
