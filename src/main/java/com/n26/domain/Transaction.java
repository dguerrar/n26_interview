package com.n26.domain;
/**
 * 
 * transaction for rest
 * 
 */
import com.n26.domain.base.BaseObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Transaction extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4385738774425398355L;
	
	
    @JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("timestamp")
	private LocalDateTime timestamp;
	public Transaction(BigDecimal amount, LocalDateTime timestamp) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}
	public Transaction() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [amount=");
		builder.append(amount);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
}
