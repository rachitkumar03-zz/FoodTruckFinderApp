package com.application.controller;

import java.text.ParseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.application.utils.BaseUtils;

@Path("/find")
public class FoodTruckFinderServlet {

	@GET
	@Path("{currentPos}")
	@Produces("application/json")
	public Response getData (@PathParam("currentPos") String currentPos)
			throws ParseException {

		BaseUtils utils = new BaseUtils();
		
		if (!utils.isValidRequest(currentPos))
			return Response.status(422).build();
		
		utils.getResponseData();
		utils.getResponseData();
		return Response.
				status(200).
				entity(utils.getResponseData()).
				build();
	}
}
