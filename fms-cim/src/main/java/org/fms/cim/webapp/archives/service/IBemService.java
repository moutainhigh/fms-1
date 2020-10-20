package org.fms.cim.webapp.archives.service;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface IBemService {

	public HashMap<String, Object> addBemInfo(JsonNode jsonNode)throws JsonParseException, JsonMappingException, IOException;

	
	
}
