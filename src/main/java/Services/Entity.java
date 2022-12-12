package Services;

import Services.entities.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public enum Entity {
    PLAYER("player", PlayerEntity.class),
    PROGRESS("progress", ProgressEntity.class),
    CURRENCY("currency", CurrencyEntity.class),
    ITEM("item", ItemEntity.class);

    private final String name;
    public final Class<? extends AbstractEntity> type;

    Entity(String name, Class<? extends AbstractEntity> type) {
        this.name = name;
        this.type = type;
    }

    public final <T extends AbstractEntity> T instance() {
        try {
            return (T) type.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Entity value(String name) {
        return Arrays.stream(values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
