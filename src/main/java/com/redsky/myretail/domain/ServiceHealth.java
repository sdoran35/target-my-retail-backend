package com.redsky.myretail.domain;

import java.io.Serializable;

/**
 * Simple object to return on servivce health
 */
public class ServiceHealth implements Serializable {
    private static final long serialVersionUID = -5714100315557502129L;

    private final long id;
    private final String content;

    public ServiceHealth(final long id,
                         final String content) {

        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
