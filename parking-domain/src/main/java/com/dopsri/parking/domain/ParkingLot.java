package com.dopsri.parking.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(ParkingLotId.class)
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Id
	private int number;
	
	@Getter @Setter
	@Id
	private int level;
	
	@Getter @Setter
	@Id
	@Enumerated(EnumType.STRING)
	private ParkingLotStructure parkingLotStructure;
	
	@Getter @Setter
	@Enumerated(EnumType.STRING)
	private ParkingLotType parkingLotType;
	
	

}
