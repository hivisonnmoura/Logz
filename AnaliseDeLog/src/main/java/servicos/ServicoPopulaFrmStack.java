package servicos;

import entidades.EntidadeThread;
import objetodevalor.Regex;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.regex.Matcher;


public class ServicoPopulaFrmStack {
    ServicoFachada servicoFachada = new ServicoFachada();

    public void populaFrmStack(JComboBox comboBox, JTextArea jTextArea){
        String stringStack1 = String.join("\n", servicoFachada.direcionaStack((EntidadeThread) comboBox.getSelectedItem()));
        jTextArea.setText(stringStack1);

        if (stringStack1.contains("locked")) {
            Regex regexDelimitaLinhasComLocked = Regex.REGEX_DELIMITA_LINHA_LOCKED;
            Matcher matcher = regexDelimitaLinhasComLocked.getPattern().matcher(stringStack1);
            while (matcher.find()) {
                int inicio = matcher.start();
                int fim = matcher.end();
                jTextArea.setSelectionStart(inicio);
                jTextArea.setSelectionEnd(fim);
                try {
                    Highlighter highlight = jTextArea.getHighlighter();
                    Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
                            Color.GREEN);
                    highlight.addHighlight(inicio, fim, painter);
                } catch (BadLocationException bad) {
                    bad.printStackTrace();
                }
            }
        }
        if (stringStack1.contains("waiting")) {
            Regex regexDelimitaLinhasComWaiting = Regex.REGEX_DELIMITA_LINHA_WAITING;
            Matcher matcher = regexDelimitaLinhasComWaiting.getPattern().matcher(stringStack1);
            while (matcher.find()) {
                int inicio = matcher.start();
                int fim = matcher.end();
                jTextArea.setSelectionStart(inicio);
                jTextArea.setSelectionEnd(fim);


                try {
                    Highlighter highlight = jTextArea.getHighlighter();
                    Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
                            Color.red);
                    highlight.addHighlight(inicio, fim, painter);
                } catch (BadLocationException bad) {
                    bad.printStackTrace();
                }
            }
        }
        if (stringStack1.contains("soluziona")) {
            Regex regexDelimitaLinhasComSoluziona = Regex.REGEX_DELIMITA_LINHA_SOLUZINA;
            Matcher matcher = regexDelimitaLinhasComSoluziona.getPattern().matcher(stringStack1);
            while (matcher.find()) {
                int inicio = matcher.start() + 1;
                int fim = matcher.end();
                jTextArea.setSelectionStart(inicio);
                jTextArea.setSelectionEnd(fim);


                try {
                    Highlighter highlight = jTextArea.getHighlighter();
                    Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
                            Color.yellow);
                    highlight.addHighlight(inicio, fim, painter);
                } catch (BadLocationException bad) {
                    bad.printStackTrace();

                }
            }
        }
    }
}
