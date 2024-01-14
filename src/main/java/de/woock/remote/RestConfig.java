package de.woock.remote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestConfig {
	
	@Bean
	RestClient restClient (RestClient.Builder builder) {
		return builder.requestFactory(new JdkClientHttpRequestFactory())
				      .baseUrl("http://localhost:8081/api")
				      .build();
	}

    @Bean
    VereinClient vereinClient(RestClient restClient) {
        return HttpServiceProxyFactory.builder()
                                      .exchangeAdapter(RestClientAdapter.create(restClient))
                                      .build()
                                      .createClient(VereinClient.class);
    }
}
