package com.github.twosj.selection.util.validation;


import com.github.twosj.selection.HasId;
import com.github.twosj.selection.error.IllegalRequestDataException;
import com.github.twosj.selection.error.NotFoundException;
import com.github.twosj.selection.error.NotValidTimeException;
import lombok.experimental.UtilityClass;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static com.github.twosj.selection.web.vote.ProfileVoteController.FIX_CLOSE_TIME;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    public static void checkDateConsistent(LocalDateTime dateTime) {
        if (dateTime.toLocalTime().isAfter(FIX_CLOSE_TIME)) {
            throw new NotValidTimeException("Vote closed");
        }
    }

    //  https://stackoverflow.com/a/65442410/548473
    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }

    public static Supplier<NotFoundException> getNot_found(String message) {
        return () -> new NotFoundException(message);
    }
}