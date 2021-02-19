package br.ifsul.trabLp;

import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;
import static javax.swing.JOptionPane.showMessageDialog;
import static org.apache.logging.log4j.util.Strings.isEmpty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrabLpApplication {

    private static ArtistaRepository artistaRepository;
    private static FestivalRepository festivalRepository;
    private static MusicaRepository musicaRepository;

    // mds a gente tem que ajeitar isso aqui

    private final static JTextField tfNomeArtista = new JTextField();
    private final static JTextField tfDataNascArtista = new JTextField();
    private final static JTextField tfNacionalidadeArtista = new JTextField();

    private final static JTextField tfNomeFestival = new JTextField();
    private final static JTextField tfDataFestival = new JTextField();
    private final static JTextField tfLocalFestival = new JTextField();
    private final static JTextField tfCategoriaFestival = new JTextField();

    private final static JTextField tfNomeMusica = new JTextField();
    private final static JTextField tfLetraMusica = new JTextField();
    private final static JTextField tfDuracaoMusica = new JTextField();

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(TrabLpApplication.class, args);

        artistaRepository = context.getBean(ArtistaRepository.class);
        festivalRepository = context.getBean(FestivalRepository.class);
        musicaRepository = context.getBean(MusicaRepository.class);

        criaUI();
    }

    private static void criaUI() {

        final JFrame janelaInicial = gerarFrame("Trabalho");

        final JPanel painelInicial = new JPanel();
        painelInicial.setLayout(new GridLayout(4, 1, 20, 20));

        final JButton btArtista = new JButton("Artista");
        final JButton btFestival = new JButton("Festival");
        final JButton btMusica = new JButton("Música");

        btArtista.addActionListener(l -> {
            janelaInicial.dispose();
            irParaSecaoArtista();
        });

        btFestival.addActionListener(l -> {
            janelaInicial.dispose();
            irParaSecaoFestival();
        });

        btMusica.addActionListener(l -> {
            janelaInicial.dispose();
            irParaSecaoMusica();
        });

        painelInicial.add(gerarTituloSecao("Menu principal"));
        painelInicial.add(btArtista);
        painelInicial.add(btFestival);
        painelInicial.add(btMusica);

        janelaInicial.setContentPane(painelInicial);
        janelaInicial.revalidate();
    }

    private static void irParaSecaoArtista() {

        final JFrame frameSecaoArtista = gerarFrame("Seção Artista");

        final JPanel painelSecaoArtista = new JPanel();
        painelSecaoArtista.setLayout(new GridLayout(3, 1, 20, 20));

        final JButton btCadastrarArtista = new JButton("Cadastrar Artista");
        final JButton btListarArtistas = new JButton("Listar Artistas");

        btCadastrarArtista.addActionListener(l -> {
            frameSecaoArtista.dispose();
            irParaTelaCadastroArtista();
        });

        btListarArtistas.addActionListener(l -> {
            frameSecaoArtista.dispose();
            irParaTelaListarArtistas();
        });

        painelSecaoArtista.add(gerarTituloSecao("Seção Artista"));
        painelSecaoArtista.add(btCadastrarArtista);
        painelSecaoArtista.add(btListarArtistas);

        frameSecaoArtista.setContentPane(painelSecaoArtista);
    }

    private static void irParaSecaoFestival() {

        final JFrame frameSecaoFestival = gerarFrame("Seção Festival");

        final JPanel painelSecaoFestival = new JPanel();
        painelSecaoFestival.setLayout(new GridLayout(3, 1, 20, 20));

        final JButton btCadastrarFestival = new JButton("Cadastrar Festival");
        final JButton btListarFestivais = new JButton("Listar Festivais");

        btCadastrarFestival.addActionListener(l -> {
            frameSecaoFestival.dispose();
            irParaTelaCadastroFestival();
        });

        btListarFestivais.addActionListener(l -> {
            frameSecaoFestival.dispose();
            irParaTelaListarFestivais();
        });

        painelSecaoFestival.add(gerarTituloSecao("Seção Festival"));
        painelSecaoFestival.add(btCadastrarFestival);
        painelSecaoFestival.add(btListarFestivais);

        frameSecaoFestival.setContentPane(painelSecaoFestival);
    }

    private static void irParaSecaoMusica() {

        final JFrame frameSecaoMusica = gerarFrame("Seção Música");

        final JPanel painelSecaoMusica = new JPanel();
        painelSecaoMusica.setLayout(new GridLayout(3, 1, 20, 20));

        final JButton btCadastrarMusica = new JButton("Cadastrar Música");
        final JButton btListarMusicas = new JButton("Listar Músicas");

        btCadastrarMusica.addActionListener(l -> {
            frameSecaoMusica.dispose();
            irParaTelaCadastroMusica();
        });

        btListarMusicas.addActionListener(l -> {
            frameSecaoMusica.dispose();
            irParaTelaListarMusicas();
        });

        painelSecaoMusica.add(gerarTituloSecao("Seção Música"));
        painelSecaoMusica.add(btCadastrarMusica);
        painelSecaoMusica.add(btListarMusicas);

        frameSecaoMusica.setContentPane(painelSecaoMusica);
    }

    private static void irParaTelaListarMusicas() {

        final JFrame frameListaMusicas = gerarFrame("Músicas");

        final JPanel painelListaMusicas = new JPanel();
        painelListaMusicas.setLayout(new GridLayout(0, 2, 50, 50));

        final List<Musica> musicas = musicaRepository.findAll();

        if (musicas.isEmpty()) {

            painelListaMusicas.add(new JLabel("Não há músicas cadastrados no momento"));
        } else {

            musicas.forEach(m -> {

                final JLabel lblMusica = new JLabel(m.getNome());
                final JButton btMusica = new JButton("Ir para a tela da música - " + m.getNome());

                btMusica.addActionListener(l -> {
                    frameListaMusicas.dispose();
                    irParaTelaMusica(m);
                });

                painelListaMusicas.add(lblMusica);
                painelListaMusicas.add(btMusica);
            });
        }

        frameListaMusicas.setContentPane(painelListaMusicas);
    }

    private static void irParaTelaMusica(final Musica musica) {

        final JFrame frameTelaMusica = gerarFrame("Música - " + musica.getNome());

        final JPanel painelTelaMusica = new JPanel();
        painelTelaMusica.setLayout(new GridLayout(0, 1, 20, 20));

        painelTelaMusica.add(gerarTituloSecao(musica.getNome()));

        painelTelaMusica.add(new JLabel(musica.getArtista().getNome() + " - " + musica.getNome() + " (" +
            musica.getDuracao() + " seg) "));

        JTextArea textArea = new JTextArea(musica.getLetra());
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));

        painelTelaMusica.add(areaScrollPane);

        frameTelaMusica.setContentPane(painelTelaMusica);
    }

    private static void irParaTelaCadastroMusica() {

        final JFrame frameCadastroMusica = gerarFrame("Cadastro Música");

        final JPanel painelCadastroMusica = new JPanel();
        painelCadastroMusica.setLayout(new GridLayout(0, 1, 20, 20));

        final List<Artista> artistas = artistaRepository.findAll();

//        if (artistas.isEmpty()) {
//            // "cadastre artistas primeiro para poder cadastrar músicas
//        } else {
//            // tela de cadastro da música
//        }

        final JComboBox<String> cbArtistas = new JComboBox<>(
            artistas.stream().map(Artista::getNome).toArray(String[]::new));

        painelCadastroMusica.add(new JLabel("Nome"));
        painelCadastroMusica.add(tfNomeMusica);

        painelCadastroMusica.add(new JLabel("Letra"));
        painelCadastroMusica.add(tfLetraMusica);

        painelCadastroMusica.add(new JLabel("Duração (em segundos)"));
        painelCadastroMusica.add(tfDuracaoMusica);

        painelCadastroMusica.add(new JLabel("Artista"));
        painelCadastroMusica.add(cbArtistas);

//        final Artista artistaSelecionado = artistas.stream()
//            .filter(a -> a.getNome().equals(cbArtistas.getSelectedItem()))
//            .findFirst()
//            .orElse(null); // mover para método cadastrarMusica()

        final JButton btCadastrarMusica = new JButton("Cadastrar Música");
        btCadastrarMusica.addActionListener(l -> cadastrarMusica(artistas, (String) cbArtistas.getSelectedItem()));
        painelCadastroMusica.add(btCadastrarMusica);

        frameCadastroMusica.setContentPane(painelCadastroMusica);
    }

    private static void cadastrarMusica(final List<Artista> artistas, final String opcaoSelecionada) {

        if (isEmpty(tfNomeMusica.getText()) || isEmpty(tfLetraMusica.getText())
            || isEmpty(tfDuracaoMusica.getText()) || isNull(opcaoSelecionada)) {

            showMessageDialog(null, "É necessário preencher todos os dados da música para cadastrá-la!");
            return;
        }

        final Artista artistaSelecionado = artistas.stream() // criar um comboBox de Artista
            .filter(a -> a.getNome().equals(opcaoSelecionada))
            .findFirst()
            .orElse(null);

        final Musica musica = new Musica();
        musica.setNome(tfNomeMusica.getText());
        musica.setLetra(tfLetraMusica.getText());
        musica.setDuracao(Integer.parseInt(tfDuracaoMusica.getText()));
        musica.setArtista(artistaSelecionado);

        musicaRepository.save(musica);
        showMessageDialog(null, "Música cadastrada com sucesso!");
        tfNomeMusica.setText("");
        tfLetraMusica.setText("");
        tfDuracaoMusica.setText("");
    }

    private static void irParaTelaListarFestivais() {

        final JFrame frameListaFestivais = gerarFrame("Festivais");

        final JPanel painelListaFestivais = new JPanel();
        painelListaFestivais.setLayout(new GridLayout(0, 2, 50, 50));

        final List<Festival> festivais = festivalRepository.findAll();

        if (festivais.isEmpty()) {

            final JLabel lblSemFestivais = new JLabel("Não há festivais cadastrados no momento");
            painelListaFestivais.add(lblSemFestivais);
        } else {

            festivais.forEach(f -> {

                final JLabel lblFestival = new JLabel(f.getNome());
                final JButton btFestival = new JButton("Ir para a tela do festival - " + f.getNome());

                btFestival.addActionListener(l -> {
                    frameListaFestivais.dispose();
                    irParaTelaFestival(f);
                });

                painelListaFestivais.add(lblFestival);
                painelListaFestivais.add(btFestival);
            });
        }

        frameListaFestivais.setContentPane(painelListaFestivais);
    }

    private static void irParaTelaFestival(final Festival festival) {

        final JFrame frameTelaFestival = gerarFrame("Festival - " + festival.getNome());

        final JPanel painelTelaFestival = new JPanel();
        painelTelaFestival.setLayout(new GridLayout(0, 1, 20, 20));

        painelTelaFestival.add(gerarTituloSecao(festival.getNome()));

        final JPanel painelInfosFestival = new JPanel();
        painelInfosFestival.setLayout(new GridLayout(0, 1, 20, 20));
        final String infosFestival = "Nome: " + festival.getNome() + " | Local: " + festival.getLocal()
            + " | Data: " + festival.getData() + " | Categoria: " + festival.getCategoria();
        painelInfosFestival.add(new JLabel(infosFestival));

        painelTelaFestival.add(painelInfosFestival);

        painelTelaFestival.add(new JLabel("Artistas confirmados: "));

        final List<Artista> artistasFestival = artistaRepository.findAllByFestivaisIn(singletonList(festival));

        if (artistasFestival.isEmpty()) {
            painelTelaFestival.add(new JLabel("Poxa... ainda não há artistas confirmados para este festival"));
        } else {

            artistasFestival.forEach(a ->
                painelTelaFestival.add(new JLabel(a.getNome() + " - " + a.getNacionalidade()))
            );
        }

        painelTelaFestival.add(new JLabel("Adicionar artistas confirmados"));

        final List<Artista> artistasNaoConfirmados = artistaRepository.findAll().stream()
            .filter(a -> artistasFestival.stream().noneMatch(b -> b.getId().equals(a.getId())))
            .collect(Collectors.toList());

        final JComboBox<String> cbArtistasNaoConfirmados = new JComboBox<>(
            artistasNaoConfirmados.stream().map(Artista::getNome).toArray(String[]::new));

        final JButton btAdicionarArtista = new JButton("Adicionar artista");
        btAdicionarArtista.addActionListener(l -> {
            adicionarArtista(festival, artistasNaoConfirmados, (String) cbArtistasNaoConfirmados.getSelectedItem());
            frameTelaFestival.dispose();
            irParaTelaFestival(festival);
        }); // resolver gamb

        painelTelaFestival.add(cbArtistasNaoConfirmados);
        painelTelaFestival.add(btAdicionarArtista);

        frameTelaFestival.add(painelTelaFestival);
    }

    private static void adicionarArtista(final Festival festival, final List<Artista> artistasNaoConfirmados,
        final String selectedItem) {

        final Artista artista = artistasNaoConfirmados.stream()
            .filter(a -> a.getNome().equals(selectedItem))
            .findFirst()
            .orElse(null);

        festival.getArtistas().add(artista);
        artista.getFestivais().add(festival);

        festivalRepository.save(festival);
        showMessageDialog(null, "Artista adicionado com sucesso ao festival!");
    }

    private static void irParaTelaCadastroFestival() {

        final JFrame frameCadastroFestival = gerarFrame("Cadastro Festival");

        final JPanel painelCadastroFestival = new JPanel();
        painelCadastroFestival.setLayout(new GridLayout(0, 1, 20, 20));

        final JButton btCadastrarFestival = new JButton("Cadastrar Festival");
        btCadastrarFestival.addActionListener(l -> cadastrarFestival());

        painelCadastroFestival.add(gerarTituloSecao("Cadastro Festival"));

        painelCadastroFestival.add(new JLabel("Nome"));
        painelCadastroFestival.add(tfNomeFestival);

        painelCadastroFestival.add(new JLabel("Data"));
        painelCadastroFestival.add(tfDataFestival);

        painelCadastroFestival.add(new JLabel("Categoria"));
        painelCadastroFestival.add(tfCategoriaFestival);

        painelCadastroFestival.add(new JLabel("Local"));
        painelCadastroFestival.add(tfLocalFestival);

        painelCadastroFestival.add(btCadastrarFestival);

        frameCadastroFestival.setContentPane(painelCadastroFestival);
    }

    private static void cadastrarFestival() {

        // adicionar validator
        if (isEmpty(tfNomeFestival.getText()) || isEmpty(tfDataFestival.getText()) ||
            isEmpty(tfCategoriaFestival.getText()) || isEmpty(tfLocalFestival.getText())) {

            showMessageDialog(null, "É necessário preencher todos os dados do festival para cadastrá-lo!");
            return;
        }

        final Festival festival = new Festival(); // adicionar mapper
        festival.setNome(tfNomeFestival.getText());
        festival.setData(Date.valueOf(tfDataFestival.getText()));
        festival.setCategoria(Categoria.values()[Integer.parseInt(tfCategoriaFestival.getText())]);
        festival.setLocal(tfLocalFestival.getText());

        festivalRepository.save(festival);

        showMessageDialog(null, "Festival cadastrado com sucesso!");
        tfNomeFestival.setText("");
        tfDataFestival.setText("");
        tfCategoriaFestival.setText("");
        tfLocalFestival.setText("");
    }

    private static void irParaTelaCadastroArtista() {

        final JFrame frameCadastroArtista = gerarFrame("Cadastro Artista");

        final JPanel painelCadastroArtista = new JPanel();
        painelCadastroArtista.setLayout(new GridLayout(0, 1, 20, 20));

        final JButton btCadastrarArtista = new JButton("Cadastrar Artista");
        btCadastrarArtista.addActionListener(l -> cadastrarArtista());

        painelCadastroArtista.add(gerarTituloSecao("Cadastro Artista"));

        painelCadastroArtista.add(new JLabel("Nome"));
        painelCadastroArtista.add(tfNomeArtista);

        painelCadastroArtista.add(new JLabel("Data de Nascimento"));
        painelCadastroArtista.add(tfDataNascArtista);

        painelCadastroArtista.add(new JLabel("Nacionalidade"));
        painelCadastroArtista.add(tfNacionalidadeArtista);

        painelCadastroArtista.add(btCadastrarArtista);

        frameCadastroArtista.setContentPane(painelCadastroArtista);
    }

    private static void cadastrarArtista() {

        // adicionar validator
        if (isEmpty(tfNomeArtista.getText()) || isEmpty(tfNacionalidadeArtista.getText()) ||
            isEmpty(tfDataNascArtista.getText())) {

            showMessageDialog(null, "É necessário preencher todos os dados do artista para cadastrá-lo!");
            return;
        }

        final Artista artista = new Artista();
        artista.setNome(tfNomeArtista.getText()); // criar mapper
        artista.setNacionalidade(tfNacionalidadeArtista.getText());
        artista.setDataNasc(Date.valueOf(tfDataNascArtista.getText()));

        artistaRepository.save(artista);

        showMessageDialog(null, "Artista cadastrado com sucesso!");
        tfNomeArtista.setText(""); // criar método privado
        tfNacionalidadeArtista.setText("");
        tfDataNascArtista.setText("");
    }

    private static void irParaTelaListarArtistas() {

        final JFrame frameListaArtistas = gerarFrame("Artistas");

        final JPanel painelListaArtistas = new JPanel();
        painelListaArtistas.setLayout(new GridLayout(0, 2, 50, 50));

        final List<Artista> artistas = artistaRepository.findAll();

        if (artistas.isEmpty()) {

            painelListaArtistas.add(new JLabel("Não há artistas cadastrados no momento"));
        } else {

            artistas.forEach(a -> {

                final JLabel lblArtista = new JLabel(a.getNome());
                final JButton btArtista = new JButton("Ir para a tela do artista - " + a.getNome());

                btArtista.addActionListener(l -> {
                    frameListaArtistas.dispose();
                    irParaTelaArtista(a);
                });

                painelListaArtistas.add(lblArtista);
                painelListaArtistas.add(btArtista);
            });
        }

        frameListaArtistas.setContentPane(painelListaArtistas);
    }

    private static void irParaTelaArtista(final Artista artista) {

        final JFrame frameTelaArtista = gerarFrame("Artista - " + artista.getNome());

        final JPanel painelTelaArtista = new JPanel();
        painelTelaArtista.setLayout(new GridLayout(0, 2, 20, 20));

        final JPanel painelArtistaFestivais = new JPanel();
        painelArtistaFestivais.setLayout(new GridLayout(0, 1));
        painelArtistaFestivais.add(gerarTituloSecao("Festivais confirmados"));
        final List<Festival> festivaisArtista = festivalRepository.findAllByArtistasIn(singletonList(artista));

        if (festivaisArtista.isEmpty()) {
            painelArtistaFestivais.add(new JLabel("Este artista ainda não está confirmado em nenhum festival!"));
        } else {
            festivaisArtista.forEach(f -> painelArtistaFestivais.add(new JLabel(f.getNome())));
        }

        final JPanel painelArtistaMusicas = new JPanel();
        painelArtistaMusicas.setLayout(new GridLayout(0, 1));
        painelArtistaMusicas.add(gerarTituloSecao("Músicas cadastradas"));
        final List<Musica> musicasArtista = musicaRepository.findAllByArtista(artista);

        if (musicasArtista.isEmpty()) {
            painelArtistaMusicas.add(new JLabel("Este artista ainda não possui nenhuma música cadastrada!"));
        } else {
            musicasArtista.forEach(f -> painelArtistaMusicas.add(new JLabel(f.getNome())));
        }

        painelTelaArtista.add(painelArtistaFestivais);
        painelTelaArtista.add(painelArtistaMusicas);
        frameTelaArtista.setContentPane(painelTelaArtista);
    }

    private static JLabel gerarTituloSecao(final String titulo) {

        JLabel lblTituloSecao = new JLabel(titulo, SwingConstants.CENTER);
        lblTituloSecao.setForeground(Color.white);
        lblTituloSecao.setBackground(new Color(255, 36, 61));
        lblTituloSecao.setOpaque(true);

        return lblTituloSecao;
    }

    private static JFrame gerarFrame(final String titulo) {

        final JFrame jframe = new JFrame(titulo);
        jframe.setSize(600, 740);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        return jframe;
    }
}
