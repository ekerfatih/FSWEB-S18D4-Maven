package com.workintech.s18d1.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BurgerErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public BurgerErrorResponse(String expectedMessage) {
        message = expectedMessage;
    }
}
