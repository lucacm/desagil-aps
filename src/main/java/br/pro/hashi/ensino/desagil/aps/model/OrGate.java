package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate not_A;
    private final NandGate not_B;
    private final NandGate nand;


    public OrGate() {
        super("OR", 2);

        not_A = new NandGate();

        not_B = new NandGate();

        nand = new NandGate();
        nand.connect(0, not_A);
        nand.connect(1, not_B);
    }

    @Override
    public boolean read() {
        return nand.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if ((inputIndex != 0) && (inputIndex != 1)) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        switch (inputIndex) {
            case 0:
                not_A.connect(0, emitter);
                not_A.connect(1, emitter);
                break;
            case 1:
                not_B.connect(0, emitter);
                not_B.connect(1, emitter);
                break;
        }
    }
}