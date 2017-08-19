package com.eldermoraes.ch02.batch;

import java.util.StringTokenizer;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class UserProcessor implements ItemProcessor {

    @Override
    public User processItem(Object line) {
        User user = new User();

        StringTokenizer tokens = new StringTokenizer((String) line, ",");
        user.setId(Integer.parseInt(tokens.nextToken()));
        user.setName(tokens.nextToken());
        user.setEmail(tokens.nextToken());
        
        return user;
    }
}
