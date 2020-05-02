package Utils;

import Cases.BaseFluga;
import Pojc.BackWriteData;
import Pojc.Case;
import Pojc.InterfaceInfo;
import Pojc.Variable;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
//读取excel文件指定行到指定行、指定列到指定列的连续数据
    public static List<BackWriteData> backWriteDatas=new ArrayList<BackWriteData>();
    static {
        loadSheet(BaseFluga.filePath,"用例");
        loadSheet(BaseFluga.filePath,"参数");
}
    public static Object[][] parameterData(String filepath, String sheetname,int startRow,int endRow,int startCell,int endCell) {
        Object[][] parameter =new Object[endRow-startRow+1][endCell-startCell+1];
        try {
            Workbook workbook =WorkbookFactory.create(new File(filepath)); //获取Workbook对象
            Sheet sheet = workbook.getSheet(sheetname);//获取sheet对象
            for (int i = startRow; i <= endRow; i++) {//循环拿到所需参数的行
                Row row = sheet.getRow(i-1);//获取行
                for (int j = startCell; j < endCell; j++) {//循环获取行上的数据
                    // 获取列，处理空单元格数据，把空的单元格数据也拿到
                    Cell cell = row.getCell(j-1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);//将单元格数据格式都定义为String
                    String value = cell.getStringCellValue();//拿到单元格数据复制给value
                    parameter[i-startRow][j-startCell]=value;//将拿到的数据放到数组中
            } }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }
    //读取指定行的数据，定义两个数组（指定行，指定列），拿到数组里指定行指定列的数据，返回一个Object的二维数组
    public static Object[][] parameterData(String filepath, String sheetname,int Rows[],int Cell[]){
        Object[][] parameter =new Object[Rows.length][Cell.length];
        try {
            Workbook workbook =WorkbookFactory.create(new File(filepath)); //获取Workbook对象
            Sheet sheet = workbook.getSheet(sheetname);//获取sheet对象
            for (int i = 0; i <Rows.length; i++) {//循环拿到所需参数的行
                Row row = sheet.getRow(Rows[i]-1);//获取行
                for (int j =0; j < Cell.length; j++) {//循环获取行上的数据
                    // 获取列，处理空单元格数据，把空的单元格数据也拿到
                    Cell cell = row.getCell(Cell[j]-1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);//将单元格数据格式都定义为String
                    String value = cell.getStringCellValue();//拿到单元格数据复制给value
                    parameter[i][j]=value;//将拿到的数据放到数组中
                } }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }
    /**@param filepath 待读取数据所在文件的路径
     * @param sheetname 带读取数据所在的sheet名
     * @param clazz 待封装对象的字节码
     * 读取excel文件中一个sheet的数据，将数据封装到一个对象里面，在将对象添加到一个泛型集合中，最后返回这个泛型集合
     */

    public static <T> List<T> load(String filepath,String sheetname,Class<T> clazz)  {
        List<T> lists=new ArrayList<T>();//定义一个泛型的数组
        try {
            Workbook workbook= WorkbookFactory.create(new File(filepath));
            Sheet sheet= workbook.getSheet(sheetname);
            Row titleRow= sheet.getRow(0);
            int cellNum= titleRow.getLastCellNum();
            String[] titleDatas=new String[cellNum];
            for(int i=0;i<cellNum;i++){
                Cell cell =titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String value= cell.getStringCellValue();
                value=value.substring(0,value.indexOf("("));//获取列头的编号
                titleDatas[i]=value; }

            int rowNum= sheet.getLastRowNum();//获取最后一行的行数
            for (int i = 1; i <=rowNum ; i++) {
                T obj= clazz.newInstance();//返回一个泛型的对象
                Row row=sheet.getRow(i);//从第二列开始获取行号
                for (int j = 0; j <cellNum ; j++) {
                    Cell cell=row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value=cell.getStringCellValue();//获取每一行的数据
                    String methodName="set"+titleDatas[j];//拼接拿到Case类里的set方法
                    Method method=clazz.getMethod(methodName,String.class);//反射获取Case类的set方法
                    method.invoke(obj,value);//调用Case类的Set方法存入值
                }
                lists.add(obj);//将赋值过后的对象保存到泛型数组中

            }
        } catch (Exception e) {
            e.printStackTrace();
        }return lists;
    }
    public static void loadSheet( String filePath,String sheetName){
        InputStream inputStream=null;
        try {
            inputStream=new FileInputStream(filePath);//创建一个输入流对象
            Workbook workbook = WorkbookFactory.create(inputStream);//传入输入流创建workbook对象，拿到要读取的excel
            Sheet sheet= workbook.getSheet(sheetName);//获取需要读取的sheet
            Row titleRow= sheet.getRow(0);//获取第一行
            int cellNum= titleRow.getLastCellNum();//获取第一行有多少数据
            for (int i = 0; i <cellNum ; i++) {
                //循环获取第一行每一列的列头的数据
                Cell cell= titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String title= cell.getStringCellValue();//获取到每一列列头的数据
                title=title.substring(0,title.indexOf("("));//将列头的数据从"("开始截取
                int cellIndex= cell.getAddress().getColumn();//获取列头所在列的索引
                CaseUtils.cellNumAndIndex.put(title,cellIndex);//将截取的列头和索引以键值对形式保存到数组中
            }
            int RowNum=sheet.getLastRowNum();//获取sheet中最后一行的行号
            for (int i = 1; i <=RowNum ; i++) {//循环取出每一行的行号
                Row row=sheet.getRow(i);
                Cell cell= row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取第一列的cell
                cell.setCellType(CellType.STRING);
                String rowIdentify= cell.getStringCellValue();//获取每一行的一列的值
                int rowIndex= row.getRowNum();//获取当前行的行号
                CaseUtils.RowNumAndIndex.put(rowIdentify,rowIndex);//将每行第一列的行号和每行第一列的值保存到map中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


