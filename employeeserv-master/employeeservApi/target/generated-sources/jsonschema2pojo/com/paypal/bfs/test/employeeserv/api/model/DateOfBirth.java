
package com.paypal.bfs.test.employeeserv.api.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "day",
    "month",
    "year"
})
public class DateOfBirth {

    /**
     * day
     * (Required)
     * 
     */
    @JsonProperty("day")
    @JsonPropertyDescription("day")
    private Integer day;
    /**
     * month
     * (Required)
     * 
     */
    @JsonProperty("month")
    @JsonPropertyDescription("month")
    private Integer month;
    /**
     * year
     * (Required)
     * 
     */
    @JsonProperty("year")
    @JsonPropertyDescription("year")
    private Integer year;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * day
     * (Required)
     * 
     */
    @JsonProperty("day")
    public Integer getDay() {
        return day;
    }

    /**
     * day
     * (Required)
     * 
     */
    @JsonProperty("day")
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * month
     * (Required)
     * 
     */
    @JsonProperty("month")
    public Integer getMonth() {
        return month;
    }

    /**
     * month
     * (Required)
     * 
     */
    @JsonProperty("month")
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * year
     * (Required)
     * 
     */
    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    /**
     * year
     * (Required)
     * 
     */
    @JsonProperty("year")
    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DateOfBirth.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("day");
        sb.append('=');
        sb.append(((this.day == null)?"<null>":this.day));
        sb.append(',');
        sb.append("month");
        sb.append('=');
        sb.append(((this.month == null)?"<null>":this.month));
        sb.append(',');
        sb.append("year");
        sb.append('=');
        sb.append(((this.year == null)?"<null>":this.year));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
