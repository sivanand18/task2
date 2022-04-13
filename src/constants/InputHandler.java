package constants;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InputHandler {
    FileInputStream fileInputStream = new FileInputStream("src/constants/constants.properties");
    public static Properties props = new Properties();

    public InputHandler() throws IOException {
        props.load(fileInputStream);
    }

    public static JSONObject getHostDetails() {
        JSONObject hostDetails = new JSONObject();
        hostDetails.put("baseUrl", props.getProperty("baseUrl"));
        return hostDetails;
    }

    public static JSONObject getUserCreds() {
        JSONObject userCreds = new JSONObject();
        userCreds.put("username", props.getProperty("username"));
        userCreds.put("password", props.getProperty("password"));
        userCreds.put("domainName", props.getProperty("domainName"));
        return userCreds;
    }
}
