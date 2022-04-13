package ueba;


import constants.Constants;

import java.io.IOException;
import java.sql.SQLException;

//this class contains all the required value
public class Requirements {
    Constants c=new Constants();
    String url =c.getUrl();
    String cookie=c.getCookie();
    String ueba_csrf=c.getCsrf();
    String connection_type=c.getConnection_type();
    String domaindeltionurl=c.getDomaindeletionurl();
    public Requirements() throws IOException, SQLException, ClassNotFoundException {
    }
}
