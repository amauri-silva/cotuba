package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

public class Cotuba {

    public void executa(ParametrosCotuba parametros) {
    	
    	String formato = parametros.getFormato();
        Path diretorioDosMD = parametros.getDiretorioDosMD();
        Path arquivoDeSaida = parametros.getArquivoDeSaida();

    	RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
    	List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
		
		Ebook ebook = new Ebook(formato, arquivoDeSaida);
		ebook.adicionaCapitulos(capitulos);

    	
    	gerador.gera(ebook);

    }

  }