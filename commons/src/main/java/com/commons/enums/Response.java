package com.commons.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
	private ResponseStatus responseStatus;
	private T responseBody;

	private Response(ResponseBuilder<T> builder) {
		this.responseStatus = builder.responseStatus;
		this.responseBody = builder.responseBody;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public T getResponseBody() {
		return responseBody;
	}

	public static class ResponseBuilder<T> {
		private ResponseStatus responseStatus;
		private T responseBody;

		public ResponseBuilder() {

		}

		public ResponseBuilder status(Status status, String statusCode) {
			this.responseStatus = new ResponseStatus(status, statusCode);
			return this;
		}

		public ResponseBuilder responseBody(T o){
			if (!ObjectUtils.isEmpty(o)) {
				this.responseBody = o;
			}
			return this;
		}

		public Response build() {
			return new Response<T>(this);
		}
	}
}
