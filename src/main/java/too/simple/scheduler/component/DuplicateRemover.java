package too.simple.scheduler.component;

import too.simple.Request;
import too.simple.Task;

/**
 * Remove duplicate requests.
 * @author code4crafer@gmail.com
 * @since 0.5.1
 */
public interface DuplicateRemover {
    /**
     *
     * Check whether the request is duplicate.
     *
     * @param request request
     * @param task task
     * @return true if is duplicate
     */
    public boolean isDuplicate(Request request, Task task);

    /**
     * Reset duplicate check.
     * @param task task
     */
    public void resetDuplicateCheck(Task task);

    /**
     * Get TotalRequestsCount for monitor.
     * @param task task
     * @return number of total request
     */
    public int getTotalRequestsCount(Task task);

}
