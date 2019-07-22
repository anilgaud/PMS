/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kgate.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kgate.model.TaskDTO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ProjectOverviewBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring container
        List<TaskDTO> listProject = (List<TaskDTO>) model.get("listProject");

        doc.add(new Paragraph("List of  Employee Details  in PDF"));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{2.0f, 3.5f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header 
        cell.setPhrase(new Phrase("Employee Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Expected Days", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Completed Days", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Deviation Days", font));
        table.addCell(cell);

        for (TaskDTO dTO : listProject) {
            table.addCell(dTO.getEmp_name());
            table.addCell(dTO.getEstimateDays());
            table.addCell(dTO.getActualDays());
            table.addCell(dTO.getDelayDays());

        }

        doc.add(table);

    }
}
