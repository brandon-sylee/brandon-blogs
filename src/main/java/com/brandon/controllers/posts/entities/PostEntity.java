package com.brandon.controllers.posts.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import lombok.Data;
import lombok.NoArgsConstructor;

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

	@NotEmpty
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.SIMPLE_TEXT)
	@Size(min = 5)
	private String subject;

	@NotEmpty
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.BASIC_WITH_IMAGES)
	private String content;
}
