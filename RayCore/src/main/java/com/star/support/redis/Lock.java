package com.star.support.redis;

/**
 * @author liuna
 */
public interface Lock extends AutoCloseable {

    boolean lock();

    void unlock();

    String getLockKey();

    @Override
    void close();
}