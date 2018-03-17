package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Servicios {

	@JsonProperty(value="id")
	private Long id; 
	
	@JsonProperty(value ="agua")
	private boolean agua; 
	
	@JsonProperty(value = "ba�era")
	private boolean ba�era; 
	
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

	public Servicios(@JsonProperty(value ="agua")boolean agua, @JsonProperty(value = "ba�era")boolean ba�era, @JsonProperty(value = "cocineta")boolean cocineta,@JsonProperty(value = "parquedero") boolean parquedero, @JsonProperty(value = "piscina")boolean piscina,
			@JsonProperty(value = "recepcion24h")boolean recepcion24h,@JsonProperty(value = "restaurante") boolean restaurante, @JsonProperty(value = "sala")boolean sala,@JsonProperty(value = "tv") boolean tv, @JsonProperty(value = "wifi")boolean wifi,	@JsonProperty(value = "yacuzzi") boolean yacuzzi) {
	
		this.agua = agua;
		this.ba�era = ba�era;
		this.cocineta = cocineta;
		this.parquedero = parquedero;
		this.piscina = piscina;
		this.recepcion24h = recepcion24h;
		this.restaurante = restaurante;
		this.sala = sala;
		this.tv = tv;
		this.wifi = wifi;
		this.yacuzzi = yacuzzi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAgua() {
		return agua;
	}

	public void setAgua(boolean agua) {
		this.agua = agua;
	}

	public boolean isBa�era() {
		return ba�era;
	}

	public void setBa�era(boolean ba�era) {
		this.ba�era = ba�era;
	}

	public boolean isCocineta() {
		return cocineta;
	}

	public void setCocineta(boolean cocineta) {
		this.cocineta = cocineta;
	}

	public boolean isParquedero() {
		return parquedero;
	}

	public void setParquedero(boolean parquedero) {
		this.parquedero = parquedero;
	}

	public boolean isPiscina() {
		return piscina;
	}

	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}

	public boolean isRecepcion24h() {
		return recepcion24h;
	}

	public void setRecepcion24h(boolean recepcion24h) {
		this.recepcion24h = recepcion24h;
	}

	public boolean isRestaurante() {
		return restaurante;
	}

	public void setRestaurante(boolean restaurante) {
		this.restaurante = restaurante;
	}

	public boolean isSala() {
		return sala;
	}

	public void setSala(boolean sala) {
		this.sala = sala;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isYacuzzi() {
		return yacuzzi;
	}

	public void setYacuzzi(boolean yacuzzi) {
		this.yacuzzi = yacuzzi;
	}
	
	
	
	
	
	
	
	
	
}
