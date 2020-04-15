package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;

    public XorGate() {
        super("XOR", 2);
        // nand esquerdo
        nand1 = new NandGate();

        // nand cima
        nand2 = new NandGate();
        nand2.connect(1, nand1);

        // nand baixo
        nand3 = new NandGate();
        nand3.connect(0, nand1);

        // nand direito
        nand4 = new NandGate();
        nand4.connect(0, nand2);
        nand4.connect(1, nand3);
    }

    @Override
    public boolean read() {
        return nand4.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0 && inputIndex != 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        switch (inputIndex) {
            case 0:
                nand1.connect(0, emitter);
                nand2.connect(0, emitter);
                break;
            case 1:
                nand1.connect(1, emitter);
                nand3.connect(1, emitter);
                break;
        }
    }
}
