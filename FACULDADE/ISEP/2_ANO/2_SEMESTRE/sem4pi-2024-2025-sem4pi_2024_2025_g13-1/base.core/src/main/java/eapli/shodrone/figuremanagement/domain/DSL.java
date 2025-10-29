package eapli.shodrone.figuremanagement.domain;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class DSL {
    private String script;

    protected DSL() {
        // for ORM
    }

    public DSL(String script) {
        if (script == null || script.isBlank()) throw new IllegalArgumentException("DSL script must not be null or blank");
        this.script = script;
    }

    public String script() {
        return script;
    }

    @Override
    public String toString() {
        return script;
    }
}
