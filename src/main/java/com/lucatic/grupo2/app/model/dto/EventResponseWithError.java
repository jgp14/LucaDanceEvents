package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseWithError {

    private EventResponse eventResponse;
    private Error error;
    private boolean isErrorBool;
}
