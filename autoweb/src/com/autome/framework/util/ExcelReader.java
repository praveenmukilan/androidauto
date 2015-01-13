package com.autome.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
  private Cell openCell;
  private Row openRow;
  private Sheet openSheet;
  private Workbook openWorkbook;
  public static int noOfSheet = 0;
  private Map<String, List<String>> storedData = new LinkedHashMap();
  
  public ExcelReader(String filePath)
    throws FileNotFoundException, IOException
  {
    this(new File(filePath));
  }
  
  public ExcelReader(File file)
    throws IOException, FileNotFoundException
  {
    openFile(file, 0);
  }
  
  public ExcelReader(String filePath, int sheetNo)
    throws IOException, FileNotFoundException
  {
    openFile(filePath, sheetNo);
  }
  
  public ExcelReader(String filePath, String sheetName)
    throws IOException, FileNotFoundException
  {
    openFile(filePath, sheetName);
  }
  
  public ExcelReader(File file, int sheetNo)
    throws IOException, FileNotFoundException
  {
    openFile(file, sheetNo);
  }
  
  public ExcelReader(File file, String sheetName)
    throws IOException, FileNotFoundException
  {
    openFile(file, sheetName);
  }
  
  public ExcelReader() {}
  
  public void openFile(File file, int sheetNo)
    throws IOException, FileNotFoundException
  {
    openWorkbook(file);
    this.openSheet = this.openWorkbook.getSheetAt(sheetNo);
  }
  
  public void openFile(String filePath, int sheetNo)
    throws FileNotFoundException, IOException
  {
    openFile(new File(filePath), sheetNo);
  }
  
  public void openFile(File file, String sheetName)
    throws FileNotFoundException, IOException
  {
    openWorkbook(file);
    this.openSheet = this.openWorkbook.getSheet(sheetName);
  }
  
  public void openFile(String filePath, String sheetName)
    throws FileNotFoundException, IOException
  {
    openWorkbook(filePath);
    this.openSheet = this.openWorkbook.getSheet(sheetName);
  }
  
  private void openWorkbook(String filePath)
    throws FileNotFoundException, IOException
  {
    openWorkbook(new File(filePath));
  }
  
  private void openWorkbook(File file)
    throws FileNotFoundException, IOException
  {
    try
    {
      InputStream inp = new FileInputStream(file);
      if (isXlsx(file)) {
        this.openWorkbook = new XSSFWorkbook(inp);
      } else {
        this.openWorkbook = new HSSFWorkbook(inp);
      }
    }
    catch (FileNotFoundException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }
  }
  
  public void openSheet(int sheetNo)
  {
    this.openSheet = this.openWorkbook.getSheetAt(sheetNo);
  }
  
  public void openSheet(String sheetName)
  {
    this.openSheet = this.openWorkbook.getSheet(sheetName);
  }
  
  public Workbook getOpenWorkbook()
  {
    return this.openWorkbook;
  }
  
  public String getData(int row, int column)
  {
    String data = "";
    try
    {
      this.openRow = this.openSheet.getRow(row);
      this.openCell = this.openRow.getCell(column);
      int cellType = this.openCell.getCellType();
      switch (cellType)
      {
      case 0: 
        if (DateUtil.isCellDateFormatted(this.openCell))
        {
          Date dt = this.openCell.getDateCellValue();
          SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
          
          data = sdf.format(dt);
        }
        else
        {
          data = Long.toString(Math.round(this.openCell.getNumericCellValue()));
        }
        break;
      case 1: 
        data = this.openCell.getRichStringCellValue().getString();
        break;
      case 2: 
        data = this.openCell.getRichStringCellValue().getString();
        break;
      case 3: 
        data = this.openCell.getRichStringCellValue().getString();
        break;
      case 4: 
        data = Boolean.toString(this.openCell.getBooleanCellValue());
        break;
      case 5: 
        data = Byte.toString(this.openCell.getErrorCellValue());
        break;
      default: 
        data = this.openCell.getRichStringCellValue().getString();
      }
      if (data == null) {}
      return "";
    }
    catch (Exception e)
    {
      if ((this.openRow == null) || (this.openCell == null) || (data == null)) {
        return "";
      }
      System.out.println(e);
    }
    return "";
  }
  
  public int getNoOfRows()
  {
    return this.openSheet.getPhysicalNumberOfRows();
  }
  
  public int getNoOfColumn()
  {
    return getNoOfColumn(0);
  }
  
  public int getNoOfColumn(int rowNo)
  {
    Row rw = this.openSheet.getRow(rowNo);
    return rw.getPhysicalNumberOfCells();
  }
  
  public void storeData()
  {
    int rowCount = this.openSheet.getPhysicalNumberOfRows();
    this.storedData.clear();
    for (int i = 1; i < rowCount; i++)
    {
      Row rw = this.openSheet.getRow(i);
      String key = getData(0, i);
      
      List<String> valueList = new ArrayList();
      this.storedData.put(key, valueList);
      for (int j = 1; j <= rw.getPhysicalNumberOfCells(); j++)
      {
        String data = getData(j, i);
        valueList.add(data);
      }
    }
  }
  
  public Map<String, List<String>> getStoredData()
  {
    if (this.storedData.isEmpty()) {
      storeData();
    }
    return this.storedData;
  }
  
  private boolean isXlsx(File fl)
  {
    String fileName = fl.getName();
    if (fileName.endsWith("xlsx")) {
      return true;
    }
    return false;
  }
}


