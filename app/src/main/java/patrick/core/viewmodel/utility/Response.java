package patrick.core.viewmodel.utility;

import io.reactivex.rxjava3.annotations.Nullable;

/**
 * Response holder provided to the UI
 *
 * @param <T>
 */
public class Response<T> {

    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(Status.SUCCESS, data, null);
    }

    public static <T> Response<T> error(Throwable error) {
        return new Response<>(Status.ERROR, null, error);
    }
}
