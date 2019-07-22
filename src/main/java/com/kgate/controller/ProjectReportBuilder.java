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
import com.kgate.model.ProjectDetails;
import com.kgate.model.ProjectReportDTO;
import com.kgate.model.TaskDTO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ProjectReportBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
//        List<ProjectDetails> listProject = (List<ProjectDetails>) model.get("listProject");
        List<ProjectReportDTO> listProject = (List<ProjectReportDTO>) model.get("listProject");
        doc.add(new Paragraph("List of  Project Details  in PDF"));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell(); 
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header 
        cell.setPhrase(new Phrase("Project Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Start Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("End Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Project Status", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Expected Days", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Completed Days", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Deviation Days", font));
        table.addCell(cell);

        for (ProjectReportDTO dTO : listProject) {
            table.addCell(dTO.getProject_name());
            table.addCell(dTO.getpStartDate());
            table.addCell(dTO.getpEndDate());
            table.addCell(dTO.getProStatus());
            table.addCell(dTO.getExpectDate());
            table.addCell(dTO.getCompleteTime());
            table.addCell(dTO.getVariationTime());

        }

        doc.add(table);

    }
}
