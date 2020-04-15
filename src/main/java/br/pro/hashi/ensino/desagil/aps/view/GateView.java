package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

// Duas modificações em relação à versão da entrega anterior:
// (a) esta classe agora é subclasse de FixedPanel em vez
// de JPanel; e (b) esta classe agora implementa MouseListener,
// indicando que ela reage a eventos de interação com o mouse.
    public class GateView extends JPanel implements ItemListener {
    private final Gate gate;


    private final JCheckBox entradaZeroButton;
    private final JCheckBox entradaUmButton;
    private final JCheckBox saidaButton;



    public GateView(Gate gate) {
        this.gate = gate;


        entradaZeroButton = new JCheckBox("0");


        entradaUmButton = new JCheckBox("1");


        saidaButton = new JCheckBox("Saída:");



        //Register a listener for the check boxes.
//        entradaZeroButton.addItemListener(this);
//        entradaUmButton.addItemListener(this);
//        saidaButton.addItemListener(this);

        JLabel entradaLabel = new JLabel("Entrada:");
        JLabel saidaLabel = new JLabel("Saída:");

        // Não há mais a chamada de setLayout, pois ela agora
        // acontece no construtor da superclasse FixedPanel.





        entradaZeroButton.addActionListener(this);
        entradaUmButton.addActionListener(this);

        saidaButton.setEnabled(false);

        // Toda componente Swing tem uma lista de observadores
        // que reagem quando algum evento de mouse acontece.
        // Usamos o método addMouseListener para adicionar a
        // própria componente, ou seja "this", nessa lista.
        // Só que addMouseListener espera receber um objeto
        // do tipo MouseListener como parâmetro. É por isso que
        // adicionamos o "implements MouseListener" lá em cima.
//        addMouseListener(this);
//
//        update();

    }

    private void update() {
        double weight;
        double radius;

        try {
            weight = Double.parseDouble(weightField.getText());
            radius = Double.parseDouble(radiusField.getText());
        } catch (NumberFormatException exception) {
            resultField.setText("?");
            return;
        }

        double result = calculator.calculate(weight, radius);

        resultField.setText(Double.toString(result));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }


}
