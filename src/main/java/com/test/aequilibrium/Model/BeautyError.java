package com.test.aequilibrium.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class BeautyError {

    @Getter
    @Setter
    private HttpStatus status;
    @Getter
    @Setter
    private List<String> messages;

}
