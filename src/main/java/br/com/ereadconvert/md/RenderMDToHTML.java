package br.com.ereadconvert.md;

import java.nio.file.Path;
import java.util.List;

import br.com.ereadconvert.domain.dto.Chapter;


public interface RenderMDToHTML {

    List<Chapter> render(Path diretorioDosMD);

}
