package org.example.mode.modeOfenum;

public enum Operator {
    ADD {
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    },
    DIVIDE {
        @Override
        public int apply(int a, int b) {
            return a - b;
        }
    };
    // other operators

    public abstract int apply(int a, int b);

    }
