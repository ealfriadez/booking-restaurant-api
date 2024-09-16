package com.boot.bookingrestaurantapi.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.responses.BookingResponse;

public class ReservationServiceImplTest {

	private static final Long RESERVATION_ID = 1L;
	private static final Long PERSON_ID = 1L;
	private static final String TURN = "Noche";		
	private static final Date DATE = new Date();
	private static final String LOCATOR = "Pachamama";
	
	private static final Reservation RESERVATION = new Reservation();
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();	
		 
	private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest(); 
	
	@Mock
	RestaurantRepository restaurantRepository;
	
	@Autowired
	private TurnRepository turnRepository;

	@Autowired
	private ReservationRespository reservationRepository;
	
	@InjectMocks
	ReservationServiceImpl reservationServiceImpl;	
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);		
	
		RESERVATION.setId(RESERVATION_ID);
		RESERVATION.setLocator(LOCATOR);
		RESERVATION.setTurn(TURN);
		RESERVATION.setPerson(PERSON_ID);
		RESERVATION.setDate(DATE);
		RESERVATION.setRestaurant(RESTAURANT);
		
		
		
	}
	
	@Test
	public void testGetReservation() throws BookingException {

		Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESERVATION);
		
		ReservationRest response = reservationServiceImpl.getReservation(RESERVATION_ID);
		
		assertEquals(response.getDate(), DATE);
	}

	@Test
	public void testCreateReservation() {
		fail("Not yet implemented");
	}

}
