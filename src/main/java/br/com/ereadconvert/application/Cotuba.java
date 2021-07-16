package br.com.ereadconvert.application;

import java.nio.file.Path;
import java.util.List;

import br.com.ereadconvert.domain.dto.Chapter;
import br.com.ereadconvert.domain.dto.Ebook;
import br.com.ereadconvert.epub.EPUBGenerate;
import br.com.ereadconvert.epub.EPUBGenerateImpl;
import br.com.ereadconvert.md.RenderMDToHTML;
import br.com.ereadconvert.md.RenderMDToHTMLImpl;
import br.com.ereadconvert.pdf.PDFGenerate;
import br.com.ereadconvert.pdf.PDFGenerateImpl;

public class Cotuba {

    public void execute(String format, Path diretorioDosMD, Path arquivoDeSaida) {
        RenderMDToHTML render = new RenderMDToHTMLImpl();
        List<Chapter> chapter = render.render(diretorioDosMD);
        Ebook ebook = new Ebook();
        ebook.setFormat(format);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCaptulo(chapter);

        if ("pfd".equals(format)) {
            PDFGenerate pdfGenerate = new PDFGenerateImpl();
            pdfGenerate.generate(ebook);
        } else if ("epub".equals(format)) {
            EPUBGenerate epubGenerate = new EPUBGenerateImpl();
            epubGenerate.generate(epubGenerate);
        } else {
            throw new RuntimeException("Formato do ebook inválido: " + format);
        }

    }
}
