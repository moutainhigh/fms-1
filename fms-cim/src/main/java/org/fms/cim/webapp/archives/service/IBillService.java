package org.fms.cim.webapp.archives.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;

public interface IBillService {

	public List<MeterDomain> getMeters(JsonNode jsonNode)throws JsonParseException, JsonMappingException, IOException;

	public List<MeterDomain> getMetersBySettlement(JsonNode jsonNode)throws JsonParseException, JsonMappingException, IOException;

}
