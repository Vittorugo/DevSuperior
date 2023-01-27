package com.example.trabalhofinalcap01.resources.exceptions;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageError {

    private String message;
    private String error;
    private String path;
    private Integer status;
    private Instant time;
}
