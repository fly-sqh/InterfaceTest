package StudyJavaMavenDay02;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*XML文件解析
1。创建SaxReader对象
2。获取document对象
3.获取根元素
4.获取根元素下的子元素的集合
5.遍历集合根据标签属性拿出一个个的对应值

 */
public class XmlDemo {

    public static void main(String[] args) throws Exception {
        String filepath="src/Config/Person.xml";
        XmlDemo xmlDemo=new XmlDemo();
        List<Student> students= xmlDemo.xmlfile(filepath);
        for(Student student:students){
            System.out.println("年龄："+student.getAge()+" 姓名："+student.getName()+" 性别："+student.getGender());
        }

    }
    public List<Student> xmlfile(String filepath) throws Exception {
        SAXReader sax=new SAXReader();
        Document document= sax.read(new File(filepath));
        Element root= document.getRootElement();
        List<Element> elements= root.elements("student");
        Class<Student> clazz=Student.class;
        List<Student> students = new ArrayList<Student>();
        for(Element element:elements){
            Student student= clazz.newInstance();
            List<Element> studentElements=element.elements();
            for (Element studentElement:studentElements){
                String name= studentElement.getName();
                name=name.substring(0,1).toUpperCase()+name.substring(1);
                name="set"+name;
                Method Method= clazz.getMethod(name, String.class);
                Method.invoke(student,studentElement.getText());
            }students.add(student);


        }
       return students;
    }
}
