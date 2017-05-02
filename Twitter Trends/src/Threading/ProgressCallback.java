package Threading;

public interface ProgressCallback<Progress> {
    void onProgressChanged(Progress progress);
}
