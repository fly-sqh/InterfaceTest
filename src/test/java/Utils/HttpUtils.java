package Utils;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * Http请求工具类
 */
public class HttpUtils {
    public static Map<String,String> cookies=new HashMap<String, String>();

    /**
     * get请求方式测试接口
     * @param url 被测接口的uri
     * @param para 被测接口的请求参数
     * @return 将被测接口请求之后的响应数据以map数组返回，返回数据有响应状态码和响应报文
     */
    public static Map<String,String> getRequst(String url, Map<String,String> para) {
        Map<String,String> responses=new HashMap<String, String>();//创建一个空的Map数组
        Set<String> keys= para.keySet();//拿到传入参数的map数组的所有key
        int count=1;//定义一个初始数字
        try{
        for (String key:keys){
            String value=para.get(key);//获取到对应key的值
            if(count==1){//如果是首个参数，在参数前面拼接上？，键名与键值之间用=连接
                url+=("?"+key+"="+value);
            }else {
                url+=("&"+key+"="+value);//如果不是首个参数，就拼接&
            }
            count++;//初始数字自增
        }
            System.out.println(url);
        HttpGet get=new HttpGet(url);//创建一个get请求对象，传入被测接口的url
        HttpClient client=HttpClients.createDefault();//创建一个HTTP客户端对象
        addCookieInRequestHeader(get);//将数组里保存的sessionid保存到请求头当中
        HttpResponse httpResponse= client.execute(get);//实现get请求，将返回的数据存储到一个HttpResponse中
        //拿到响应状态码，赋值给statusCode（注意：返回的是int类型，这里强转为了String，因为数组是String类型）
        String statusCode=String.valueOf(httpResponse.getStatusLine().getStatusCode());
        getAndStoreCookiesFromResponseHeader(httpResponse);
        responses.put("statusCode",statusCode);//将响应状态码存到map数组里
        String response= EntityUtils.toString(httpResponse.getEntity());//拿到实体响应报文转成String类型，存储到response里
        responses.put("response",response);//将实体响应报文存到map数组
        }catch (IOException e){
            e.printStackTrace();
        }
        return responses;//返回map数组
    }

    /**
     * 获取请求响应的sessionid放到请求头中，用作接口的鉴权
     * @param  httpRequest 请求类型
     */
    private static void addCookieInRequestHeader(HttpRequest httpRequest) {
        String cookieValue=cookies.get("sessionid");
        httpRequest.addHeader("Cookie",cookieValue);
}

    /**
     * 获取接口的响应头，取出cookie，如果包含PLAY_SESSION，就保存到cookie数组中
     * @param httpResponse 请求类型
     */
    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        //从响应头里取出set-cookie的响应头
        Header header= httpResponse.getFirstHeader("Set-Cookie");
        if (header!=null){//如果不为空，取出响应头数据
            String value= header.getValue();
            if (value!=null&&value.trim().length()>0){//如果数据不为空，且长度大于0则以；分割数据
              String[] cookiePairs=value.split(";");
              if (cookiePairs!=null){
                  for(String cookiePair:cookiePairs){
                      if (cookiePair.contains("PLAY_SESSION")){
                          cookies.put("sessionid",cookiePair);
                      }
                  }
              }
            }
        }
    }

    /**
     * post请求方式测试接口
     * @param url 被测接口的uri
     * @param para 被测接口的参数
     * @return
     */
    public static Map<String,String> postRequst(String url,Map<String,String> para) {
        Map<String,String> responses=new HashMap<String, String>();
        HttpPost post=new HttpPost(url);












        List<BasicNameValuePair> pairList=new ArrayList<BasicNameValuePair>();
        Set<String> keys= para.keySet();
        try{
        for(String key:keys) {
            String value=para.get(key);
            pairList.add(new BasicNameValuePair(key, value));
            post.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        }
        HttpClient httpClient=HttpClients.createDefault();//创建一个HttpClient对象
        addCookieInRequestHeader(post);//将数组里保存的sessionid保存到请求头当中

        HttpResponse request= httpClient.execute(post);//实现post请求，将结果存入HttpResponse
        getAndStoreCookiesFromResponseHeader(request);
        String statusCode= String.valueOf(request.getStatusLine().getStatusCode());
        responses.put("statusCode",statusCode);
        String response=EntityUtils.toString(request.getEntity());
        responses.put("response",response);
        }catch (IOException e){
            e.printStackTrace();
        }

        return responses;
    }

}
