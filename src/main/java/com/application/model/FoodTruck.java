package com.application.model;

/**
 * This serves as a java bean for Food truck details
 * @author Rachit
 *
 */
public class FoodTruck {

	private int objectid;
	private int locationid;
	private String status;
	private String permit;
	private String facilitytype;
	private String locationdescription;
	private String address;
	private String schedule;
	private String applicant;
	private String lot;
	private String fooditems;
	private Double latitude;
	private Double longitude;
	private String dayshours;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return objectid;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.objectid = id;
	}
	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationid;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationid = locationId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the permit
	 */
	public String getPermit() {
		return permit;
	}
	/**
	 * @param permit the permit to set
	 */
	public void setPermit(String permit) {
		this.permit = permit;
	}
	/**
	 * @return the facilityType
	 */
	public String getFacilityType() {
		return facilitytype;
	}
	/**
	 * @param facilityType the facilityType to set
	 */
	public void setFacilityType(String facilityType) {
		this.facilitytype = facilityType;
	}
	/**
	 * @return the locationDescription
	 */
	public String getLocationDescription() {
		return locationdescription;
	}
	/**
	 * @param locationDescription the locationDescription to set
	 */
	public void setLocationDescription(String locationDescription) {
		this.locationdescription = locationDescription;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}
	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	/**
	 * @return the applicant
	 */
	public String getApplicant() {
		return applicant;
	}
	/**
	 * @param applicant the applicant to set
	 */
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	/**
	 * @return the lot
	 */
	public String getLot() {
		return lot;
	}
	/**
	 * @param lot the lot to set
	 */
	public void setLot(String lot) {
		this.lot = lot;
	}
	/**
	 * @return the foodItems
	 */
	public String getFoodItems() {
		return fooditems;
	}
	/**
	 * @param foodItems the foodItems to set
	 */
	public void setFoodItems(String foodItems) {
		this.fooditems = foodItems;
	}
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * @return the dayshours
	 */
	public String getDayshours() {
		return dayshours;
	}
	/**
	 * @param dayshours the dayshours to set
	 */
	public void setDayshours(String dayshours) {
		this.dayshours = dayshours;
	}
	
}
