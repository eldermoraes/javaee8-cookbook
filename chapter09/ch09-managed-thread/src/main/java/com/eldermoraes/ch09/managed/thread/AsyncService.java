package com.eldermoraes.ch09.managed.thread;

import com.eldermoraes.ch09.managed.thread.remote.UserBean;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Stateless
@Path("asyncService")
public class AsyncService {

    @Inject
    private UserBean userBean;

    @Resource(name = "LocalManagedThreadFactory")
    private ManagedThreadFactory factory;

    @GET
    public void asyncService(@Suspended AsyncResponse response) {
        Thread thread = factory.newThread(() -> {
            response.resume(Response.ok(userBean.getUser()).build());
        });
        
        thread.setName("Managed Async Task");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

}
