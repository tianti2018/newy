package com.tw.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tw.web.dao.TixianDAO;
import com.tw.web.hibernate.persistent.Tixian;



@SuppressWarnings("serial")
@Scope("prototype")
@Controller("DownLoadExcelAction")
public class DownLoadExcelAction extends ExtJSONActionSuport {
		
	private TixianDAO tixianDAO;
	

	public TixianDAO getTixianDAO() {
		return tixianDAO;
	}
	@Autowired
	public void setTixianDAO(TixianDAO tixianDAO) {
		this.tixianDAO = tixianDAO;
	}

	public String init() throws Exception {    
				
		 HttpServletResponse response = ServletActionContext.getResponse();
		 Map<String,Object> conditionProperties = new HashMap<String, Object>();
		 conditionProperties.put("type", "0");
		 
		 Map<String,Integer> compare = new HashMap<String, Integer>();
		 compare.put("type", 0);
		 
		 Map<String,Boolean> sort = new HashMap<String, Boolean>();
		 sort.put("appDate", false);
		
		 List<Tixian> litAll = tixianDAO.findAllPagerList_new(conditionProperties, compare, sort, 0, 0, "all");
		 
	     HSSFWorkbook workbook = exportExcel(litAll);    
	     if(workbook!= null){    
	    	 String fileName = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".xls";
	         this.printExcel(workbook,response,fileName);    
	     }    
       
	     return null;    
 	}    

	//导出Excel
	private void printExcel(HSSFWorkbook workbook,HttpServletResponse response, String fileName) throws IOException {
    OutputStream out = response.getOutputStream();
    
    System.out.println(" >>>>>>>>>>>>>>>>>>>>>  "+fileName);
    
    response.addHeader("Content-Disposition","attachment;filename=\""+fileName + "\"");    
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    workbook.write(out);
    out.flush();
    out.close();
	
	}
			
	public HSSFWorkbook exportExcel(List dataList) throws Exception {
    HSSFWorkbook workbook = null;
    
    try {
      // 这里的数据即时你要从后台取得的数据
      
      // 创建工作簿实例
      workbook = new HSSFWorkbook();
      // 创建工作表实例
      HSSFSheet sheet = workbook.createSheet("TscExcel");
      // 设置列宽
      this.setSheetColumnWidth(sheet);
      // 获取样式
      HSSFCellStyle style = this.createTitleStyle(workbook);
      
     //
     if (dataList != null && dataList.size() > 0) {
        // 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
        HSSFRow row = sheet.createRow((short) 0);// 建立新行
        
        this.createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "会员编号");
        this.createCell(row, 1, style, HSSFCell.CELL_TYPE_STRING, "登录名称");
        this.createCell(row, 2, style, HSSFCell.CELL_TYPE_STRING, "手机号码"); //显示的修正嫌疑度
        this.createCell(row, 3, style, HSSFCell.CELL_TYPE_STRING, "银行名称");
        this.createCell(row, 4, style, HSSFCell.CELL_TYPE_STRING, "开户行地址");
        this.createCell(row, 5, style, HSSFCell.CELL_TYPE_STRING, "银行账户");
        this.createCell(row, 6, style, HSSFCell.CELL_TYPE_STRING, "账户持有人姓名"); 
        this.createCell(row, 7, style, HSSFCell.CELL_TYPE_STRING, "体现金额");
        
        // 给excel填充数据
        for(int i=0;i<dataList.size();i++) {
           Tixian tixian = (Tixian)dataList.get(i);
            HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行                          
            
            Integer userId = tixian.getUser().getUserId();
            String loginName = tixian.getUser().getLoginName();
            String phone = tixian.getUser().getPhone();
            String bankName = tixian.getBankName();
            String openBankAddr = tixian.getOpenBankAddr();
            String account = tixian.getAccount();
            String accountHolder = tixian.getAccountHolder();
            Double tixianMoney = tixian.getTixianMoney()*0.95;
            
            if (null!=userId ||userId!=0) {
            	this.createCell(row1, 0, style, HSSFCell.CELL_TYPE_STRING, userId);
            }
            
            if (isNullorEmpty(loginName)) {
            	this.createCell(row1, 1, style, HSSFCell.CELL_TYPE_STRING, loginName);
            }
            if (isNullorEmpty(phone)) {
            	this.createCell(row1, 2, style, HSSFCell.CELL_TYPE_STRING, phone);
            }
            if (isNullorEmpty(bankName)) {
            	this.createCell(row1, 3, style, HSSFCell.CELL_TYPE_STRING, bankName);
            }
            
            if (isNullorEmpty(openBankAddr)) {
            	this.createCell(row1, 4, style, HSSFCell.CELL_TYPE_STRING, openBankAddr);
            }
            if (isNullorEmpty(account)) {
            	this.createCell(row1, 5, style, HSSFCell.CELL_TYPE_STRING, account);
            }
            if (isNullorEmpty(accountHolder)) {
            	this.createCell(row1, 6, style, HSSFCell.CELL_TYPE_STRING, accountHolder);
            }  
            if (null!=tixianMoney || 0.0!=tixianMoney) {
            	this.createCell(row1, 7, style, HSSFCell.CELL_TYPE_STRING, tixianMoney);
            }  
         }
	   }
       else {
           this.createCell(sheet.createRow(0), 0, style,
           HSSFCell.CELL_TYPE_STRING, "查无资料");
       }
   	 }
	 catch(Exception e){
       e.printStackTrace();
    }
    	 
    	return workbook;
  }
	
	public boolean isNullorEmpty(Object obj) {
		if (obj instanceof String) {
			if (StringUtils.isNotEmpty(String.valueOf(obj))&&(obj!=null)) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (obj instanceof Date) {
			if (obj != null) {
				return true;
			}
			else {
				return false;
			}			
		}
		
		else if (obj instanceof Integer) {
			if (obj != null) {
				return true;
			}
			else {
				return false;
			}			
		}
		return false;
		
	}
	
  private void setSheetColumnWidth(HSSFSheet sheet){
          // 根据你数据里面的记录有多少列，就设置多少列
          sheet.setColumnWidth(0, 3000);
          sheet.setColumnWidth(1, 3000);
          sheet.setColumnWidth(2, 5000);
          sheet.setColumnWidth(3, 8000);
          sheet.setColumnWidth(4, 8000);
          sheet.setColumnWidth(5, 8000);
          sheet.setColumnWidth(6, 5000);
          sheet.setColumnWidth(7, 8000);
          
  }
  
  // 设置excel的title样式
  private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {
     HSSFFont boldFont = wb.createFont();
     boldFont.setFontHeight((short) 200);
     HSSFCellStyle style = wb.createCellStyle();
     style.setFont(boldFont);
     style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
     return style;
  }
  // 创建Excel单元格
  private void createCell(HSSFRow row, int column, HSSFCellStyle style,
    int cellType,Object value) {
    HSSFCell cell = row.createCell(column);
    cell.setCellType(HSSFCell.ENCODING_UTF_16);
     if (style != null) {
        cell.setCellStyle(style);
    } 
    switch (cellType) {
      case HSSFCell.CELL_TYPE_BLANK: {}
      break;
      case HSSFCell.CELL_TYPE_STRING: {
      cell.setCellValue(new HSSFRichTextString(String.valueOf(value)));
      }
      break;
      case HSSFCell.CELL_TYPE_NUMERIC: {
      	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);      	
      	cell.setCellValue(Double.parseDouble(value.toString()));
      }
      break;
		  default:
		     break;
		 }
   }
	
  public static void main (String args[]) {
  	System.out.println(StringUtils.replace("@@@@231432423", "@", "#"));
  }
}
