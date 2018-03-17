package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonaOperador extends RelacionUniandino{

	
		public PersonaOperador(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre, @JsonProperty(value="rol")String rol,@JsonProperty(value="carnet") Integer carnet) {
		
		super(id, nombre, rol, carnet);
		
	}
	
	
	
	
}
