package com.check24.IMDB.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class RateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2966128521608459668L;
    private Integer rate;
    private String username;
}
