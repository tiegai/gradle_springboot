package org.example.mode.modeOfcommand;

public class DivideCommand implements Command {

    private int a;
    private int b;

    public DivideCommand(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer execute() {
        return a - b;
    }
}
