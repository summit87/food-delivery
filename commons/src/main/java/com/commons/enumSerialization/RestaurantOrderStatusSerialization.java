package com.commons.enumSerialization;

import com.commons.enums.ItemAvailabilityStatus;
import com.commons.enums.RestaurantOrderStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RestaurantOrderStatusSerialization
		extends JsonDeserializer<RestaurantOrderStatus> {

	@Override
	public RestaurantOrderStatus deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext)
			throws IOException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		RestaurantOrderStatus type = null;
		try {
			if (node != null) {
				type = RestaurantOrderStatus.valueOf((node.textValue()));
				if (type != null) {
					return type;
				}
			}
			log.error("unsupported event type {} ", node.textValue());
			throw new RuntimeException("Unsupported event type ");
		} catch (Exception e) {
			type = null;
			log.error("", e);
			throw new RuntimeException(
					"Unable to find order status event type");
		}
	}
}
