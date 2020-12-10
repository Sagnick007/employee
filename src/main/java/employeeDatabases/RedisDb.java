package employeeDatabases;

import redis.clients.jedis.Jedis;

public class RedisDb {
	public void setRedisData(String key, String json_data)
	{
		Jedis jedis = new Jedis("localhost");
		jedis.set(key, json_data);
		// Set expiry to 1 hour
		jedis.expire(key, 3600);
		jedis.close();
	}
	
	public String getRedisData(String key)
	{
		Jedis jedis = new Jedis("localhost");
		String data = jedis.get(key);
		jedis.close();
		return data;
	}
}
