/**
 * SerializationUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SerializationUtil
 * 描述: 序列化/反序列化 工具类
 * @author qye.zheng
 * 
 */
public final class SerializationUtil extends org.apache.commons.lang3.SerializationUtils
{
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private SerializationUtil()
	{
	}

	/**
	 * 
	 * @description 将对象序列化到一个指定文件中。
	 * @param obj
	 * @param filePath
	 * @author qye.zheng
	 */
	public final static void serialize(Serializable obj, String filePath)
	{
		try {
			serialize(obj, new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @description 将指定序列化文件反序列化为一个对象。
	 * @param filePath
	 * @return
	 * @author qye.zheng
	 */
	public final static Serializable deserialize(String filePath)
	{
		try {
			return deserialize(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @description 将一个Map中的key和value全部序列化，并生成一个新的Map。
	 * 被序列化的Map中的key和value必须都是Serializable的。
	 * @param m
	 * @return
	 * @author qye.zheng
	 */
	public final static Map<byte[], byte[]> serialize(Map<?, ?> m)
	{
		if (m == null || m.isEmpty())
		{
			throw new NullPointerException("map is null or empty.");
		}
		Map<byte[], byte[]> newMap = new HashMap<byte[], byte[]>();
		for (Object obj : m.keySet())
		{
			byte[] key = serialize((Serializable) obj);
			byte[] value = serialize((Serializable) m.get(obj));
			newMap.put(key, value);
		}
		return newMap;
	}

	/**
	 * 
	 * @description 将已序列化过内部元素的Map反序列化。
	 * @param m
	 * @return
	 * @author qye.zheng
	 */
	public final static Map<?, ?> deserialize(Map<byte[], byte[]> m)
	{
		if (m == null || m.isEmpty())
		{
			throw new NullPointerException("map is null or empty.");
		}
		Map<Object, Object> newMap = new HashMap<Object, Object>();
		for (byte[] obj : m.keySet())
		{
			Object key = deserialize(obj);
			Object value = deserialize(m.get(obj));
			newMap.put(key, value);
		}
		return newMap;
	}

	/**
	 * 
	 * @description 将指定List中所有元素序列化，并生成新的List，被序列化的List中元素必须是Serializable的。
	 * @param l
	 * @return
	 * @author qye.zheng
	 */
	public final static List<byte[]> serialize(List<?> l)
	{
		if (l == null || l.size() == 0)
		{
			throw new NullPointerException("list is null or empty.");
		}
		List<byte[]> newList = new ArrayList<byte[]>();
		for (Object obj : l)
		{
			byte[] e = serialize((Serializable) obj);
			newList.add(e);
		}
		return newList;
	}
	
	/**
	 * 
	 * @description 将内部元素已经序列化的List反序列化。
	 * @param l
	 * @return
	 * @author qye.zheng
	 */
	public final static List<?> deserialize(List<byte[]> l)
	{
		if (l == null || l.size() == 0)
		{
			throw new NullPointerException("list is null or empty.");
		}
		List<Object> newList = new ArrayList<Object>();
		for (byte[] obj : l)
		{
			Object e = deserialize(obj);
			newList.add(e);
		}
		return newList;
	}

	/**
	 * 
	 * @description 将指定Set的所有元素序列化，并生成新的Set，被序列化的Set的元素必须是Serializable的。
	 * @param s
	 * @return
	 * @author qye.zheng
	 */
	public final static Set<byte[]> serialize(Set<?> s)
	{
		if (s == null || s.size() == 0)
		{
			throw new NullPointerException("set is null or empty.");
		}
		Set<byte[]> newSet = new HashSet<byte[]>();
		for (Object obj : s)
		{
			byte[] e = serialize((Serializable) obj);
			newSet.add(e);
		}
		return newSet;
	}

	/**
	 * 
	 * @description 将内部元素已经序列化的Set反序列化。
	 * @param s
	 * @return
	 * @author qye.zheng
	 */
	public final static Set<?> deserialize(Set<byte[]> s)
	{
		if (s == null || s.size() == 0)
		{
			throw new NullPointerException("set is null or empty.");
		}
		Set<Object> newSet = new HashSet<Object>();
		for (byte[] obj : s)
		{
			Object e = deserialize(obj);
			newSet.add(e);
		}
		return newSet;
	}
}
