package com.varunsambanni.issuetracker.exception;

import java.util.Map;

public record ValidationErrorResponse(
        String message,
        Map<String, String> errors)
{

}
