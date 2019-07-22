package com.kgate.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kgate.model.TaskDTO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectStatusBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<TaskDTO> listProject = (List<TaskDTO>) model.get("listProject");
        doc.add(new Paragraph("List of  Project Details  in PDF"));

        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f});
        table.setSpacingBefore(8);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);
        font.setSize(10);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(3);

        // write table header 
        cell.setPhrase(new Phrase("Employee Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Task Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Task Type", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Start Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("End Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Project Status", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Submit Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Estimate Days", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Actual Days", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Delay Days", font));
        table.addCell(cell);
//        table.addCell(getNormalCell);

        for (TaskDTO dTO : listProject) {
//            table.addCell(dTO.getEmp_name());
            table.addCell(new Phrase(dTO.getEmp_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.getTask_Name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.getTask_Type(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.gettStartDate(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.gettEndDate(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.getStatus(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.getTsubDate(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.getEstimateDays(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.getActualDays(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            table.addCell(new Phrase(dTO.getDelayDays(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));

        }

        doc.add(table);

    }

}
