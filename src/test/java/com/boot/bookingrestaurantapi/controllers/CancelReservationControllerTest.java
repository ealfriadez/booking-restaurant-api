package com.boot.bookingrestaurantapi.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.CancelReservationService;

public class CancelReservationControllerTest {

	private static final String LOCATOR = "Pachamama";
	
	private static final String SUCCESS_STATUS = "Succes";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	
	@Mock
	CancelReservationService cancelReservationService;
	
	@InjectMocks
	CancelReservationController controller;	
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);	
		
		Mockito.when(cancelReservationService.deleteReservation(LOCATOR)).thenReturn(LOCATOR);
	}
	
	@Test
	public void testDeleteReservation() throws BookingException {
		
		final BookingResponse<String> response = controller.deleteReservation(LOCATOR);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);		
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), LOCATOR);
	}

}
