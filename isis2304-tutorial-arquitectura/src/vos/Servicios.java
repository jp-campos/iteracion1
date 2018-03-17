package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Servicios {

	
	@JsonProperty(value ="agua")
	private boolean agua; 
	
	@JsonProperty(value = "bañera")
	private boolean bañera; 
	
	@JsonProperty(value = "cocineta")
	private boolean cocineta;
	
	@JsonProperty(value = "parquedero")
	private boolean parquedero;
	
	
	@JsonProperty(value = "piscina")
	private boolean piscina;
	
	@JsonProperty(value = "recepcion24h")
	private boolean recepcion24h;
	
	@JsonProperty(value = "restaurante")
	private boolean restaurante;
	
	@JsonProperty(value = "sala")
	private boolean sala;
	
	@JsonProperty(value = "tv")
	private boolean tv;
	
	@JsonProperty(value = "wifi")
	private boolean wifi;
	
	@JsonProperty(value = "yacuzzi")
	private boolean yacuzzi;
	
	
}
