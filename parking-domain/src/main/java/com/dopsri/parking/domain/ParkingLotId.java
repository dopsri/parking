package com.dopsri.parking.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private int number;	
	
	@Getter @Setter
	private int level;
	
	@Getter @Setter
	private ParkingLotStructure parkingLotStructure;

}
