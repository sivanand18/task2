package ueba;

import java.io.IOException;
import java.sql.SQLException;

public interface Factoryinterface {
    public Object connection() throws ClassNotFoundException, SQLException, IOException;

}
