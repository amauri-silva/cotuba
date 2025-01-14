package br.com.ereadconvert.md;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import br.com.ereadconvert.domain.dto.Chapter;

public class RenderMDToHTMLImpl implements RenderMDToHTML {

    @Override
    public List<Chapter> render(Path diretorioDosMD) {

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
            arquivosMD.filter(matcher::matches).sorted().forEach(arquivoMD -> {
                Parser parser = Parser.builder().build();
                Node document = null;
                try {
                    document = parser.parseReader(Files.newBufferedReader(arquivoMD));
                    document.accept(new AbstractVisitor() {
                        @Override
                        public void visit(Heading heading) {
                            if (heading.getLevel() == 1) {
                                // capítulo
                                String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
                                // TODO: usar título do capítulo
                            } else if (heading.getLevel() == 2) {
                                // seção
                            } else if (heading.getLevel() == 3) {
                                // título
                            }
                        }

                    });
                } catch (Exception ex) {
                    throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
                }

                try {
                    HtmlRenderer renderer = HtmlRenderer.builder().build();
                    String html = renderer.render(document);

                    
                } catch (Exception ex) {
                    throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
                }
            });

            //TODO verificar a lista de retorno
            return null;
            
        } catch (IOException ex) {
            throw new RuntimeException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(),  ex);
        }
    }
}
