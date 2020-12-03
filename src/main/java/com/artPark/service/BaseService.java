package com.artPark.service;

import com.artPark.common.plugin.BasicMapper;
import com.artPark.common.plugin.BasicModel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lbc on 2020/11/26  16:31.
 */
public class BaseService implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<Class, BasicMapper> modelMapper = new ConcurrentHashMap<Class, BasicMapper>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T get(String name){
        return (T)applicationContext.getBean(name);
    }

    public List find(BasicModel obj) {
        BasicMapper mapper = modelMapper.get(obj.getClass());
        if(mapper == null){
            mapper = addMapper(obj.getClass());
        }
        return mapper.find(obj);
    }

    public <T> T findOne(String pk,Class c) {
        BasicMapper mapper = modelMapper.get(c);
        if(mapper == null){
            mapper = addMapper(c);
        }
        Object a = mapper.findOne(pk);
        return (T)a;
    }

    public void delete(String pk,Class c) {
        BasicMapper mapper = modelMapper.get(c);
        if(mapper == null){
            mapper = addMapper(c);
        }
        mapper.delete(pk);
    }

    public Integer update(Object var1) {
        BasicMapper mapper = modelMapper.get(var1.getClass());
        if(mapper == null){
            mapper = addMapper(var1.getClass());
        }
        return mapper.update(var1);
    }

    public Integer insert(Object obj) {
        BasicMapper mapper = modelMapper.get(obj.getClass());
        if(mapper == null){
            mapper = addMapper(obj.getClass());
        }
        return mapper.insert(obj);
    }

    private <T> BasicMapper addMapper(Class c){
        String[] array = c.getName().split("\\.");
        String mapName = array[array.length-1];
        mapName = mapName.substring(0,1).toLowerCase()+mapName.substring(1)+"Mapper";
        BasicMapper mapper = get(mapName);
        modelMapper.put(c,mapper);
        return mapper;
    }
}
