package com.boot.bookingrestaurantapi.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;

public class RestaurantControllerTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Pachamama";
	private static final String ADDRESS = "EE.UU.";
	private static final String DESCRIPTION = "Cook Peruvian";	
	private static final String IMAGE = "ealfriadez@gmail.com";		
	
	private static final RestaurantRest RESTAURANT_REST_1 = new RestaurantRest();
	private static final RestaurantRest RESTAURANT_REST_2 = new RestaurantRest();
	private static final RestaurantRest RESTAURANT_REST_3 = new RestaurantRest();
	
	private static final List<TurnRest> TURN_LIST = new ArrayList<>(); 
	private static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>(); 
	
	private static final String SUCCESS_STATUS = "Succes";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	
	@Mock
	RestaurantService restaurantService;
	
	@InjectMocks
	RestaurantController controller;	
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);			
		
		RESTAURANT_REST_1.setId(RESTAURANT_ID);
		RESTAURANT_REST_1.setName(NAME);
		RESTAURANT_REST_1.setAddress(ADDRESS);
		RESTAURANT_REST_1.setDescription(DESCRIPTION);
		RESTAURANT_REST_1.setImage(IMAGE);
		RESTAURANT_REST_1.setTurns(TURN_LIST);
		
		RESTAURANT_REST_2.setId(RESTAURANT_ID);
		RESTAURANT_REST_2.setName(NAME);
		RESTAURANT_REST_2.setAddress(ADDRESS);
		RESTAURANT_REST_2.setDescription(DESCRIPTION);
		RESTAURANT_REST_2.setImage(IMAGE);
		RESTAURANT_REST_2.setTurns(TURN_LIST);
		
		RESTAURANT_REST_3.setId(RESTAURANT_ID);
		RESTAURANT_REST_3.setName(NAME);
		RESTAURANT_REST_3.setAddress(ADDRESS);
		RESTAURANT_REST_3.setDescription(DESCRIPTION);
		RESTAURANT_REST_3.setImage(IMAGE);
		RESTAURANT_REST_3.setTurns(TURN_LIST);
		
		RESTAURANT_REST_LIST.add(RESTAURANT_REST_1);
		RESTAURANT_REST_LIST.add(RESTAURANT_REST_2);
		RESTAURANT_REST_LIST.add(RESTAURANT_REST_3);
		
		Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST_1);
		Mockito.when(restaurantService.getRestaurants()).thenReturn(RESTAURANT_REST_LIST);
	}	
	
	@Test
	public void testGetRestaurantById() throws BookingException {		
		
		final BookingResponse<RestaurantRest> response = controller.getRestaurantById(RESTAURANT_ID);
			
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);		
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST_1);
	}
	
	@Test
	public void testGetRestaurants() throws BookingException {
		
		final BookingResponse<List<RestaurantRest>> response = controller.getRestaurants();
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);		
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST_LIST);
	}

}
