package com.appengine.user.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@EqualsAndHashCode(of = "id")
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private int age;

}