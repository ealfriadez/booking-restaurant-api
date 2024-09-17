package com.boot.bookingrestaurantapi.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;

public class ReservationServiceImplTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Pachamama";
	private static final String ADDRESS = "EE.UU.";
	private static final String DESCRIPTION = "Cook Peruvian";	
	private static final String IMAGE = "ealfriadez@gmail.com";	
	private static final Long RESERVATION_ID = 1L;
	private static final Long PERSON_ID = 1L;
	private static final Long TURN_ID = 1L;
	private static final String NAME_TURN = "Noche";		
	private static final Date DATE = new Date();
	private static final String LOCATOR = "Pachamama";
	
	private static final Reservation RESERVATION = new Reservation();
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final Turn TURN = new Turn();
	private static final List<Turn> TURN_LIST = new ArrayList<>(); 
	
	private static final Optional<Turn> OPTIONAL_TURN = Optional.of(TURN);
	private static final Optional<Turn> OPTIONAL_TURN_EMPTY = Optional.empty();	
	
	private static final Optional<Restaurant> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);
	private static final Optional<Restaurant> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();	
	
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();	
		 
	private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest(); 
	private static final Optional<CreateReservationRest> OPTIONAL_RESERVATION_REST = Optional.of(CREATE_RESERVATION_REST);
	private static final Optional<CreateReservationRest> OPTIONAL_RESERVATION_REST_EMPTY = Optional.empty();
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private TurnRepository turnRepository;

	@Mock
	private ReservationRespository reservationRepository;
	
	@InjectMocks
	private ReservationServiceImpl reservationServiceImpl;	
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);
		
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setName(NAME);
		RESTAURANT.setAddress(ADDRESS);
		RESTAURANT.setDescription(DESCRIPTION);
		RESTAURANT.setImage(IMAGE);
		RESTAURANT.setTurns(TURN_LIST);
		
		TURN.setId(TURN_ID);
		TURN.setName(NAME_TURN);
		TURN.setRestaurant(RESTAURANT);
		
		RESERVATION.setId(RESERVATION_ID);
		RESERVATION.setLocator(LOCATOR);
		RESERVATION.setTurn(NAME_TURN);
		RESERVATION.setPerson(PERSON_ID);
		RESERVATION.setDate(DATE);
		RESERVATION.setRestaurant(RESTAURANT);
		
		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSON_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
	}
	
	@Test
	public void testCreateReservationRestaurantTurn() throws BookingException {		
		
		Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
		Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		
		reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
	}
}
