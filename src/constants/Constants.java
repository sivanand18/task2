package constants;

import logon.login;
import org.json.JSONObject;
import ueba.Dbconnection;
import ueba.Factory;
import ueba.Factoryinterface;
import ueba.Function;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class Constants {
    InputHandler inputHandler=new InputHandler();
    Factory factory=new Factory();
    public static JSONObject hostDetails= InputHandler.getHostDetails();
   login l=new login();
    JSONObject sessiondetails=l.getSessionDetails();
    String csrf=sessiondetails.getString("csrf");
    String cookie=sessiondetails.getString("cookie");
    Function f=new Function();
    public  String connection_type=f.getproperty("content-type");

     Factoryinterface fcon= (Factoryinterface) factory.getanyconnection("propertyconnection");
     Properties pcon= (Properties) fcon.connection();
     String url= pcon.getProperty("domainadditionurl");
     String domaindeletionurl=pcon.getProperty("domaindeletionurl");

    public String getDomaindeletionurl() {
        return domaindeletionurl;
    }

    public void setDomaindeletionurl(String domaindeletionurl) {
        this.domaindeletionurl = domaindeletionurl;
    }
   /* {
        try {
            Dbconnection db=new Dbconnection();

            Factoryinterface dbcon= (Factoryinterface) factory.getanyconnection("dbconnection");
            Connection con= (Connection) dbcon.httpconnection();
            Statement stmt;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select url from responses where connectonname='domainconnection'");
            rs.next();
            url = rs.getString(1);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    public Constants() throws IOException, SQLException, ClassNotFoundException {
    }


    public String getConnection_type() {
          return connection_type;
     }

     public String getCookie() {
          return cookie;
     }
     public  String getCsrf()
     {
         return csrf;
     }


     public void setConnection_type(String connection_type) {
          this.connection_type = connection_type;
     }

     public void setCookie(String cookie) {
          this.cookie = cookie;
     }



     public String getUrl() {
          return url;
     }

     public void setUrl(String url) {
          this.url = url;
     }


}
