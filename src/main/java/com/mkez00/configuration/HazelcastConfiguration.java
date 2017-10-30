package com.mkez00.configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Configuration;

/**
 * Created by michaelkezele on 2017-10-25.
 */
@Configuration
public class HazelcastConfiguration {

    private HazelcastInstance hazelcast;

    public HazelcastConfiguration(){
        hazelcast = Hazelcast.newHazelcastInstance();
    }

    public HazelcastInstance getHazelcast(){
        return hazelcast;
    }
}
