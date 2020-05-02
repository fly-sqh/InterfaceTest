package StudyJavaMavenDay02;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

public class XmlUtil {
    public static void main(String[] args) throws Exception {
         Xml("src/Config/XmlUtil.xml");
    }
    public static void Xml(String path) throws Exception {
        InputStream inputStream=new FileInputStream(new File(path));//创建一个io流对象，传入文件地址
        SAXReader reader=new SAXReader();//创建SAXReader对象，读取XML文件使用的对象
        Document document= reader.read(inputStream);//用SAXReader对象读取XML文件得到一个document
        Element root= document.getRootElement();//获取document的根目录
        Class<Page> clazz=Page.class;//根据一个类的字节码文件来创建这个类的对象
        Page page=clazz.newInstance();
        Method method= clazz.getMethod("setKeyword", String.class);//获取这个类需要使用的方法，传入类名和参数类型
        String rootvalue=root.attributeValue("Keyword");//获取根节点某个元素的属性值
        method.invoke(page,rootvalue);//调用反射出来对象的方法赋值
        System.out.println(page.getKeyword());
        List<Element> elements= root.elements();//拿到根目录下所有的子元素
        Class<Locator> clazz1=Locator.class;//根据Locator类的字节码文件来创建这个类的对象
        Locator locator=clazz1.newInstance();//得到一个locator对象
        for (Element element:elements){//遍历根目录下的子元素
           List<Attribute> attributes= element.attributes();//将根目录下的子元素添加到集合中
           for (Attribute attribute:attributes) {//遍历子元素集合
               String attributeName= attribute.getName();//循环拿到子元素的属性名
               attributeName="set"+attributeName.substring(0,1).toUpperCase()+attributeName.substring(1);
               //将拿到的属性名首字母大写再拼接一个"set"得到类里面的set方法名
               Method method1=clazz1.getMethod(attributeName,String.class);
               //获取到set方法名
               String attributeValue=attribute.getValue();
               //获取元素值
               method1.invoke(locator,attributeValue);
               //放到set方法中
           }System.out.println("keyword：" + locator.getKeyword() + " by：" +locator.getBy() + " value：" + locator.getValue());
        }
    }
}
