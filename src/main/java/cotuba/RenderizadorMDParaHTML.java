package cotuba;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.stream.Stream;

public class RenderizadorMDParaHTML {

	public void renderiza(Path diretorioDosMD) {
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
		Stream<Path> arquivosMD = Stream.empty();
		try {
			arquivosMD = Files.list(diretorioDosMD).filter(arquivo -> matcher.matches(arquivo)).sorted();
		} catch (IOException ex) {
			throw new RuntimeException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(),
					ex);
		}
	}

}