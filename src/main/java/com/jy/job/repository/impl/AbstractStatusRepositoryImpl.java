package com.jy.job.repository.impl;

import com.jy.job.repository.XmlRepository;

/**
 * @ClassName AbstractStatusRepositoryImpl
 * @Description //TODO
 * @Author QiangJia
 * @Date 2020/1/10 16:20
 * @Version 1.0
 **/
public abstract class AbstractStatusRepositoryImpl<E> implements XmlRepository<E> {

    private E globalConfiguration = null;

    @Override
    public E load() {
        return globalConfiguration;
    }

    @Override
    public void save(E entity) {

    }

    public E getGlobalConfiguration() {
        return globalConfiguration;
    }

    public void setGlobalConfiguration(E globalConfiguration) {
        this.globalConfiguration = globalConfiguration;
    }
}
