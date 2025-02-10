package MONGODB_ej5;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Connection;
import org.bson.Document;

public class Ejemplo
{
    public static void main(String[] args)
    {
        try
        {
            MongoClient client = new MongoClient("localhost", 27017);

             //Para ver todas las bases de datos que existen en el cliente haríamos lo siguiente

            MongoCursor<String> mongoCursor1=client.listDatabaseNames().iterator();
            System.out.println("BASES DE DATOS:");
            while(mongoCursor1.hasNext())
            {
                System.out.println(mongoCursor1.next());
            }

            System.out.println();

            MongoDatabase db = client.getDatabase("admin");



            //para hacer lo que en sql sería un select hacemos lo siguiente
            MongoCollection<Document> productCollection=db.getCollection("amigos");

            FindIterable<Document> findIterable=productCollection.find();
            MongoCursor<Document> mongoCursor= findIterable.iterator();

            System.out.println("DATOS DE LA COLECCIÓN AMIGOS:");
            while(mongoCursor.hasNext())
            {
                System.out.println(mongoCursor.next());
            }


        }catch (MongoException e)
        {
            System.out.println("Error al connectar");
        }
    }
}
