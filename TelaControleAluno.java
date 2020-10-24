package br.unip.sicc.view;

import br.unip.sicc.dao.JpaUtil;
import br.unip.sicc.model.GerenciadorDeAluno;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaControleAluno extends JFrame implements WindowListener{

    private PainelCadastroAluno painelCadastroAluno;
    private PainelBuscaAluno painelBuscaAlunos2;
    
    private GerenciadorDeAluno gerenciador;

    //implementacao do padrao Singleton
    private static TelaControleAluno instance;
    
    private TelaControleAluno() {
        
        gerenciador = GerenciadorDeAluno.getInstance();

        this.setTitle("Controle de Aluno");
        this.setSize(800, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(this);

        painelCadastroAluno = PainelCadastroAluno.getInstance();
        painelBuscaAlunos = PainelBuscaAluno.getInstance();

        this.add(painelCadastroAluno, BorderLayout.WEST);
        this.add(painelBuscaAlunos, BorderLayout.CENTER);
        this.setJMenuBar(montaMenu());

        this.setVisible(true);

    }

    public static TelaControleAluno getInstance() {
        if (instance == null) {
            instance = new TelaControleAluno();
        }
        return instance;
    }

    
    //implementacao do padrao Singleton
   
    private JMenuBar montaMenu() {
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuCadastro = new JMenu("Cadastro");
        menuCadastro.setMnemonic(KeyEvent.VK_T);
        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjuda.setMnemonic(KeyEvent.VK_A);
        JMenuItem itemNovo = new JMenuItem("cadastrar");
        itemNovo.setMnemonic(KeyEvent.VK_N);
        itemNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelCadastroAluno.setAluno(gerenciador.getNovaAluno());
            }
        });
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.setMnemonic(KeyEvent.VK_S);
        itemSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensagemSobre = "Software desenvolvido para cadastro de alunos";
                JOptionPane.showMessageDialog(null, mensagemSobre,
                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuCadastro.add(itemNovo);
        menuAjuda.add(itemSobre);
        barraMenu.add(menuCadastro);
        barraMenu.add(menuAjuda);
        return barraMenu;
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaControleAluno tela = TelaControleAluno.getInstance();
            }
        });
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        JpaUtil.fechar();
    }

    @Override
    public void windowIconified(WindowEvent e){
        System.out.println("Minimizou");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
