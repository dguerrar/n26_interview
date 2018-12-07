package com.n26.converter;
/**
 * 
 * converts an stastistics objects into a JSON using the GSON library
 * GSON has some problems converting BigDecimals, so custom converter is required
 * 
 */
import com.n26.domain.Stastistics;
import com.n26.generic.GenericModule;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;

public class StatisticConverter extends GenericModule implements JsonSerializer<Stastistics> {



	/**
	 * 
	 */
	private static final long serialVersionUID = 6862674999072366983L;

	public JsonElement serialize(final Stastistics stats, final Type type, final JsonSerializationContext context) {
		log.debug("-->validate (stats {})",stats);
		JsonObject result = new JsonObject();
		result.add("sum", new JsonPrimitive(stats.getSum().toString()));
		result.add("avg", new JsonPrimitive(stats.getAvg().toString()));
		result.add("max", new JsonPrimitive(stats.getMax().toString()));
		result.add("min", new JsonPrimitive(stats.getMin().toString()));
		result.add("count", new JsonPrimitive(stats.getCount()));
		log.debug("<--validate (result {})",result);
		return result;
	}

	@Override
	protected Class<?> getLogClass() {
		return StatisticConverter.class;
	}
}
