package org.roguepanda.mod.client.framework;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

//http://stackoverflow.com/questions/664896/get-the-vk-int-from-an-arbitrary-char-in-java, first answer

public class KeyMappingUtil
{
	public static Map<String, Integer> keyTextToCode = new HashMap<String, Integer>();
	
	static {
		try
		{
			Field[] fields = KeyEvent.class.getDeclaredFields();
			for(Field field : fields)
			{
				String name = field.getName();
				if(name.startsWith("VK_"))
				{
					keyTextToCode.put(name.substring("VK_".length()).toUpperCase(), field.getInt(null));
				}
			}
		} catch (IllegalArgumentException e)
		{
			throw new RuntimeException("Error retrieving keyboard mappings", e);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException("Error retrieving keyboard mappings", e);
		}
	}
	
	public static Integer getCode(String key)
	{
		return keyTextToCode.get(key);
	}
}
