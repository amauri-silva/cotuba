package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.epub.GeradorEPUBImpl;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.pdf.GeradorPDF;

public class Cotuba {

    public void executa(String formato, Path diretorioDosMD, Path arquivoDeSaida) {

    	RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
    	List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook(formato, arquivoDeSaida);
		ebook.adicionaCapitulos(capitulos);

    	if ("pdf".equals(formato)) {
			
    		GeradorPDF geradorPDF = GeradorPDF.cria();
			geradorPDF.gera(ebook);

		} else if ("epub".equals(formato)) {
			 
			GeradorEPUB geradorEPUB = new GeradorEPUBImpl();
		    geradorEPUB.gera(ebook);
		      
		} else {
			throw new RuntimeException("Formato do ebook inválido: " + formato);
		}

    }

  }