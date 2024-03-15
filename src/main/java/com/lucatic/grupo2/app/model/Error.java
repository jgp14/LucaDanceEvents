package com.lucatic.grupo2.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private String message;
    private String error;
    private int status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime date;
}
