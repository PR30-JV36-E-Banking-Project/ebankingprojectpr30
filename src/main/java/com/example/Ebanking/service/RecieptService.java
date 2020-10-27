/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.AccountEntity;
import com.example.Ebanking.entities.TransactionEntity;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class RecieptService extends HttpServlet {

    @Autowired
    FormatDouble fd;

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String imgUrl = classLoader.getResource(".").getPath().replace("/classes", "").replace("target/", "").substring(1)
            + "src/main/webapp/resources/fonts/arial-unicode-ms.ttf";
    String img = classLoader.getResource(".").getPath().replace("/classes", "").replace("target/", "").substring(1)
            + "src/main/webapp/resources/images/logo.png";

    public void createPdf(TransactionEntity transactionE, HttpServletResponse response) throws IOException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        String imgUrl = classLoader.getResource(".").getPath().replace("/classes", "").replace("target/", "").substring(1)
//                + "src/main/webapp/resources/fonts/arial-unicode-ms.ttf";
//        String img = classLoader.getResource(".").getPath().replace("/classes", "").replace("target/", "").substring(1)
//                + "src/main/webapp/resources/images/logo.png";
        FontFactory.register(imgUrl, "Arial Unicode MS");
        Font normalFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 13, Font.NORMAL);
        Font tittleFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 18);
        Font subfont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 9, Font.ITALIC);
        Font subfont2 = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 13, Font.ITALIC);
        Font blackFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(255, 255, 255, 255));
        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();

            Image image1 = Image.getInstance(img);
            image1.setAlignment(Element.ALIGN_LEFT);
            image1.setBorderWidth(0);
            image1.scaleAbsolute(150, 50);

            PdfPTable table = new PdfPTable(4); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1.3f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            PdfPCell celllogo = new PdfPCell(image1);
            celllogo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celllogo.setVerticalAlignment(Element.ALIGN_TOP);
            celllogo.setBorderWidth(0);
            celllogo.setFixedHeight(60);
            celllogo.setColspan(1);

            Paragraph tittle = new Paragraph("BIÊN LAI CHUYỂN TIỀN", tittleFont);
            tittle.setIndentationLeft(80);
            Paragraph subTittle = new Paragraph("(Payment Reciept)", subfont);
            subTittle.setIndentationLeft(150);
            PdfPCell celltittle = new PdfPCell();
            celltittle.addElement(tittle);
            celltittle.addElement(subTittle);
            celltittle.setBorderWidth(0);
            celltittle.setColspan(3);

            Paragraph tittle1 = new Paragraph("Ngày, giờ giao dịch", normalFont);
            tittle1.setIndentationLeft(10);
            Paragraph subTittle1 = new Paragraph("(Trans Date, Time)", subfont);
            subTittle1.setIndentationLeft(20);
            PdfPCell cell1 = new PdfPCell();
            cell1.addElement(tittle1);
            cell1.addElement(subTittle1);
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setFixedHeight(45);
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell1CT = new PdfPCell(new Paragraph(transactionE.getTransactionDate().toString(), normalFont));
            cell1CT.setBorderColor(BaseColor.BLACK);
            cell1CT.setBorderColorRight(BaseColor.WHITE);
            cell1CT.setPaddingLeft(10);
            cell1CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1CT.setColspan(3);

            Paragraph tittle2 = new Paragraph("Số lệnh giao dịch", normalFont);
            tittle2.setIndentationLeft(10);
            Paragraph subTittle2 = new Paragraph("(Order number)", subfont);
            subTittle2.setIndentationLeft(20);
            PdfPCell cell2 = new PdfPCell();
            cell2.addElement(tittle2);
            cell2.addElement(subTittle2);
            cell2.setBorderColor(BaseColor.BLACK);
            cell2.setPaddingLeft(10);
            cell2.setFixedHeight(45);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell2CT = new PdfPCell(new Paragraph(Integer.toString(transactionE.getTransactionID()), normalFont));
            cell2CT.setBorderColor(BaseColor.BLACK);
            cell2CT.setPaddingLeft(10);
            cell2CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2CT.setColspan(3);

            Paragraph tittle3 = new Paragraph("Tài Khoản nguồn", normalFont);
            tittle3.setIndentationLeft(10);
            Paragraph subTittle3 = new Paragraph("(Debit Account)", subfont);
            subTittle3.setIndentationLeft(20);
            PdfPCell cell3 = new PdfPCell();
            cell3.addElement(tittle3);
            cell3.addElement(subTittle3);
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setFixedHeight(45);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell3CT = new PdfPCell(new Paragraph(fd.fmID(transactionE.getSenderAccount().getAccountID()), normalFont));
            cell3CT.setBorderColor(BaseColor.BLACK);
            cell3CT.setPaddingLeft(10);
            cell3CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            Paragraph tittle31 = new Paragraph("Số tiền trích nợ", normalFont);
            tittle31.setIndentationLeft(8);
            Paragraph subTittle31 = new Paragraph("(Debit Amount)", subfont);
            subTittle31.setIndentationLeft(14);
            PdfPCell cell31 = new PdfPCell();
            cell31.addElement(tittle31);
            cell31.addElement(subTittle31);
            cell31.setBorderColor(BaseColor.BLACK);
            cell31.setPaddingLeft(10);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell31CT = new PdfPCell(new Paragraph(fd.fD(transactionE.getAmount()) + " VND", normalFont));
            cell31CT.setBorderColor(BaseColor.BLACK);
            cell31CT.setPaddingLeft(10);
            cell31CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            Paragraph tittle4 = new Paragraph("Tài Khoản ghi", normalFont);
            tittle4.setIndentationLeft(10);
            Paragraph subTittle4 = new Paragraph("(Credit Account)", subfont);
            subTittle4.setIndentationLeft(15);
            PdfPCell cell4 = new PdfPCell();
            cell4.addElement(tittle4);
            cell4.addElement(subTittle4);
            cell4.setBorderColor(BaseColor.BLACK);
            cell4.setPaddingLeft(10);
            cell4.setFixedHeight(45);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell4CT = new PdfPCell(new Paragraph(fd.fmID(transactionE.getReceiverAccount().getAccountID()), normalFont));
            cell4CT.setBorderColor(BaseColor.BLACK);
            cell4CT.setPaddingLeft(10);
            cell4CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            Paragraph tittle41 = new Paragraph("Số tiền ghi", normalFont);
            tittle41.setIndentationLeft(10);
            Paragraph subTittle41 = new Paragraph("(Credit Amount)", subfont);
            subTittle41.setIndentationLeft(15);
            PdfPCell cell41 = new PdfPCell();
            cell41.addElement(tittle41);
            cell41.addElement(subTittle41);
            cell41.setBorderColor(BaseColor.BLACK);
            cell41.setPaddingLeft(10);
            cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell41CT = new PdfPCell(new Paragraph(fd.fD(transactionE.getAmount()) + " VND", normalFont));
            cell41CT.setBorderColor(BaseColor.BLACK);
            cell41CT.setPaddingLeft(10);
            cell41CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell41CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            Paragraph tittle5 = new Paragraph("Tên người hưởng", normalFont);
            tittle5.setIndentationLeft(10);
            Paragraph subTittle5 = new Paragraph("(Beneficiary Name)", subfont);
            subTittle5.setIndentationLeft(13);
            PdfPCell cell5 = new PdfPCell();
            cell5.addElement(tittle5);
            cell5.addElement(subTittle5);
            cell5.setBorderColor(BaseColor.BLACK);
            cell5.setPaddingLeft(10);
            cell5.setFixedHeight(45);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell5CT = new PdfPCell(new Paragraph(transactionE.getReceiverAccount().getCustomerEntity().getFullName(), normalFont));
            cell5CT.setBorderColor(BaseColor.BLACK);
            cell5CT.setPaddingLeft(10);
            cell5CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5CT.setColspan(3);

            Paragraph tittle6 = new Paragraph("Tên ngân hàng hưởng", normalFont);
            tittle6.setIndentationLeft(10);
            Paragraph subTittle6 = new Paragraph("(Beneficiary Bank Name)", subfont);
            subTittle6.setIndentationLeft(17);
            PdfPCell cell6 = new PdfPCell();
            cell6.addElement(tittle6);
            cell6.addElement(subTittle6);
            cell6.setBorderColor(BaseColor.BLACK);
            cell6.setPaddingLeft(10);
            cell6.setFixedHeight(45);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell6CT = new PdfPCell(new Paragraph(transactionE.getSenderAccount().getBankEntity().getBankName(), normalFont));
            cell6CT.setBorderColor(BaseColor.BLACK);
            cell6CT.setPaddingLeft(10);
            cell6CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6CT.setColspan(3);

            Paragraph tittle7 = new Paragraph("Nội dung chuyển tiền", normalFont);
            tittle7.setIndentationLeft(10);
            Paragraph subTittle7 = new Paragraph("(Details of payment)", subfont);
            subTittle7.setIndentationLeft(17);
            PdfPCell cell7 = new PdfPCell();
            cell7.addElement(tittle7);
            cell7.addElement(subTittle7);
            cell7.setBorderColor(BaseColor.BLACK);
            cell7.setPaddingLeft(10);
            cell7.setFixedHeight(45);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell7CT = new PdfPCell(new Paragraph(transactionE.getContent(), normalFont));
            cell7CT.setBorderColor(BaseColor.BLACK);
            cell7CT.setPaddingLeft(10);
            cell7CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7CT.setColspan(3);

            Paragraph tittle8 = new Paragraph("Loại phí", normalFont);
            tittle8.setIndentationLeft(10);
            Paragraph subTittle8 = new Paragraph("(Charge code)", subfont);
            subTittle8.setIndentationLeft(10);
            PdfPCell cell8 = new PdfPCell();
            cell8.addElement(tittle8);
            cell8.addElement(subTittle8);
            cell8.setBorderColor(BaseColor.BLACK);
            cell8.setPaddingLeft(10);
            cell8.setFixedHeight(45);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell8CT = new PdfPCell(new Paragraph("Người chuyển trả", normalFont));
            cell8CT.setBorderColor(BaseColor.BLACK);
            cell8CT.setPaddingLeft(10);
            cell8CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8CT.setVerticalAlignment(Element.ALIGN_MIDDLE);

            Paragraph tittle81 = new Paragraph("Số tiền Phí", normalFont);
            tittle81.setIndentationLeft(10);
            Paragraph subTittle81 = new Paragraph("(Charge Amount)", subfont);
            subTittle81.setIndentationLeft(10);
            PdfPCell cell81 = new PdfPCell();
            cell81.addElement(tittle81);
            cell81.addElement(subTittle81);
            cell81.setBorderColor(BaseColor.BLACK);
            cell81.setPaddingLeft(10);
            cell81.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell81CT = new PdfPCell(new Paragraph("5,000 VND", normalFont));
            cell81CT.setBorderColor(BaseColor.BLACK);
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

            table.addCell(cell8);
            table.addCell(cell8CT);
            table.addCell(cell81);
            table.addCell(cell81CT);

            Paragraph thank = new Paragraph("Cảm ơn quý khách đã sử dụng dịch vụ của E-Banking!", subfont2);
            thank.setIndentationLeft(90);
            Paragraph thankEng = new Paragraph("(Thank you for banking with E-Banking!)", subfont);
            thankEng.setIndentationLeft(160);
            document.add(table);
            document.add(thank);
            document.add(thankEng);
            document.close();
            writer.close();
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

        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void pdfAccount(AccountEntity ae, HttpServletResponse response) throws IOException {
        FontFactory.register(imgUrl, "Arial Unicode MS");
        Font normalFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 13, Font.NORMAL);
        Font tittleFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 18);
        Font subfont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 9, Font.ITALIC);
        Font subfont2 = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 13, Font.ITALIC);
        Font blackFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new CMYKColor(255, 255, 255, 255));
        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();

            Image image1 = Image.getInstance(img);
            image1.setAlignment(Element.ALIGN_LEFT);
            image1.setBorderWidth(0);
            image1.scaleAbsolute(150, 50);

            PdfPTable table = new PdfPTable(4); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1.3f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            PdfPCell celllogo = new PdfPCell(image1);
            celllogo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celllogo.setVerticalAlignment(Element.ALIGN_TOP);
            celllogo.setBorderWidth(0);
            celllogo.setFixedHeight(60);
            celllogo.setColspan(1);

            Paragraph tittle = new Paragraph("BIÊN LAI", tittleFont);
            tittle.setIndentationLeft(100);
            Paragraph subTittle = new Paragraph("(Reciept)", subfont);
            subTittle.setIndentationLeft(120);
            PdfPCell celltittle = new PdfPCell();
            celltittle.addElement(tittle);
            celltittle.addElement(subTittle);
            celltittle.setBorderWidth(0);
            celltittle.setColspan(3);

            Paragraph tittle1 = new Paragraph("Số tài khoản", normalFont);
            tittle1.setIndentationLeft(10);
            Paragraph subTittle1 = new Paragraph("(Account number)", subfont);
            subTittle1.setIndentationLeft(20);
            PdfPCell cell1 = new PdfPCell();
            cell1.addElement(tittle1);
            cell1.addElement(subTittle1);
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setFixedHeight(45);
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell1CT = new PdfPCell(new Paragraph(fd.fmID(ae.getAccountID()), normalFont));
            cell1CT.setBorderColor(BaseColor.BLACK);
            cell1CT.setBorderColorRight(BaseColor.WHITE);
            cell1CT.setPaddingLeft(10);
            cell1CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1CT.setColspan(3);

            Paragraph tittle2 = new Paragraph("Số dư", normalFont);
            tittle2.setIndentationLeft(10);
            Paragraph subTittle2 = new Paragraph("(Ballance)", subfont);
            subTittle2.setIndentationLeft(15);
            PdfPCell cell2 = new PdfPCell();
            cell2.addElement(tittle2);
            cell2.addElement(subTittle2);
            cell2.setBorderColor(BaseColor.BLACK);
            cell2.setPaddingLeft(10);
            cell2.setFixedHeight(45);
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell2CT = new PdfPCell(new Paragraph(fd.fD(ae.getBallance()) + " VND", normalFont));
            cell2CT.setBorderColor(BaseColor.BLACK);
            cell2CT.setBorderColorRight(BaseColor.WHITE);
            cell2CT.setPaddingLeft(10);
            cell2CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2CT.setColspan(3);

            Paragraph tittle3 = new Paragraph("Kiểu tài khoản", normalFont);
            tittle3.setIndentationLeft(10);
            Paragraph subTittle3 = new Paragraph("(Account Type)", subfont);
            subTittle3.setIndentationLeft(20);
            PdfPCell cell3 = new PdfPCell();
            cell3.addElement(tittle3);
            cell3.addElement(subTittle3);
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setFixedHeight(45);
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell3CT = new PdfPCell(new Paragraph(ae.getAccountType(), normalFont));
            cell3CT.setBorderColor(BaseColor.BLACK);
            cell3CT.setBorderColorRight(BaseColor.WHITE);
            cell3CT.setPaddingLeft(10);
            cell3CT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3CT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3CT.setColspan(3);
            
            table.addCell(celllogo);
            table.addCell(celltittle);
            table.addCell(cell3);
            table.addCell(cell3CT);
            table.addCell(cell1);
            table.addCell(cell1CT);
            table.addCell(cell2);
            table.addCell(cell2CT);

            Paragraph thank = new Paragraph("Cảm ơn quý khách đã sử dụng dịch vụ của E-Banking!", subfont2);
            thank.setIndentationLeft(90);
            Paragraph thankEng = new Paragraph("(Thank you for banking with E-Banking!)", subfont);
            thankEng.setIndentationLeft(160);
            document.add(table);
            document.add(thank);
            document.add(thankEng);
            document.close();
            writer.close();
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }
}
