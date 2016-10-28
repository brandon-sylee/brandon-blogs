package com.brandon.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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
    private String message;
    private T substance;

    public ApiResults(Exception e) {
        this.success = false;
        this.errors = Lists.newArrayList(e.getClass().getCanonicalName(), e.getMessage());
    }

    public ApiResults(Exception e, String message) {
        this(e);
        this.message = message;
    }

    public ApiResults(Exception e, String message, String... errors) {
        this(e);
        this.message = message;
        this.errors = Arrays.asList(errors);
    }

    public ApiResults(Exception e, String message, List<String> errors) {
        this(e);
        this.message = message;
        this.errors = errors;
    }

    public ApiResults(Throwable e) {
        this.success = false;
        this.errors = Lists.newArrayList(e.getMessage(), e.getCause().getMessage());
    }

    public ApiResults(Throwable e, String message) {
        this(e);
        this.message = message;
    }

    public ApiResults(Throwable e, String message, String... errors) {
        this(e);
        this.message = message;
        this.errors = Arrays.asList(errors);
    }

    public ApiResults(Throwable e, String message, List<String> errors) {
        this(e);
        this.message = message;
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
