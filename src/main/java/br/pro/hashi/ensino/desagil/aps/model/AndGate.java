package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand;
    private final NandGate not;


    public AndGate() {
        super("AND", 2);
        // nand esquerdo
        nand = new NandGate();

        // nand direito
        not = new NandGate();
        not.connect(0, nand);
        not.connect(1, nand);
    }

    @Override
    public boolean read() {
        return not.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if ((inputIndex != 0) && (inputIndex != 1)) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand.connect(inputIndex, emitter);
    }
}

