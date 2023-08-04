package com.check24.IMDB.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4758471208824513039L;

    private Long userId;
    private String username;
    private String email;
}
