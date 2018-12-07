/**
 * statistics storage
 * 
 * 
 */
package com.n26.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.n26.domain.base.BaseObject;

public class Stastistics extends BaseObject {

	/**
	* 
	*/
	private static final long serialVersionUID = 2896964683264909339L;

	@JsonProperty("sum")
	private BigDecimal sum = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

	@JsonProperty("avg")
	private BigDecimal avg = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

	@JsonProperty("max")
	private BigDecimal max = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

	@JsonProperty("min")
	private BigDecimal min =BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

	@JsonProperty("count")
	private long count;

	@JsonIgnore
	private transient LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);

	public Stastistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg.setScale(2, BigDecimal.ROUND_HALF_UP);
		;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max.setScale(2, BigDecimal.ROUND_HALF_UP);
		;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * update the stats with stats from other stats time-slots
	 * 
	 * @param stats
	 */

	public void updateStastistics(Stastistics stats) {
		
		 if (this.getCount()==0 && stats.getCount()>0 ) {
			this.setAvg(stats.getAvg());
			this.setSum(stats.getSum());
			this.setCount(stats.getCount());
			this.setMin(stats.getMin());
			this.setMax(stats.getMax());
		}else if (stats.getCount()>0 && this.count>0){
			BigDecimal counts = new BigDecimal(stats.getCount() + this.getCount());
			this.setAvg(this.getSum().add(stats.getSum()).divide(counts, 2, RoundingMode.HALF_UP));
			this.setSum(this.getSum().add(stats.getSum()));
			this.setCount(stats.getCount() + this.getCount());
			this.setMax(this.getMax().max(stats.getMax()));
			this.setMin(this.getMin().min(stats.getMin()));
		}
			
		
	}
	/**
	 * add a transaction to the time-slots statistics
	 * 
	 * @param transaction
	 */
	public void addTransaction(Transaction transaction) {
	
		this.setMax(transaction.getAmount());
		this.setMin(transaction.getAmount());
		this.setCount(this.getCount() + 1);
		this.setSum(this.getSum().add(transaction.getAmount()));
		this.setAvg(this.getSum().divide(BigDecimal.valueOf(this.getCount()), 2, RoundingMode.HALF_UP));
	    //set this timestamp
		this.timestamp=transaction.getTimestamp();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stastistics [sum=");
		builder.append(sum);
		builder.append(", avg=");
		builder.append(avg);
		builder.append(", max=");
		builder.append(max);
		builder.append(", min=");
		builder.append(min);
		builder.append(", count=");
		builder.append(count);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
