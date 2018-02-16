package com.eldermoraes.ch09.async.result;

import java.util.concurrent.Executor;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author eldermoraes
 */
@Path("asyncResultService")
public class AsyncResultService {

    @Inject
    private Executor executor;
    
    @EJB
    private UserBean userBean;

    @GET
    public void asyncResult(@Suspended final AsyncResponse asyncResponse) {
        executor.execute(() -> {
            User user = userBean.getSlowUser();
            asyncResponse.resume(user);
        });
    }
}
