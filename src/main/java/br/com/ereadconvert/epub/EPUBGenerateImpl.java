package br.com.ereadconvert.epub;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.stream.Stream;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

public class EPUBGenerateImpl implements EPUBGenerate {

    @Override
    public void generate(EPUBGenerate epubGenerate) {

        try {
            Book epub = new Book();
            EpubWriter epubWriter = new EpubWriter();

            try {
                epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));

                // TODO: usar título do capítulo
                epub.addSection("Capítulo", new Resource(html.getBytes(), MediatypeService.XHTML));

            } catch (IOException ex) {
                throw new RuntimeException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Formato do epub inválido: ePUB");
            // TODO: handle exception
        }
    }
}
