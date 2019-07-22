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
import com.kgate.model.Employee;
import com.kgate.model.TaskDTO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring container
        List<TaskDTO> listProject = (List<TaskDTO>) model.get("listProject");

        doc.add(new Paragraph("List of Task Details  in PDF"));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{2.0f, 3.5f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f});
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

        cell.setPhrase(new Phrase("Manager Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Task Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Task Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Task Start Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Task End Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);

        for (TaskDTO dTO : listProject) {
            table.addCell(dTO.getEmp_name());
            table.addCell(dTO.getName());
            table.addCell(dTO.getTask_Name());
            table.addCell(dTO.getTask_Type());
            table.addCell(dTO.gettStartDate());
            table.addCell(dTO.gettEndDate());
            table.addCell(dTO.getStatus());

        }

        doc.add(table);

    }
}
