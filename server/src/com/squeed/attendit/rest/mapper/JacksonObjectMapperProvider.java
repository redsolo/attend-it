package com.squeed.attendit.rest.mapper;

import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;


/**
 * JAX-RS provider used to configure the Jackson JSON de/serialization 
 * of dates. 
 * 
 * @author Erik
 */
@Provider
@Produces("application/json")
public class JacksonObjectMapperProvider implements
		ContextResolver<ObjectMapper> {

	public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	static {
		final SimpleDateFormat iso8601 = new SimpleDateFormat(
				DateAdapter.ISO8601_PATTERN);

		OBJECT_MAPPER.getDeserializationConfig().setDateFormat(iso8601);
		OBJECT_MAPPER.getSerializationConfig().setDateFormat(iso8601);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return OBJECT_MAPPER;
	}
}

