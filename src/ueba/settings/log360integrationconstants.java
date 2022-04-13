package ueba.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class log360integrationconstants {
    public HashMap getRequest() throws IOException {
        FileInputStream f =new FileInputStream("src/constants/constants.properties");
        Properties prop=new Properties();
        prop.load(f);
        HashMap<String,String> map=new HashMap<>();
        String additionrequest =prop.getProperty("");
        map.put("domainadditionrequest",additionrequest);
        String integrationremoverequest= prop.getProperty("log360removeintegrationurl");
        map.put("log360removeintegrationurl",integrationremoverequest);
        return map;
    }
}
