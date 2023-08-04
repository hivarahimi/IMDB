package com.check24.IMDB.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "USER_EN")
public class UserEntity implements Serializable {

    private static final String USER_GENERATOR = "user_generator";
    @Serial
    private static final long serialVersionUID = 7446360012769549559L;

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = USER_GENERATOR)
    @SequenceGenerator(name = USER_GENERATOR, sequenceName = "USER_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long userId;

    private String username;
    private String email;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private Set<Rating> ratings = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
