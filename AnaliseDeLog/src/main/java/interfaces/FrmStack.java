package interfaces;

import entidades.EntidadeThread;
import objetodevalor.Regex;
import repositorios.RepositorioThread;
import servicos.ServicoFachada;
import servicos.ServicoPopulaFrmStack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrmStack extends JFrame {

    RepositorioThread repositorioThread = new RepositorioThread();
    private JTextArea jTextArea = new JTextArea();
    private ServicoFachada servicoFachada = new ServicoFachada();

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                FrmStack frame = new FrmStack();
                frame.setVisible(true);
                frame.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmStack() {
        setTitle("Logz - An\u00E1lise de stacks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int altura = gd.getDisplayMode().getHeight() - 70;
        int largura = gd.getDisplayMode().getWidth() - 70;
        setBounds(100, 100, largura + 10, altura + 10);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSelecioneAThread = new JLabel("Selecione a Thread desejada: ");
        lblSelecioneAThread.setBounds((largura / 2) - 170, (int) (altura * 0.05), 256, 14);
        contentPane.add(lblSelecioneAThread);

        setLocationRelativeTo(null);

        JComboBox<Object> comboBox = new JComboBox<>(
                new DefaultComboBoxModel<>(servicoFachada.buscarTodosObjetosRepositorioThread().toArray()));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof EntidadeThread) {
                    EntidadeThread entidadeThread = (EntidadeThread) value;
                    String tipoDaThread = "Detalhada";
                    if(entidadeThread.getCaminho().contains("Cohere"))
                            tipoDaThread = "Coherence";

                    setText(tipoDaThread+" - " +entidadeThread.getCpu() + " - " + entidadeThread.getLwpid());
                }
                return this;
            }

        });

        comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(e -> {
            ServicoPopulaFrmStack servicoPopulaFrmStack = new ServicoPopulaFrmStack();
            servicoPopulaFrmStack.populaFrmStack(comboBox,jTextArea);
        });
        comboBox.setBounds((largura / 2) - 170, (int) (altura * 0.08), 170, 20);
        contentPane.add(comboBox);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Descri\u00E7\u00E3o da Stack da Thread escolhida", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel.setBounds(12, 97, 1271, 545);

        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane sp = new JScrollPane();
        sp.setBounds(10, 24, (int) (largura * 0.96), (int) (altura * 0.729));
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(sp);
        sp.setViewportView(jTextArea);


        jTextArea.addKeyListener(new KeyAdapter() {

        });
        jTextArea.setText("Descri\u00E7\u00E3o da Stack");


        JButton btnRetornar = new JButton("Retornar");
        btnRetornar.setBounds(((int) ((largura * 0.01))), (int) (altura * 0.92), 89, 23);
        btnRetornar.addActionListener(arg0 -> {
            servicoFachada.deletaStack();
            FrmNodos telaDois = new FrmNodos();
            telaDois.setVisible(true);
            setVisible(false);

        });

        contentPane.add(btnRetornar);

        JButton btnNewButton = new JButton("Copiar");

        btnNewButton.addActionListener(arg0 -> {

            String descricaoStack = jTextArea.getText();

            Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
            ClipboardOwner selection = new StringSelection(jTextArea.getText());
            board.setContents((Transferable) selection, selection);

        });
        btnNewButton.setBounds((largura / 2 - 89 / 2), (int) (altura * 0.92), 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Fechar");
        btnNewButton_1.addActionListener(e -> setVisible(false));
        btnNewButton_1.setBounds(((int) (largura * 0.99 - 89)), (int) (altura * 0.92), 89, 23);
        contentPane.add(btnNewButton_1);

        JLabel lblProcessoLwpid = new JLabel("Tipo Thread - Processo - LWPID");
        lblProcessoLwpid.setBounds(289, 55, 192, 20);
        contentPane.add(lblProcessoLwpid);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Legenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(1194,11 , 89, 78);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOCKED");
        lblNewLabel.setBackground(Color.GREEN);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setOpaque(true);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(4, 16, 80, 14);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("SOLUZIONA");
        lblNewLabel_1.setBackground(Color.YELLOW);
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setBounds(4, 33, 80, 14);
        panel_1.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("WAITING");
        lblNewLabel_2.setBackground(Color.RED);
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_2.setOpaque(true);
        lblNewLabel_2.setBounds(4, 50, 80, 14);
        panel_1.add(lblNewLabel_2);
    }
}