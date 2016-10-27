package com.brandon.properties;

import com.google.common.base.Objects;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by brandon Lee on 2016-10-26.
 */
@Component
@ConfigurationProperties(locations = "classpath:blog-settings.yml", prefix = "blog", ignoreInvalidFields = true, exceptionIfInvalid = false)
public class BlogSettings implements Serializable {
    private static final long serialVersionUID = 4611529292747228247L;
    private Page page;
    private Resources resources;
    public BlogSettings() {
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("page", page)
                .add("resources", resources)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogSettings that = (BlogSettings) o;
        return Objects.equal(page, that.page) &&
                Objects.equal(resources, that.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(page, resources);
    }

    public static class Resources implements Serializable {
        private static final long serialVersionUID = -5031545854006797785L;
        private List<String> scripts;

        public Resources() {
        }

        public List<String> getScripts() {
            return scripts;
        }

        public void setScripts(List<String> scripts) {
            this.scripts = scripts;
        }
    }

    public static class Page implements Serializable {
        private static final long serialVersionUID = 1186300965446981273L;
        private String title;

        public Page() {
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("title", title)
                    .toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Page page = (Page) o;
            return Objects.equal(title, page.title);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(title);
        }

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
