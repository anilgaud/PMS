package com.kgate.controller;

import com.kgate.model.Employee;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Employee> listEmployee = (List<Employee>) model.get("listEmployee");
        HSSFSheet sheet = workbook.createSheet("Employee List");
        HSSFRow fRow = sheet.createRow(0);
        fRow.createCell(0).setCellValue("Name");
        fRow.createCell(1).setCellValue("Email");
        fRow.createCell(2).setCellValue("Address");
        fRow.createCell(3).setCellValue("Telephone");

        int rowNum = 1;

        for (Employee aEmployee : listEmployee) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(aEmployee.getName());
            row.createCell(1).setCellValue(aEmployee.getEmail());
            row.createCell(2).setCellValue(aEmployee.getAddress());
            row.createCell(3).setCellValue(aEmployee.getTelephone());

        }
    }

}
