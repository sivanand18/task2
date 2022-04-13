package logon;

import constants.InputHandler;
import okhttp3.*;
import org.json.JSONObject;
import ueba.httpconnection;

import java.io.IOException;

public class login {


        private static final OkHttpClient HTTP_CLIENT = httpconnection.getconnection();
    public static String csrf,session_id,session_id_sso,cookie;
    static JSONObject hostDetails=InputHandler.getHostDetails();
    static JSONObject userCreds=InputHandler.getUserCreds();
    public static JSONObject getSessionDetails() throws IOException {
        String url = ""+hostDetails.getString("baseUrl")+"/j_security_check";
        RequestBody formData = new FormBody.Builder()
                .add("j_username",userCreds.getString("username"))
                .add("j_password",userCreds.getString("password"))
                .add("domainName",userCreds.getString("domainName"))
                .add("AUTHRULE_NAME", "Authenticator")
                .build();
        Request httpReq = new Request.Builder()
                .url(url)
                .post(formData)
                .build();
        Response response = HTTP_CLIENT.newCall(httpReq).execute();
        session_id=response.headers("Set-Cookie").get(0);
        session_id=session_id.substring(session_id.indexOf("=")+1,session_id.indexOf(";"));
        session_id_sso=response.headers("Set-Cookie").get(1);
        session_id_sso=session_id_sso.substring(session_id_sso.indexOf("=")+1,session_id_sso.indexOf(";"));
        String url1 = ""+hostDetails.getString("baseUrl");
        Request request1 = new Request.Builder()
                .url(url1)
                .build();
        Response response1 = HTTP_CLIENT.newCall(request1).execute();
         Headers headers=response1.headers();
        System.out.println(headers);
        csrf=response1.headers("Set-Cookie").get(0);

        csrf=csrf.substring(csrf.indexOf("=")+1,csrf.indexOf(";"));
        System.out.println(csrf);
        cookie="JSESSIONIDUEBA="+session_id+"; JSESSIONIDUEBASSO="+session_id_sso+"; uebacsrf="+csrf+"; _zcsr_tmp="+csrf+"";
        System.out.println(cookie);
        JSONObject sessionDetails=new JSONObject();
        sessionDetails.put("cookie",cookie);
        sessionDetails.put("csrf",csrf);
        return sessionDetails;
    }



}