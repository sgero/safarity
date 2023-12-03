package com.example.safarity.service;

import com.example.safarity.model.Ticket;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.thymeleaf.context.Context;

import javax.swing.text.Document;
import java.io.*;
import java.util.List;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";


    private SpringTemplateEngine springTemplateEngine;


    private TicketService ticketService;


    @Autowired
    public PdfService(SpringTemplateEngine springTemplateEngine, TicketService ticketService) {
        this.springTemplateEngine = springTemplateEngine;
        this.ticketService = ticketService;
    }


    public File generateTicketPdf() throws Exception {
        Context context = getContextTicketListPdf();
        String html = loadAndFillTemplate(context);
        String xhtml = convertToXhtml(html);
        return renderTicketListPdf(xhtml);
    }

    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentContent(true);
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setSmartIndent(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        tidy.setTidyMark(false);

        Document htmlDOM = (Document) tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint((org.w3c.dom.Document) htmlDOM, out);
        return out.toString();
        
    }

    private File renderTicketListPdf(String html) throws Exception {
        File file = File.createTempFile("ticket", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }


    private Context getContextTicketListPdf() {
        List<Ticket> ticketList = this.ticketService.list();
        Context context = new Context();
        context.setVariable("ticket-list", ticketList);
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return springTemplateEngine.process("ticketPDF", context);
    }

}
