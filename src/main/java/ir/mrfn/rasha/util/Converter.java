package ir.mrfn.rasha.util;


import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.UUID;

public class Converter {


    public synchronized void convertToPdf() throws IOException {

            String path = ResourceUtils.getFile("classpath:static").getAbsolutePath();
            InputStream docFile = new FileInputStream(new File(path+File.separator+"orgin"+File.separator+"file.docx"));
            XWPFDocument doc = new XWPFDocument(docFile);
            PdfOptions pdfOptions = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(path+File.separator+"generated"+File.separator+ UUID.randomUUID()+".pdf"));
            PdfConverter.getInstance().convert(doc, out, pdfOptions);

            doc.close();
            out.close();


    }


}
