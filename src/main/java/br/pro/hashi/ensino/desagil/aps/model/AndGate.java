package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand;
    private final NandGate not;


    public AndGate() {
        super("NOT", 1);

        nand = new NandGate();
        not = new NandGate();
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
        switch (inputIndex) {
            case 0:
                nand.connect(0, emitter);
                break;
            case 1:
                nand.connect(1, emitter);
                not.connect(0, nand);
                not.connect(1, nand);
                break;
        }
    }
}
