package StudyJavaMavenDay02;

import Utils.JDBCQuery;

import java.util.Map;
import java.util.Set;

public class JDBCTest {
    public static void main(String[] args) {

            String sql="select email,name,id from account where password=? and name=?";
            Object[] values={"123456","Albin"};
           Map<String,Object> queryResult= JDBCQuery.Query(sql,values);
            Set<String> keys= queryResult.keySet();
            for (String key:keys){
                System.out.println(queryResult.get(key));
             }
    }
}
