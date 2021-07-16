package br.com.ereadconvert.pdf;

import java.nio.file.Path;

import br.com.ereadconvert.domain.dto.Ebook;


public interface PDFGenerate {

    void generate(Ebook ebook);

}
