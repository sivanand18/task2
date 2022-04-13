package ueba;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Propertyconnection implements Factoryinterface{

    @Override
    public Object connection() throws ClassNotFoundException, SQLException, IOException {
        FileInputStream f =new FileInputStream("src/constants/constants.properties");
        Properties prop=new Properties();
        prop.load(f);
        return prop;
    }


}
