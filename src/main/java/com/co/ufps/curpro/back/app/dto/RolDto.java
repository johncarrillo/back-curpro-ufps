package com.co.ufps.curpro.back.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class RolDto {
	private Long id;
	private String nombre;
}
