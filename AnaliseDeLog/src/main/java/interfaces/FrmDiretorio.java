package interfaces;

import servicos.ServicoFachada;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FrmDiretorio extends JFrame  {


    private static final long serialVersionUID = 1L;
    private JTextField txtInserirDiretrio;
    ServicoFachada servicoFachada = new ServicoFachada();


    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
            try {
                FrmDiretorio frame = new FrmDiretorio();
                frame.setResizable(false);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    public FrmDiretorio() {
        setTitle("Logz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 444, 339);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(
                new TitledBorder(null, "Selecionar o Diretorio com os .tar.gz", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 42, 408, 47);
        contentPane.add(panel);
        panel.setLayout(null);


        txtInserirDiretrio = new JTextField();
        txtInserirDiretrio.setForeground(Color.LIGHT_GRAY);
        txtInserirDiretrio.setBounds(6, 17, 285, 20);
        panel.add(txtInserirDiretrio);
        txtInserirDiretrio.setColumns(10);


        JButton btnLocalizar = new JButton("Localizar");
        btnLocalizar.addActionListener(e -> {

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int i = file.showSaveDialog(null);
            if (i != 1) {

                File arquivo = file.getSelectedFile();
                String caminho = arquivo.toString();
                txtInserirDiretrio.setText(arquivo.getAbsolutePath());


            }
        });
        btnLocalizar.setBounds(301, 16, 97, 23);
        panel.add(btnLocalizar);

        JTextArea textArea = new JTextArea();
        textArea.setText(null);
        textArea.setBounds(10, 115, 408, 132);
        contentPane.add(textArea);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        textArea.setLayout(new BorderLayout());
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setErr(printStream);
        textArea.setVisible(false);

        JButton btnDescompactar = new JButton("Descompactar");
        btnDescompactar.setBounds(137, 266, 150, 23);
        contentPane.add(btnDescompactar);
        btnDescompactar.addActionListener(e -> {

            List<String> ListaArquivo = new ArrayList<>();
            String caminho = txtInserirDiretrio.getText();
            setCursor(WAIT_CURSOR);
            File arquivo = new File(caminho);
            try {
                if (arquivo.listFiles().length == 0)
                    throw new NullPointerException();
                for (File f : arquivo.listFiles()) {
                    if (f.isFile()) {
                        if (f.getName().endsWith(".tar.gz")) {
                            ListaArquivo.add(f.getName());
                        } else {
                            throw new NullPointerException();
                        }
                    }
                }

                servicoFachada.solicitarServicoDescompactador(caminho, ListaArquivo);
                setCursor(DEFAULT_CURSOR);
                textArea.setText(null);
                this.dispose();

            } catch (NullPointerException nullPointer) {
                String erroAoSelecionarDiretorio = "Diretório inválido";
                JOptionPane.showMessageDialog(null, erroAoSelecionarDiretorio, "Erro ao localizar diretório", JOptionPane.ERROR_MESSAGE);
                setCursor(DEFAULT_CURSOR);
                textArea.setText(null);
                btnLocalizar.doClick();
            }
        });

    }
}
