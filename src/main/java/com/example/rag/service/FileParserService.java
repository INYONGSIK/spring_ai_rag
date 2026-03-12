package com.example.rag.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileParserService {

    public String parse(MultipartFile file) throws Exception {

        String name = file.getOriginalFilename();

        if(name.endsWith(".docx")) {
            return parseDocx(file);
        }

        if(name.endsWith(".xlsx")) {
            return parseXlsx(file);
        }

        if(name.endsWith(".pptx")) {
            return parsePptx(file);
        }

        if(name.endsWith(".pdf")) {
            return parsePdf(file);
        }

        throw new RuntimeException("지원하지 않는 파일");
    }

    private String parseDocx(MultipartFile file) throws Exception {

        XWPFDocument doc =
                new XWPFDocument(file.getInputStream());

        XWPFWordExtractor extractor =
                new XWPFWordExtractor(doc);

        return extractor.getText();
    }

    private String parseXlsx(MultipartFile file) throws Exception {

        StringBuilder text = new StringBuilder();

        Workbook workbook =
                new XSSFWorkbook(file.getInputStream());

        for(Sheet sheet : workbook) {
            for(Row row : sheet) {
                for(Cell cell : row) {
                    text.append(cell.toString()).append(" ");
                }
                text.append("\n");
            }
        }

        return text.toString();
    }

    private String parsePptx(MultipartFile file) throws Exception {

        XMLSlideShow ppt =
                new XMLSlideShow(file.getInputStream());

        StringBuilder text = new StringBuilder();

        for(XSLFSlide slide : ppt.getSlides()) {
            for(XSLFShape shape : slide.getShapes()) {
                if(shape instanceof XSLFTextShape) {
                    text.append(
                            ((XSLFTextShape) shape).getText()
                    ).append("\n");
                }
            }
        }

        return text.toString();
    }

    /**
     * PDF 파싱
     */
    private String parsePdf(MultipartFile file) throws Exception {

        PDDocument document = PDDocument.load(file.getInputStream());

        PDFTextStripper stripper = new PDFTextStripper();

        String text = stripper.getText(document);

        document.close();

        return text;
    }

}