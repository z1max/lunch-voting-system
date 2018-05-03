package com.z1max.lunchvotingsystem.util;

import com.z1max.lunchvotingsystem.model.AbstractBaseEntity;

public class Util {

    public static void checkNew(AbstractBaseEntity entity){
        if (!entity.isNew()){
            throw new IllegalArgumentException(entity + " must bew new (id = mull)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id){
        if (entity.isNew()){
            entity.setId(id);
        } else if (entity.getId() != id){
            throw new IllegalArgumentException(entity + " must be with id = " + id);
        }
    }
}
