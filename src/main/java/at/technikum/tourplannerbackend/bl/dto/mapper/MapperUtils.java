package at.technikum.tourplannerbackend.bl.dto.mapper;

import java.util.function.Consumer;

public class MapperUtils {

    private MapperUtils() {
    }

    public static <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (null != value) {
            setter.accept(value);
        }
    }
}
