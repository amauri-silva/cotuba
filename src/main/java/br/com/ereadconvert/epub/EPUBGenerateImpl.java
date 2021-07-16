package br.com.ereadconvert.epub;

import java.io.IOException;
import java.nio.file.Files;

import br.com.ereadconvert.domain.dto.Ebook;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

public class EPUBGenerateImpl implements EPUBGenerate {

    @Override
    public void generate(Ebook epubGenerate) {

        try {
            Book epub = new Book();
            EpubWriter epubWriter = new EpubWriter();

            try {
                epubWriter.write(epub, Files.newOutputStream(epubGenerate.getArquivoDeSaida()));

                // TODO: usar título do capítulo
                epub.addSection("Capítulo", new Resource(html.getBytes(), MediatypeService.XHTML));

            } catch (IOException ex) {
                throw new RuntimeException("Erro ao criar arquivo EPUB: " + epubGenerate.getArquivoDeSaida().toAbsolutePath() + "  " + ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Formato do epub inválido: ePUB");
            // TODO: handle exception
        }
    }
}
