package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexionBD
{
    public static Connection getConnection()
    {
        Connection con = null;

        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon","root", "");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}


