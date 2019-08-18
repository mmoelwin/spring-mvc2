package com.demo.springmvc.controller;

import com.demo.springmvc.model.Category;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
@Component
public class CategoriesPdf {
    public static ByteArrayInputStream categoryPdfViews(List<Category> categories){

        ByteArrayOutputStream out=new ByteArrayOutputStream();

        Document document=new Document();

        try {

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{1,3});

            PdfPCell hcell;
            Font font= FontFactory.getFont(FontFactory.HELVETICA);

            hcell=new PdfPCell(new Phrase("Id",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Name",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for(Category category:categories){
                PdfPCell cell;

                cell=new PdfPCell(new Phrase(category.getId().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(String.valueOf(category.getName())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document,out);
            document.open();

            document.add(table);

            document.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return  new ByteArrayInputStream(out.toByteArray());
    }
}
