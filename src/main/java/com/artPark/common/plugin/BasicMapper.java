package com.artPark.common.plugin;

import java.util.List;

/**
 * @author lbc
 *
 */
public interface BasicMapper<T> {
	List<T> find(Object obj);

	T findOne(String pk);

	Integer findCount(Object obj);

	void delete(String pk);

	<S extends T> Integer insert(S obj);

	Integer update(T var1);

}
