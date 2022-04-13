package ueba;

import encoding.Encrypted;
import okhttp3.*;
import ueba.settings.Domainsettingsconstants;
import ueba.settings.log360integrationconstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


//this class is used for network calls that we are going to use
public class Function implements Factoryinterface {
    Domainsettingsconstants dc=new Domainsettingsconstants();
    Factory factory=new Factory();
    Dbconnection dbconn=new Dbconnection();
    public void domainsettings() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        Factoryinterface dbcon= (Factoryinterface) factory.getanyconnection("dbconnection");
        Connection conn= (Connection) dbcon.connection();
        ResultSet rs= dbconn.getresult("select * from domaintable");
        while(rs.next()) {
            HashMap<String,String> request1 =dc.getRequest();
            String request=request1.get("domainadditionrequest");
            String  domainname= rs.getString(2);
            if(rs.getString(6).contains("false")) {
                System.out.println(domainname+" domain is already added");
                System.out.println();
                continue;
            }
            String  domaincontroller= rs.getString(3);
            String  username= rs.getString(4);
            String  password= rs.getString(5);
            Encrypted encrypted=new Encrypted();
            List <Integer> encusername=encrypted.encryptString(username);
            List <Integer> encpassword=encrypted.encryptString(password);
            Function function=new Function();
            username=function.converter(encusername);
            password=function.converter(encpassword);
            request = request.replace("domainname", domainname);
            request = request.replace("dcname", domaincontroller);
            request = request.replace("username", username);
            request = request.replace("password", password);
            Requirements obj = new Requirements();
            httpconnection con = new httpconnection();
            Request httpReq = con.postresponse(request, obj.url, obj.ueba_csrf, obj.cookie, obj.connection_type);
            OkHttpClient connect = con.getconnection();
            Response response = connect.newCall(httpReq).execute();
            Map<String, String> map = new HashMap<>();
            String value = response.body().string();
            response.body().close();
            String result1 = value.replaceAll("\"", "");
            String result = result1.replaceAll("[{ }]", "");
            String[] keyValuePairs = result.split(",");
            for (int i = 0; i < keyValuePairs.length; i++) {

                String[] message = keyValuePairs[i].split(":");
                map.put(message[0], message[1]);
            }
            response.body().close();
            String issuccess = map.get("isSuccess");
            if(issuccess.contains("true")) {
                System.out.println(domainname+" "+"successfully added");
                System.out.println();
                response.body().close();
            }
            else
            {
                System.out.println("problem while configuring domain "+domainname);
                System.out.println();
                response.body().close();
            }
            dbconn.updatetable("update domaintable set isdeleted='false' where domainname='"+domainname+"'");
            response.body().close();

        }
        return;

    }
    public void domaindeletion(String Domainname) throws SQLException, ClassNotFoundException, IOException {
        Dbconnection db=new Dbconnection();
        Connection con= db.postgresconnection();
        ResultSet rss= db.getresult("select isdeleted from domaintable where domainname='"+Domainname+"'");
        rss.next();
        String isdeleted=rss.getString(1);
        if(isdeleted.contains("true"))
        {
            System.out.println("Domain is not present");
            return;

        }
        else {
            String domainid = null;
            try {
                Statement stmt;
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select domain_id from  adsdomainconfiguration where domain_name='"+Domainname+"'");
                rs.next();
                domainid = rs.getString(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            HashMap<String, String> request1 = dc.getRequest();
            String request = request1.get("domaindeletionrequest");
            request = request.replace("deletiondomain", domainid);
            Requirements obj = new Requirements();
            httpconnection conn = new httpconnection();
            Request httpReq = conn.postresponse(request, obj.domaindeltionurl, obj.ueba_csrf, obj.cookie, obj.connection_type);
            OkHttpClient connect = conn.getconnection();
            Response response = connect.newCall(httpReq).execute();
            Map<String, String> map = new HashMap<>();
            String value = response.body().string();
            response.body().close();
            String result1 = value.replaceAll("\"", "");
            String result = result1.replaceAll("[{ }]", "");
            String[] keyValuePairs = result.split(",");
            for (int i = 0; i < keyValuePairs.length; i++) {

                String[] message = keyValuePairs[i].split(":");
                map.put(message[0], message[1]);
            }
            response.body().close();
            String issuccess = map.get("isSuccess");
            if(issuccess.contains("true")) {
                response.body().close();
                System.out.print(Domainname+" "+"is successfully deleted");
                System.out.println();
            }
            else
            {
                response.body().close();
                System.out.println("problem while deleting domain");
                System.out.println();

                return;
            }
            dbconn.updatetable("update domaintable set isdeleted='true' where domainname='"+Domainname+"'");
            response.body().close();
        }
      return;
    }
    public String converter(List<Integer> word)
    {
        String delim=",";
        StringBuilder sb = new StringBuilder();
        int j;
        for (j = 0; j < word.size()-1; j++) {
            sb.append(word.get(j));
            sb.append(delim);
        }
        sb.append(word.get(j));
        String res = sb.toString();


        return  res;
    }
    public String getproperty(String value) throws IOException {
        FileInputStream f =new FileInputStream("src/constants/constants.properties");
        Properties prop=new Properties();
        prop.load(f);
        String request =prop.getProperty(value);
        return request;
    }
    public void getall() {
        try {
            Factory factory=new Factory();
            Factoryinterface dbcon= (Factoryinterface) factory.getanyconnection("dbconnection");
            Connection con= (Connection) dbcon.connection();
            Statement stmt;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from domaintable");
            System.out.println("results:-");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
    public void log360removeintegration() throws IOException {

        log360integrationconstants log360=new log360integrationconstants();
        HashMap<String, String> request1 = log360.getRequest();
        String removeintegrationurl=request1.get("log360removeintegrationurl");
        System.out.println(removeintegrationurl);

    }
    @Override
    public Object connection() throws ClassNotFoundException, SQLException {
        return null;
    }




}
