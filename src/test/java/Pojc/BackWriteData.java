package Pojc;

import org.testng.annotations.Test;

public class BackWriteData {
    private String RowIdentify;
    private String sheetName;
    private String result;
    private String cellName;

    public String getRowIdentify() {
        return RowIdentify;
    }

    public void setRowIdentify(String caseId) {
        RowIdentify = caseId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    @Test
    public void testName() {
    }

    public BackWriteData(String rowIdentify, String sheetName, String result, String cellName) {
        this.RowIdentify = rowIdentify;
        this.sheetName = sheetName;
        this.result = result;
        this.cellName = cellName;
    }
}
