package Utils;

import Cases.BaseFluga;
import Pojc.Variable;

import java.util.List;
import java.util.Map;

public class PasswordGenerator {
    public static String iPasswordGenerator(){
       String sql="select min(`password`)+1 as MinPassword from account";
       Map<String,Object> map= JDBCQuery.Query(sql);
       System.out.println(map.get("MinPassword").toString());
       return map.get("MinPassword").toString();
    }
    public static String truePassword(){
        String sql="select `password` from account where username='qihua.shen@feibeluga.com'";
        Map<String,Object> map= JDBCQuery.Query(sql);
        return map.get("password").toString();
    }
}
