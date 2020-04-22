package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;
import br.pro.hashi.ensino.desagil.aps.model.Light;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Gate gate;

    // Checkboxes
    private final JCheckBox In0Box;
    private final JCheckBox In1Box;

    // Switches (atributos da classe Switch para selecionar checkboxes)
    private final Switch Switch0;
    private final Switch Switch1;
    private final Light light;

    // Novos atributos necessários para esta versão da interface.
    private Color color;
    private Image image = null;

    public GateView(Gate gate) {

        // Como subclasse de FixedPanel, esta classe agora
        // exige que uma largura e uma altura sejam fixadas.
        super(220, 120);

        this.gate = gate;
        In0Box = new JCheckBox();
        In1Box = new JCheckBox();
        Switch0 = new Switch();
        Switch1 = new Switch();
        light = new Light(255, 0, 0);


        // Adiciona as Labels & Checkboxs criadas anteriormente na ordem de exibição
        // Lembrando que a classe extends de JPanel para funcionar como contêiner.
        if (gate.getInputSize() != 1) {
            // Painel de exibição para gates com inputSize != 1
            // Portas: AND, NAND, OR, XOR
            add(In0Box, 10, 40, 20, 20);
            add(In1Box, 10, 65, 20, 20);

            String name = gate.toString() + ".png";
            URL url = getClass().getClassLoader().getResource(name);
            image = getToolkit().getImage(url);

            // Adiciona ActionListener para as entradas e desabilita a edição da box de saida.
            In0Box.addActionListener(this);
            In1Box.addActionListener(this);
        } else {
            // Painel de exibição para gates com inputSize = 1
            // Portas: NOT
            add(In0Box, 10, 20, 25, 25);
            // Adiciona ActionListener para as entradas e desabilita a edição da box de saida.
            In0Box.addActionListener(this);
        }


        // Conecta os respectivos sinais às entradas
        if (gate.getInputSize() != 1) {
            gate.connect(0, Switch0);
            gate.connect(1, Switch1);
        } else {
            gate.connect(0, Switch0);
        }

        light.connect(0, gate);

        addMouseListener(this);

        // Atualiza a classe para uma nova iteração
        update();
    }
    // Método update serve para atualizar o painel de
    // seleção cada vez que o botão está selected e notSelected.

    private void update() {
        color = light.getColor();
        repaint();
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

        // Atualiza a classe para uma nova iteração
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        // Se o clique foi dentro do quadrado colorido...
        if (x >= 210 && x < 235 && y >= 311 && y < 336) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 30, 10, 150, 100, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillOval(172, 50, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
