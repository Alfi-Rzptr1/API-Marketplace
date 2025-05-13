package com.rezeptor.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonConverter implements AttributeConverter<Map<String, Object>, String>{

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public String convertToDatabaseColumn(Map<String, Object> attribute) {
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (IOException e) {
			throw new IllegalArgumentException("Error serializing JSON",e);
		}
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, new TypeReference<Map<String,Object>>() {});
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Error deserializing JSON",e);
		}
	}

}
