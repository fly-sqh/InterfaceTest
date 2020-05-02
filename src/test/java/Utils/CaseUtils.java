package Utils;

import Cases.BaseFluga;
import Pojc.BackWriteData;
import Pojc.Case;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Case工具类
 */

public class CaseUtils {
    public static Map<String, Integer> cellNumAndIndex = new HashMap<String, Integer>();
    public static Map<String, Integer> RowNumAndIndex = new HashMap<String, Integer>();
    public static List<Case> cases = new ArrayList<Case>();

    static {
        //调用load方法把excel文件里的值封装到Case对象里
        cases= ExcelUtils.load(BaseFluga.filePath, "用例", Case.class);
    }
    public static Object[][] data(String ApiId, String[] cellName) {
        //拿到想要的ApiId和需要的取出的列的值，返回一个二维数组，为接口测试提供数据驱动
        Class<Case> clazz = Case.class;
        ArrayList<Case> csList = new ArrayList<Case>();
        for (Case cs : cases) {
            if (cs.getApiId().equals(ApiId)) {
                csList.add(cs);
            }
        }
        Object[][] a = new Object[csList.size()][cellName.length];//[7][2]
        for (int i = 0; i < csList.size(); i++) {
            Case se = csList.get(i);
            for (int j = 0; j < cellName.length; j++) {
                String methodName = "get" + cellName[j];
                try {
                    Method method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(se);
                    a[i][j] = value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return a;
    }
//回写数据实现
    public static void BlackWriteResult(String result, String cellName, String filePath, String sheetName, String caseId) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            int rowNum = RowNumAndIndex.get(caseId);
            int cellNum = cellNumAndIndex.get(cellName);
            inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(result);
            outputStream = new FileOutputStream(new File(filePath));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 批量回写数据
     * @param filePath 需要写入数据的文件路劲
     */
    public static void BatchWriteData(String filePath){
        InputStream inputStream= null;
        OutputStream outputStream=null;
        try {
            inputStream = new FileInputStream(new File(filePath));//传入输入流
            Workbook workbook= WorkbookFactory.create(inputStream);//创建一个workbook对象
            for (BackWriteData backWriteData:ExcelUtils.backWriteDatas){
                //循环backWriteDatas这个对象数组，拿到里面的每个对象
                 Sheet sheet= workbook.getSheet(backWriteData.getSheetName());//获取需要操作的sheet
                 int rowNum= RowNumAndIndex.get(backWriteData.getRowIdentify());//从数组中取出需要写入数据的行号
                 Row row= sheet.getRow(rowNum);//获取需要写入数据的行
                 int cellNum=cellNumAndIndex.get(backWriteData.getCellName());//获取需要写入数据的列号
                 Cell cell=row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取需要写入数据的列
                 cell.setCellType(CellType.STRING);
                 cell.setCellValue(backWriteData.getResult());//在对应行对应列中写入数据
            }
            outputStream=new FileOutputStream(new File(filePath));//传入需要写入数据的文件并创建输出流
            workbook.write(outputStream);//写入数据并保存文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//关闭输出流对象和输入流对象
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream!=null){
                inputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
