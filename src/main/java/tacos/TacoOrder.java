package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

//@Data
@Table("Taco_Order") //TacoOrder class is mapped to a table named "Taco_Order".
public class TacoOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private Date placeAt;
	
	@NotBlank(message="Delivery name is required")
	private String deliveryName;
	
	@NotBlank(message="Delivery street is required")
	private String deliveryStreet;
	
	@NotBlank(message="Delivery city is required")
	private String deliveryCity;
	
	@NotBlank(message="Delivery state is required")
	private String deliveryState;
	
	@NotBlank(message="Delivery zip is required")
	@Digits(integer=5, fraction=0, message="Invalid CVV")
	private String deliveryZip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;	//contains 3 digits
	
	private List<Taco> tacos = new ArrayList<>();
	
	public TacoOrder() {}

	public TacoOrder(Long id, Date placeAt, @NotBlank(message = "Delivery name is required") String deliveryName,
			@NotBlank(message = "Delivery street is required") String deliveryStreet,
			@NotBlank(message = "Delivery city is required") String deliveryCity,
			@NotBlank(message = "Delivery state is required") String deliveryState,
			@NotBlank(message = "Delivery zip is required") @Digits(integer = 5, fraction = 0, message = "Invalid CVV") String deliveryZip,
			@CreditCardNumber(message = "Not a valid credit card number") String ccNumber,
			@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY") String ccExpiration,
			@Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCVV, List<Taco> tacos) {
		this.id = id;
		this.placeAt = placeAt;
		this.deliveryName = deliveryName;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryState = deliveryState;
		this.deliveryZip = deliveryZip;
		this.ccNumber = ccNumber;
		this.ccExpiration = ccExpiration;
		this.ccCVV = ccCVV;
		this.tacos = tacos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPlaceAt() {
		return placeAt;
	}

	public void setPlaceAt(Date placeAt) {
		this.placeAt = placeAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public void setDeliveryStreet(String deliveryStreet) {
		this.deliveryStreet = deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryZip() {
		return deliveryZip;
	}

	public void setDeliveryZip(String deliveryZip) {
		this.deliveryZip = deliveryZip;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public String getCcCVV() {
		return ccCVV;
	}

	public void setCcCVV(String ccCVV) {
		this.ccCVV = ccCVV;
	}

	public List<Taco> getTacos() {
		return tacos;
	}

	public void setTacos(List<Taco> tacos) {
		this.tacos = tacos;
	}

	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
	

}
