package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.md.RenderizadorMDParaHTMLComCommonMark;
import cotuba.pdf.GeradorPDFComIText;

public interface RenderizadorMDParaHTML {
	List<Capitulo> renderiza(Path diretorioDosMD);
	
	static RenderizadorMDParaHTML cria() {
		return new RenderizadorMDParaHTMLComCommonMark();
	}
}