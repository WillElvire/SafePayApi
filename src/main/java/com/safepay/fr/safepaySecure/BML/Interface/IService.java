package com.safepay.fr.safepaySecure.BML.Interface;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    public Object save(T t);
    public Object findAll();
    public Object findById(String id);
    public Object findById(int id);
    public default void deleteById(int id) {
    }

    public default void deleteById(String id) {

    }
}
