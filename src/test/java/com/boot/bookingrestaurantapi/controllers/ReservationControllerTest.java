package com.boot.bookingrestaurantapi.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;

public class ReservationControllerTest {

	private static final Long RESERVATION_ID = 1L;
	private static final Long RESTAURANT_ID = 1L;
	private static final Long PERSON_ID = 1L;
	private static final Long TURN_ID = 1L;		
	private static final Date DATE = new Date();
	private static final String LOCATOR = "Pachamama";
	
	private static final ReservationRest RESERVATION_REST = new ReservationRest();	 
	private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest(); 
	
	private static final String SUCCESS_STATUS = "Succes";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	
	@Mock
	ReservationService reservationService;
	
	@InjectMocks
	ReservationController controller;	
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);			
		
		RESERVATION_REST.setLocator(LOCATOR);
		RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		RESERVATION_REST.setDate(DATE);
		RESERVATION_REST.setPerson(PERSON_ID);
		RESERVATION_REST.setTurnId(TURN_ID);
		
		CREATE_RESERVATION_REST.setDate(DATE);		
		CREATE_RESERVATION_REST.setPerson(PERSON_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		
		Mockito.when(reservationService.getReservation(RESERVATION_ID)).thenReturn(RESERVATION_REST);
		Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(RESERVATION_REST.getLocator());
	}
	
	@Test
	public void testGetReservationById() throws BookingException {
		
		final BookingResponse<ReservationRest> response = controller.getReservationById(RESERVATION_ID);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);		
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_REST);
	}

	@Test
	public void testCreateReservation() throws BookingException {
		
		final BookingResponse<String> response = controller.createReservation(CREATE_RESERVATION_REST);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);		
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_REST.getLocator());
	}

}
