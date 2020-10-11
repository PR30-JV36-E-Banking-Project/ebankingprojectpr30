/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class RecieptService {

    

    public void createPdf() throws IOException{
        URL font_path = Thread.currentThread().getContextClassLoader().getResource("arial-unicode-ms.ttf");
        FontFactory.register(font_path.toString(), "Arial Unicode MS");
//        System.out.println(font_path.toString());
//        Font myfont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Font yellowFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("StylingExample.pdf"));
            document.open();
            //document.add(new Paragraph("Styling Example"));

            //Paragraph with color and font styles
            Paragraph paragraphOne = new Paragraph("Some colored paragraph text Thái Ngọc Tâm nguyễn thị linh", blueFont);
            document.add(paragraphOne);
            Paragraph paragraphOne1 = new Paragraph("Some colored paragraph text Thái Ngọc Tâm", blueFont);
            document.add(paragraphOne1);
            Paragraph paragraphOne2 = new Paragraph("Some colored paragraph text Thái Ngọc Tâm", redFont);
            document.add(paragraphOne2);

            //Create chapter and sections
            Paragraph chapterTitle = new Paragraph("Chapter Title ", yellowFont);
            Chapter chapter1 = new Chapter(chapterTitle, 1);
            chapter1.setNumberDepth(0);

            Paragraph sectionTitle = new Paragraph("Section Title", redFont);
            Section section1 = chapter1.addSection(sectionTitle);

            Paragraph sectionContent = new Paragraph("Section Text content", blueFont);
            section1.add(sectionContent);

            document.add(chapter1);

            document.close();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
