package org.example.mode.modeOffactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {

    static Map<String, Operation> operationMap = new HashMap<String, Operation>();

    static {
        operationMap.put("add", new Addition());
        operationMap.put("divide", new Division());
    }

    public static Optional<Operation> getOperation(String operator){
        return Optional.ofNullable(operationMap.get(operator));
    }
}
