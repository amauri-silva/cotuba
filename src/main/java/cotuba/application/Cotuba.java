package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUBImpl;
import cotuba.md.RenderizadorMDParaHTMLImpl;
import cotuba.pdf.GeradorPDFImpl;

public class Cotuba {

    public void executa(String formato, Path diretorioDosMD, Path arquivoDeSaida) {

		RenderizadorMDParaHTMLImpl renderizador = new RenderizadorMDParaHTMLImpl(); 
		List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook(formato, arquivoDeSaida);
		ebook.adicionaCapitulos(capitulos);

    	if ("pdf".equals(formato)) {
			
			GeradorPDFImpl	geradorPDF	=	new	GeradorPDFImpl();
			geradorPDF.gera(ebook);

		} else if ("epub".equals(formato)) {
			 
			GeradorEPUBImpl geradorEPUB = new GeradorEPUBImpl();
		    geradorEPUB.gera(ebook);
		      
		} else {
			throw new RuntimeException("Formato do ebook inválido: " + formato);
		}

    }

  }