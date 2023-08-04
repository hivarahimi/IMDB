package com.check24.IMDB.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class RatingDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8342878719394191451L;
    private Integer rating;
    private UserDto userDto;
}
