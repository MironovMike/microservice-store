package ru.mironovmike.store.util;

import java.util.Optional;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();
    public static UserContext getUserContext() {
        Optional<UserContext> optional = Optional.ofNullable(UserContextHolder.userContext.get());
        if (!optional.isPresent()) {
            setUserContext(new UserContext());
        }
        return userContext.get();
    }

    public static void setUserContext(UserContext context) {
        userContext.set(context);
    }
}
