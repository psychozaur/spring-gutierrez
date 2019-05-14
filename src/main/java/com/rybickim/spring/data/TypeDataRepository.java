package com.rybickim.spring.data;

import com.rybickim.spring.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TypeDataRepository implements TypeDataDAO{

    private static final Logger logger = LoggerFactory.getLogger(TypeDataRepository.class);

    private Map<String, Type> types = new HashMap<>();

    public Map<String, Type> getTypes() {
        return types;
    }

    public void setTypes(Map<String, Type> types) {
        this.types = types;
    }

    @Override
    public Type[] getAll() {
        return types.values().toArray(new Type[types.values().size()]);
    }

    @Override
    public Type findById(String id) {
        if(logger.isDebugEnabled()) logger.debug("starting findById() method from TypeDataRepository: " + id);

        Type type = types.get(id);

        if(logger.isDebugEnabled()) logger.debug("ending findById() method from TypeDataRepository: " + type);


        return type;
    }
}
