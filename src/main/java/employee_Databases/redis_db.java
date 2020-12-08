package employee_Databases;

import redis.clients.jedis.Jedis;

public class redis_db {
	public void set_redis_data(String key, String json_data)
	{
		Jedis jedis = new Jedis("localhost");
		jedis.set(key, json_data);
		// Set expiry to 1 hour
		jedis.expire(key, 3600);
		jedis.close();
	}
}
