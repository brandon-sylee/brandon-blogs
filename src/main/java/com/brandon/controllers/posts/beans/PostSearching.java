package com.brandon.controllers.posts.beans;

import java.io.Serializable;
import java.util.Optional;

import org.hibernate.validator.constraints.SafeHtml;

/**
 * Created by brandon Lee on 2016-11-22.
 */
public class PostSearching implements Serializable {
	private static final long serialVersionUID = -7011204005668728028L;

	@SafeHtml(whitelistType = SafeHtml.WhiteListType.BASIC)
	private String keyword;
	private PostSearchField fields;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public PostSearchField getFields() {
		return Optional.ofNullable(fields).orElse(PostSearchField.T);
	}

	public void setFields(PostSearchField fields) {
		this.fields = fields;
	}
}
