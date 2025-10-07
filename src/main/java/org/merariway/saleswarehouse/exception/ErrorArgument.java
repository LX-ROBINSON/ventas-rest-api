package org.merariway.saleswarehouse.exception;

import java.util.Map;

public record ErrorArgument(String message, Map<String, String> errors) {
}
