package com.commons.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Getter
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
	
	public static class ResponseBuilder<T> {
		
		private ResponseStatus responseStatus;
		private T responseBody;
		
		public ResponseBuilder() {
		
		}
		
		public ResponseBuilder<T> status(Status status, String statusCode) {
			this.responseStatus = new ResponseStatus(status, statusCode);
			return this;
		}
		
		public ResponseBuilder<T> responseBody(T o) {
			if (!ObjectUtils.isEmpty(o)) {
				this.responseBody = o;
			}
			return this;
		}
		
		public Response<T> build() {
			return new Response<T>(this);
		}
	}
}
