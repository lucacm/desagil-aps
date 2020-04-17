package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// (a) Esta classe  é subclasse de JPanel (por conta do painel criado);
// e (b) esta classe agora implementa ActionListener,
// indicando que ela reage a eventos de interação.
public class GateView extends JPanel implements ActionListener {
    private final Gate gate;

    // Checkboxes
    private final JCheckBox In0Box;
    private final JCheckBox In1Box;
    private final JCheckBox OutBox;

    // Switches (atributos da classe Switch para selecionar checkboxes)
    private final Switch Switch0;
    private final Switch Switch1;

    public GateView(Gate gate) {
        this.gate = gate;
        // Instâncias (lembrando que os textos ao lado da checkbox
        // 0 e 1 representa a entrada e não o sinal recebido.
        In0Box = new JCheckBox("0");
        In1Box = new JCheckBox("1");
        OutBox = new JCheckBox();
        Switch0 = new Switch();
        Switch1 = new Switch();
        // Labels
        JLabel InLabel = new JLabel("Entrada:");
        JLabel OutLabel = new JLabel("Saída:");

        // Define o Layout à partir de setLayout para a janela de
        // vizualização do programa que simulará as checkboxes.
        // O parâmetro é o mesmo daclasse View.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adiciona as Labels & Checkboxs criadas anteriormente na ordem de exibição
        // Lembrando que a classe extends de JPanel para funcionar como contêiner.
        if (gate.getInputSize() != 1) {
            // Painel de exibição para gates com inputSize != 1
            // Portas: AND, NAND, OR, XOR
            add(InLabel);
            add(In0Box);
            add(In1Box);
            add(OutLabel);
            add(OutBox);
            // Adiciona ActionListener para as entradas e desabilita a edição da box de saida.
            In0Box.addActionListener(this);
            In1Box.addActionListener(this);
        } else {
            // Painel de exibição para gates com inputSize = 1
            // Portas: NOT
            add(InLabel);
            add(In0Box);
            add(OutLabel);
            add(OutBox);
            // Adiciona ActionListener para as entradas e desabilita a edição da box de saida.
            In0Box.addActionListener(this);
        }
        // Desabilita a checkbox de saída como opção clicável
        OutBox.setEnabled(false);
        // Atualiza a classe para uma nova iteração
        update();
    }

    // Método update serve para atualizar o painel de
    // seleção cada vez que o botão está selected e notSelected.
    private void update() {
        boolean OutState = gate.read();

        if (OutState) {
            OutBox.setSelected(true);
        } else
            OutBox.setSelected(false);
    }

    // Método actionPerformed realiza as conexões de
    // cada Gate emitindo sinais de acordo com a classe
    // Switch, em que diz: Quando o botão está marcado (checkbox)
    // um sinal true é emitido e o contrário um sinal false é emitido.
    @Override
    public void actionPerformed(ActionEvent event) {
        // Define o sinal se é true ou false dependendo se a
        // checkbox está selecionada ou não.
        boolean In0State = In0Box.isSelected();
        boolean In1State = In1Box.isSelected();

        // Utiliza o Switch para identificar o sinais como true or false
        // de acordo com a checkbox selecionada ou não
        if (In0State) {
            Switch0.turnOn();
        } else
            Switch0.turnOff();
        if (In1State) {
            Switch1.turnOn();
        } else
            Switch1.turnOff();

        // Condição para conectar a os sinais de acordo com o imputSize.
        if (gate.getInputSize() != 1) {
            gate.connect(0, Switch0);
            gate.connect(1, Switch1);
        } else {
            gate.connect(0, Switch0);
        }
        // Atualiza a classe para uma nova iteração
        update();
    }
}
