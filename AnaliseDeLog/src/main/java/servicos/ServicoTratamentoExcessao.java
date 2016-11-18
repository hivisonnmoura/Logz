package servicos;


import javax.swing.*;

public class ServicoTratamentoExcessao {
    public void tratamentoDeExcessaoSelecionaProcesso(){
        JOptionPane.showMessageDialog(null,"Selecione um processo!","Favor selecionar um processo", JOptionPane.ERROR_MESSAGE);
    }

    public void tratamentoDeExcessaoDiretorioInvalido(JButton btnLocalizar, JTextArea textArea){
        String erroAoSelecionarDiretorio = "Diretório inválido";
        JOptionPane.showMessageDialog(null, erroAoSelecionarDiretorio, "Erro ao localizar diretório", JOptionPane.ERROR_MESSAGE);
        textArea.setText(null);
        btnLocalizar.doClick();
    }
}
