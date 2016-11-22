package com.brandon.controllers;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * Created by brandon Lee on 2016-10-28.
 */
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResults<T> implements Serializable {
	private static final long serialVersionUID = -5357075976645398489L;
	private boolean success;
	private List<String> errors;
	private T substance;

	public ApiResults(List<String> errors) {
		this.success = false;
		this.errors = errors;
	}

	public ApiResults(Exception e) {
		this.success = false;
		this.errors = Lists.newArrayList(e.getMessage());
	}

	public ApiResults(Exception e, String... errors) {
		this(e);
		this.errors = Arrays.asList(errors);
	}

	public ApiResults(Exception e, List<String> errors) {
		this(e);
		this.errors = errors;
	}

	public ApiResults(Throwable e) {
		this.success = false;
		this.errors = Lists.newArrayList(e.getMessage());
	}

	public ApiResults(Throwable e, String... errors) {
		this(e);
		this.errors = Arrays.asList(errors);
	}

	public ApiResults(Throwable e, List<String> errors) {
		this(e);
		this.errors = errors;
	}

	public ApiResults(T t) {
		this.success = true;
		this.substance = t;
	}

	public Long getIssued_time() {
		return System.currentTimeMillis();
	}
}
