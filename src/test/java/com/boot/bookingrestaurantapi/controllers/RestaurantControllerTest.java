package com.boot.bookingrestaurantapi.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;

public class RestaurantControllerTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String RESTAURANT_NAME = "Pachamama";
	private static final String RESTAURANT_ADDRESS = "EE.UU.";
	private static final String RESTAURANT_DESCRIPTION = "Cook Peruvian";	
	
	private static final RestaurantRest RESTAURANT = new RestaurantRest();
	
	private static final String SUCCESS_STATUS = "Succes";
	
	@Mock
	RestaurantService restaurantService;
	
	@InjectMocks
	RestaurantController controller;	
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);	
		
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setName(RESTAURANT_NAME);
		RESTAURANT.setAddress(RESTAURANT_ADDRESS);
		RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
	}	
	
	@Test
	public void testGetRestaurantById() throws BookingException {
		
		//Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT);
		
		BookingResponse<RestaurantRest> response = controller.getRestaurantById(RESTAURANT_ID);
			
		assertEquals(response.getStatus(), SUCCESS_STATUS);
	}

}
