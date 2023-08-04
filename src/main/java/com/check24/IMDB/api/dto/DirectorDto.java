package com.check24.IMDB.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DirectorDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 5114844584050866375L;
    private String name;
}
