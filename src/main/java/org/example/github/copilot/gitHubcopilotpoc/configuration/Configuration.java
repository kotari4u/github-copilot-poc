package org.example.github.copilot.gitHubcopilotpoc.configuration;

import org.example.github.copilot.gitHubcopilotpoc.domain.BookInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@org.springframework.context.annotation.Configuration

public class Configuration {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}
	
	@Bean
	public RedisTemplate<String, BookInfo> redisTemplate() {
		RedisTemplate<String, BookInfo> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
