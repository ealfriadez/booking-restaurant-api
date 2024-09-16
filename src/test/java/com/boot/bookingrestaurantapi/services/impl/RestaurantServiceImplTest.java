package com.boot.bookingrestaurantapi.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;

public class RestaurantServiceImplTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Pachamama";
	private static final String ADDRESS = "EE.UU.";
	private static final String DESCRIPTION = "Cook Peruvian";	
	private static final String IMAGE = "ealfriadez@gmail.com";	
	
	private static final Restaurant RESTAURANT1 = new Restaurant();
	private static final Restaurant RESTAURANT2 = new Restaurant();
	private static final Restaurant RESTAURANT3 = new Restaurant();
	private static final Optional<Restaurant> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT1);
	private static final Optional<Restaurant> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();
	
	private static final List<Turn> TURN_LIST = new ArrayList<>(); 
	private static final List<Restaurant> RESTAURANT_LIST = new ArrayList<>();
	private static final List<Reservation> RESERVATION_LIST = new ArrayList<>();
	private static final List<Board> BOARD_LIST = new ArrayList<>();	
	
	@Mock
	RestaurantRepository restaurantRepository;
	
	@InjectMocks
	RestaurantServiceImpl restaurantServiceImpl;	
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);		
		
		RESTAURANT1.setId(RESTAURANT_ID);
		RESTAURANT1.setName(NAME);
		RESTAURANT1.setAddress(ADDRESS);
		RESTAURANT1.setDescription(DESCRIPTION);
		RESTAURANT1.setImage(IMAGE);
		RESTAURANT1.setReservations(RESERVATION_LIST);
		RESTAURANT1.setBoards(BOARD_LIST);
		RESTAURANT1.setTurns(TURN_LIST);
		
		RESTAURANT2.setId(RESTAURANT_ID);
		RESTAURANT2.setName(NAME);
		RESTAURANT2.setAddress(ADDRESS);
		RESTAURANT2.setDescription(DESCRIPTION);
		RESTAURANT2.setImage(IMAGE);
		RESTAURANT2.setReservations(RESERVATION_LIST);
		RESTAURANT2.setBoards(BOARD_LIST);
		RESTAURANT2.setTurns(TURN_LIST);
		
		RESTAURANT3.setId(RESTAURANT_ID);
		RESTAURANT3.setName(NAME);
		RESTAURANT3.setAddress(ADDRESS);
		RESTAURANT3.setDescription(DESCRIPTION);
		RESTAURANT3.setImage(IMAGE);
		RESTAURANT3.setReservations(RESERVATION_LIST);
		RESTAURANT3.setBoards(BOARD_LIST);
		RESTAURANT3.setTurns(TURN_LIST);
		
		RESTAURANT_LIST.add(RESTAURANT1);
		RESTAURANT_LIST.add(RESTAURANT2);
		RESTAURANT_LIST.add(RESTAURANT3);		
	}
	
	@Test
	public void testGetRestaurantById() throws BookingException {
		
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		
		RestaurantRest response = restaurantServiceImpl.getRestaurantById(OPTIONAL_RESTAURANT.get().getId());
		
		assertEquals(response.getId(), RESTAURANT_ID);
		assertEquals(response.getName(), NAME);		
		assertEquals(response.getAddress(), ADDRESS);
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getImage(), IMAGE);		
		assertEquals(response.getTurns(), TURN_LIST);
	}
	
	@Test(expected = BookingException.class)
	public void testGetRestaurantByIdException() throws BookingException {
		
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);
		
		restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
		
		fail();
	}

	
	@Test
	public void testGetRestaurants() throws BookingException{
			
		Mockito.when(restaurantRepository.findAll()).thenReturn(RESTAURANT_LIST);
		final List<RestaurantRest> response = restaurantServiceImpl.getRestaurants();
		
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 3);
	}
}
