package ueba.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Domainsettingsconstants {
  //  String request = "{\"DOMAIN_NAME\":\"domainname\",\"DC_LIST\":[\"dcname\"],\"IS_AUTH_REQUIRED\":\"true\",\"USERNAME\":\"[username]\",\"PASSWORD\":\"[password]\",\"syncFreq\":\"Daily\",\"syncDay\":\"Sunday\",\"syncHr\":\"02\",\"syncMin\":\"00\",\"syncMask\":\"1\",\"syncDate\":\"01\"}";

    public HashMap getRequest() throws IOException {
        FileInputStream f =new FileInputStream("src/constants/constants.properties");
        Properties prop=new Properties();
        prop.load(f);
        HashMap<String,String> map=new HashMap<>();
        String additionrequest =prop.getProperty("domainaddtionrequest");
        map.put("domainadditionrequest",additionrequest);
        String deletionrequest= prop.getProperty("domaindeletionrequest");
        map.put("domaindeletionrequest",deletionrequest);
        return map;
    }
}
