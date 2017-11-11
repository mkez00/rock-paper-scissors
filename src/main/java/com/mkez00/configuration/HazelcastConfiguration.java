package com.mkez00.configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by michaelkezele on 2017-10-25.
 */
@Configuration
public class HazelcastConfiguration {

    private HazelcastInstance hazelcast;

    @PostConstruct
    public void init(){
        hazelcast = Hazelcast.newHazelcastInstance();
    }

    public HazelcastInstance getHazelcast(){
        return this.hazelcast;
    }
}
