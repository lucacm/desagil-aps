package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

// Duas modificações em relação à versão da entrega anterior:
// (a) esta classe agora é subclasse de FixedPanel em vez
// de JPanel; e (b) esta classe agora implementa MouseListener,
// indicando que ela reage a eventos de interação com o mouse.
public class GateView extends JPanel implements ActionListener {
    private final Gate gate;
    
    // Checkboxes

    private final JCheckBox In0Box;
    private final JCheckBox In1Box;
    private final JCheckBox OutBox;

    // Switches

    private final Switch Switch0;
    private final Switch Switch1;

    public GateView(Gate gate) {
        this.gate = gate;

        In0Box = new JCheckBox("0");
        In1Box = new JCheckBox("1");
        OutBox = new JCheckBox();
        Switch0 = new Switch();
        Switch1 = new Switch();

        JLabel InLabel = new JLabel("Entrada:");
        JLabel OutLabel = new JLabel("Saída:");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(InLabel);
        add(In0Box);
        add(In1Box);
        add(OutLabel);
        add(OutBox);

        // Adiciona ActionListener para as entradas e desabilita a edição da box de saida.
        In0Box.addActionListener(this);
        In1Box.addActionListener(this);
        OutBox.setEnabled(false);

        update();
    }

    private void update() {
        boolean OutState = gate.read();

        if (OutState) {
            OutBox.setSelected(true);
        } else
            OutBox.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        boolean In0State = In0Box.isSelected();
        boolean In1State = In1Box.isSelected();

        if (In0State) {
            Switch0.turnOn();
        } else
            Switch0.turnOff();

        if (In1State) {
            Switch1.turnOn();
        } else
            Switch1.turnOff();

        gate.connect(0,Switch0);
        gate.connect(1,Switch1);

        update();
    }
}
