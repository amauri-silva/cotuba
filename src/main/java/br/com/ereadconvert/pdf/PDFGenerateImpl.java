package br.com.ereadconvert.pdf;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;

import br.com.ereadconvert.domain.dto.Ebook;

public class PDFGenerateImpl implements PDFGenerate {

    @Override
    public void generate(Ebook ebook) {

        try (PdfWriter writer = new PdfWriter(Files.newOutputStream(ebook.getArquivoDeSaida()));
            PdfDocument pdf = new PdfDocument(writer);
            Document pdfDocument = new Document(pdf)) {


            try {
                HtmlRenderer renderer = HtmlRenderer.builder().build();
                String html = renderer.render(pdfDocument);

                List<IElement> convertToElements = HtmlConverter.convertToElements(html);
                for (IElement element : convertToElements) {
                    pdfDocument.add((IBlockElement) element);
                }
                // TODO: não adicionar página depois do último capítulo
                pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            } catch (Exception ex) {
                throw new RuntimeException("Erro ao criar arquivo PDF: " + ebook.getArquivoDeSaida().toAbsolutePath() + "  " +  ex);
            }

        }

    }
}
