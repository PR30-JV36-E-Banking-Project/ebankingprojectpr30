/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.TransactionEntity;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class RecieptService {

    public void createPdf(TransactionEntity transactionE, HttpServletResponse response) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader.getResource(".").getPath().replace("/classes", "").replace("target/", ""));
        String imgUrl = classLoader.getResource(".").getPath().replace("/classes", "").replace("target/", "").substring(1)
                + "src/main/webapp/resources/fonts/arial-unicode-ms.ttf";
        String img = classLoader.getResource(".").getPath().replace("/classes", "").replace("target/", "").substring(1)
                + "src/main/webapp/resources/images/logo.png";
        FontFactory.register(imgUrl, "Arial Unicode MS");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Font myfont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(255, 0, 0, 0));
        Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Font yellowFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();
            Image image1 = Image.getInstance(img);
            image1.setAlignment(Element.ALIGN_LEFT);
            image1.setBorderWidth(0);
            image1.scaleAbsolute(150, 50);
//            document.add(image1);
            Paragraph paragraph1 = new Paragraph("Reciept");
            paragraph1.setFont(myfont);
//            document.add(paragraph1);
            PdfPTable table = new PdfPTable(4); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            PdfPCell celllogo = new PdfPCell(image1);
            celllogo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celllogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            celllogo.setBorderWidth(0);
            celllogo.setBorderColor(BaseColor.WHITE);
            celllogo.setColspan(1);
            PdfPCell celltittle = new PdfPCell(new Paragraph("Reciept", blueFont));
            celltittle.setHorizontalAlignment(Element.ALIGN_CENTER);
            celltittle.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celltittle.setBorderWidth(0);
            celltittle.setColspan(3);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Ngày, giờ giao dịch", myfont));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setFixedHeight(30);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell1CT = new PdfPCell(new Paragraph(transactionE.getTransactionDate().toString(), myfont));
            cell1CT.setBorderColor(BaseColor.BLUE);
            cell1CT.setBorderColorRight(BaseColor.WHITE);
            cell1CT.setPaddingLeft(10);
            cell1CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1CT.setColspan(3);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Số lệnh giao dịch", myfont));
            cell2.setBorderColor(BaseColor.BLUE);
            cell2.setPaddingLeft(10);
            cell2.setFixedHeight(30);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell2CT = new PdfPCell(new Paragraph(Integer.toString(transactionE.getTransactionID()), myfont));
            cell2CT.setBorderColor(BaseColor.BLUE);
            cell2CT.setPaddingLeft(10);
            cell2CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2CT.setColspan(3);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Tài Khoản nguồn", myfont));
            cell3.setBorderColor(BaseColor.BLUE);
            cell3.setPaddingLeft(10);
            cell3.setFixedHeight(30);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell3CT = new PdfPCell(new Paragraph(Double.toString(transactionE.getSenderAccount().getAccountID()), myfont));
            cell3CT.setBorderColor(BaseColor.BLUE);
            cell3CT.setPaddingLeft(10);
            cell3CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell31 = new PdfPCell(new Paragraph("Số tiền trích nợ", myfont));
            cell31.setBorderColor(BaseColor.BLUE);
            cell31.setPaddingLeft(10);
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell31CT = new PdfPCell(new Paragraph(String.valueOf(transactionE.getAmount()), myfont));
            cell31CT.setBorderColor(BaseColor.BLUE);
            cell31CT.setPaddingLeft(10);
            cell31CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Tài Khoản ghi", myfont));
            cell4.setBorderColor(BaseColor.BLUE);
            cell4.setPaddingLeft(10);
            cell4.setFixedHeight(30);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell4CT = new PdfPCell(new Paragraph(String.valueOf(transactionE.getReceiverAccount().getAccountID()), myfont));
            cell4CT.setBorderColor(BaseColor.BLUE);
            cell4CT.setPaddingLeft(10);
            cell4CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell41 = new PdfPCell(new Paragraph("Số tiền ghi", myfont));
            cell41.setBorderColor(BaseColor.BLUE);
            cell41.setPaddingLeft(10);
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell41CT = new PdfPCell(new Paragraph(String.valueOf(transactionE.getAmount()), myfont));
            cell41CT.setBorderColor(BaseColor.BLUE);
            cell41CT.setPaddingLeft(10);
            cell41CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell41CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Tên người hưởng", myfont));
            cell5.setBorderColor(BaseColor.BLUE);
            cell5.setPaddingLeft(10);
            cell5.setFixedHeight(30);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell5CT = new PdfPCell(new Paragraph(transactionE.getReceiverAccount().getCustomerEntity().getFullName(), myfont));
            cell5CT.setBorderColor(BaseColor.BLUE);
            cell5CT.setPaddingLeft(10);
            cell5CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5CT.setColspan(3);

            PdfPCell cell6 = new PdfPCell(new Paragraph("Tên ngân hàng", myfont));
            cell6.setBorderColor(BaseColor.BLUE);
            cell6.setPaddingLeft(10);
            cell6.setFixedHeight(30);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell6CT = new PdfPCell(new Paragraph(transactionE.getSenderAccount().getBankEntity().getBankName(), myfont));
            cell6CT.setBorderColor(BaseColor.BLUE);
            cell6CT.setPaddingLeft(10);
            cell6CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6CT.setColspan(3);

            PdfPCell cell7 = new PdfPCell(new Paragraph("Loại phí", myfont));
            cell7.setBorderColor(BaseColor.BLUE);
            cell7.setPaddingLeft(10);
            cell7.setFixedHeight(30);
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell7CT = new PdfPCell(new Paragraph("Cell 2", myfont));
            cell7CT.setBorderColor(BaseColor.BLUE);
            cell7CT.setPaddingLeft(10);
            cell7CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell81 = new PdfPCell(new Paragraph("Số tiền Phí", myfont));
            cell81.setBorderColor(BaseColor.BLUE);
            cell81.setPaddingLeft(10);
            cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell81.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell81CT = new PdfPCell(new Paragraph("5,000 VND", myfont));
            cell81CT.setBorderColor(BaseColor.BLUE);
            cell81CT.setPaddingLeft(10);
            cell81CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell81CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(celllogo);
            table.addCell(celltittle);
            table.addCell(cell1);
            table.addCell(cell1CT);
            table.addCell(cell2);
            table.addCell(cell2CT);
            table.addCell(cell3);
            table.addCell(cell3CT);
            table.addCell(cell31);
            table.addCell(cell31CT);
            table.addCell(cell4);
            table.addCell(cell4CT);
            table.addCell(cell41);
            table.addCell(cell41CT);
            table.addCell(cell5);
            table.addCell(cell5CT);
            table.addCell(cell6);
            table.addCell(cell6CT);
            table.addCell(cell7);
            table.addCell(cell7CT);

            table.addCell(cell81);
            table.addCell(cell81CT);

            document.add(table);
            document.close();
//            writer.close();
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
