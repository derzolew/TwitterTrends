package Threading;

public class WorkerOperation implements Operation<String, Integer, String> {

    @Override
    public String doing(String pS, ProgressCallback<Integer> progressCallback) throws Exception {
        for (int i = 0; i < 3; i++) {
            Thread.currentThread().sleep(1000L);
            System.out.println("Worker operation: " + pS + i);
            progressCallback.onProgressChanged(i);



        }


        return "I did what you was doing";
    }
}
