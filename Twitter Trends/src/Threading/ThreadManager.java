package Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

public class ThreadManager {
    private final ExecutorService mExecutorService;

    public ThreadManager() {
        this(Executors.newFixedThreadPool(3));
    }

    public ThreadManager(final ExecutorService pExecutorService) {
        mExecutorService = pExecutorService;
    }

    public<Params, Progress, Result> void execute(Operation<Params, Progress, Result> operation,
                                                  Params param,
                                                  ResultCallback<Progress, Result> onResultCallback) {
        mExecutorService.execute(() -> {

            try {
                Result result = operation.doing(param, progress -> {
                    new Runnable() {

                        @Override
                        public void run() {
                            onResultCallback.onProgressChanged(progress);
                        }
                    }.run();
                });

                new Runnable() {

                    @Override
                    public void run() {
                        onResultCallback.onSuccess(result);
                    }
                }.run();

            } catch (Exception pE) {
                onResultCallback.onError(pE);
            }

        });


    }
}
