package com.example.demo.filter;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestHeadersContext {

    public static final String X_SOURCE = "x-Source";

    private String xSource;
}