package com.check24.IMDB.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class GenreDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6162450606773865662L;
    private String name;
}
