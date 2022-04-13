package ueba;

import com.adventnet.ds.query.SelectQuery;
import com.adventnet.ds.query.SelectQueryImpl;
import com.adventnet.ds.query.Table;
import com.adventnet.mfw.bean.BeanUtil;
import com.adventnet.persistence.*;
import constants.Constants;
import encoding.Encrypted;
import logon.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


public class Mainn {
    public static void main(String[] args) throws Exception {
        Factory factory=new Factory();
        Function obj=new Function();
        obj.domainsettings();
   //  obj.domaindeletion("L3CQA2");
        // obj.getall();
       // obj.log360removeintegration();
       // login l=new login();
     //   l.getSessionDetails();

    }


}
