package com.brandon.controllers.posts.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by brandon Lee on 2016-10-28.
 */
@NoArgsConstructor
@Data
@Entity
public class PostEntity implements Serializable {
    private static final long serialVersionUID = -4260158103411947036L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;

    @NotNull
    @Size(min = 5)
    private String subject;

    @NotEmpty
    private String content;
}
