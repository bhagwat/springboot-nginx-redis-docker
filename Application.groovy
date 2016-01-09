@Grab('org.springframework.boot:spring-boot-starter-redis:1.3.1.RELEASE')

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


@RestController
@Configuration
class Application {
	private final String KEY = "spring.boot.redis.webhit.counter"

	@Bean
 	JedisConnectionFactory jedisConnectionFactory() {
	     JedisConnectionFactory factory = new JedisConnectionFactory();
	     factory.setHostName("redis");
	     factory.setUsePool(true);
	     return factory;
 	}
 
	 @Bean
	 StringRedisTemplate redisTemplate() {
	     StringRedisTemplate redisTemplate = new StringRedisTemplate();
	     redisTemplate.setConnectionFactory(jedisConnectionFactory());
	     return redisTemplate;
	 }

	 @Autowired
	 StringRedisTemplate template
	 
	private String getUpdatedCounter(){
		def ops = template.opsForValue()
		String currentValue = ops.get(KEY)
		ops.set( KEY, currentValue ? (Integer.valueOf(currentValue )+ 1).toString() : "0")
		println("Counter: " + ops.get(KEY))
		return ops.get(KEY)
	}

    @RequestMapping("/")
    public String hello() {
        "Counter: " + getUpdatedCounter() +", Host:: "+getHostname()
    }

    String getHostname(){InetAddress.getLocalHost().getHostName() +"::"+ InetAddress.getLocalHost().getHostAddress()}
}