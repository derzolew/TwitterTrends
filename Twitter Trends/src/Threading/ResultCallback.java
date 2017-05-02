package Threading;

public interface ResultCallback<Progress, Result> extends ProgressCallback<Progress>  {
    void onSuccess(Result result);
    void onError(Exception e);
}
