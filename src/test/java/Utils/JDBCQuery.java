package Utils;

import com.mysql.cj.x.protobuf.MysqlxExpr;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBCQuery {
    public static Properties properties=new Properties();//创建properties对象
    static {
        File file=new File("Config/jdbc.properties");//传入配置好的文件路径
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(file);
            properties.load(inputStream);//读取文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据sql查询表数据，并以map返回，key为字段名，value为字段值
     * @param sql 要执行的sql语句
     * @param values 条件字段的值
     * @return
     */
    public static Map<String,Object> Query(String sql, Object ... values )  {
         Map<String,Object> finalResult=new HashMap<String, Object>();
        try {
            String url= properties.getProperty("jdbc.url");//获取文件中jdbc.url键值对的值
            String username=properties.getProperty("jdbc.username");//获取文件中jdbc.username键值对的值
            String password=properties.getProperty("jdbc.password");//获取文件中jdbc.password键值对的值
            //1.创建一个连接数据库的对象
            Connection con = DriverManager.getConnection(url,username,password);
            //2.获取PreparedStatement对象（此类型的对象有提供数据库操作方法）：
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            //3.设置条件字段的值：
            for(int i=0;i<values.length;i++) {
                preparedStatement.setObject(i+1,values[i]);
            }
            //4.调用查询方法，执行查询，返回ResultSet（结果集）：
            ResultSet result= preparedStatement.executeQuery();
            ResultSetMetaData resultMetaData= result.getMetaData();
            int columnCount= resultMetaData.getColumnCount();
            List<String> list=new ArrayList<String>();
            //5.利用while循环拿值，直到没有数据值

            while(result.next()){
                for (int i = 1; i <=columnCount ; i++) {
                    String columndata= resultMetaData.getColumnLabel(i);
                    String colummresult=result.getObject(columndata).toString();
                    finalResult.put(columndata,colummresult);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return finalResult;
    }
}
