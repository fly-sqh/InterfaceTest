package Utils;

import Cases.BaseFluga;
import Pojc.BackWriteData;
import Pojc.Case;
import Pojc.Variable;

import java.lang.reflect.Method;
import java.util.*;

public class VariableUtils {
    public static List<Variable> variables = new ArrayList<Variable>();
    public static Map<String, String> varibleAndName = new HashMap<String, String>();

    /**
     * 调用load方法将取出的数据保存到对象中，再将返回的对象list赋值给varibleAndName这个对象数组
     */
    static {
        variables = ExcelUtils.load(BaseFluga.filePath, "参数", Variable.class);
        saveVaribleAndNameToMap();
    }

    /**
     * 遍历对象数组，取出值存放到Map当中,并将数据回写到对应的单元格中
     */
    private static void saveVaribleAndNameToMap(){
        for (Variable variable : variables) {
            String name = variable.getName();
            String value = variable.getValue();
            if (variable.getValue()==null||variable.getValue().trim().length()==0){
                String reflectClass= variable.getReflectClass();
                String reflectMethod=variable.getReflectMethod();
                try {

                    Class clazz= Class.forName(reflectClass);
                    Object object =clazz.newInstance();
                    Method method= clazz.getMethod(reflectMethod);
                    value= (String)method.invoke(object);
                    BackWriteData backWriteData=new BackWriteData
                            (variable.getName(),"参数",value,"ReflectValue");
                    ExcelUtils.backWriteDatas.add(backWriteData);
                    CaseUtils.BatchWriteData(BaseFluga.filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            varibleAndName.put(name, value);
        }
    }

    /**
     * 将数组里的key值与传过来的数据对比，如果包含，就将对于key替换成map数组里的值
     * （数组里的key放的是excel文件参数sheet里的name，值为value）
     *
     * @param parameter excel读取出来的参数（json）
     */
    public static String replaceVarible(String parameter) {
        Set<String> keys = varibleAndName.keySet();
        for (String key : keys) {
            System.out.println(key+"："+varibleAndName.get(key));
            if (parameter.contains(key)) {
                parameter = parameter.replace(key, varibleAndName.get(key));
            }
        }
        return parameter;
    }
}

