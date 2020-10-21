package org.fms.cim.webapp.archives.service;

import java.io.IOException;
import java.util.List;

import org.fms.cim.webapp.archives.domain.MeterDomain;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface IBillService {

	public List<MeterDomain> getMeters(JsonNode jsonNode)throws JsonParseException, JsonMappingException, IOException;

	public List<MeterDomain> getMetersBySettlement(JsonNode jsonNode)throws JsonParseException, JsonMappingException, IOException;

}
