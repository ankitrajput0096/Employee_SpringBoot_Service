
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
    "line1",
    "line2",
    "city",
    "state",
    "country",
    "zip_code"
})
public class Address {

    /**
     * line 1 of address
     * (Required)
     * 
     */
    @JsonProperty("line1")
    @JsonPropertyDescription("line 1 of address")
    private String line1;
    /**
     * line 2 of address
     * 
     */
    @JsonProperty("line2")
    @JsonPropertyDescription("line 2 of address")
    private String line2;
    /**
     * city of employee
     * (Required)
     * 
     */
    @JsonProperty("city")
    @JsonPropertyDescription("city of employee")
    private String city;
    /**
     * state of employee
     * (Required)
     * 
     */
    @JsonProperty("state")
    @JsonPropertyDescription("state of employee")
    private String state;
    /**
     * country name
     * (Required)
     * 
     */
    @JsonProperty("country")
    @JsonPropertyDescription("country name")
    private String country;
    /**
     * state zip code
     * (Required)
     * 
     */
    @JsonProperty("zip_code")
    @JsonPropertyDescription("state zip code")
    private Integer zipCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * line 1 of address
     * (Required)
     * 
     */
    @JsonProperty("line1")
    public String getLine1() {
        return line1;
    }

    /**
     * line 1 of address
     * (Required)
     * 
     */
    @JsonProperty("line1")
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * line 2 of address
     * 
     */
    @JsonProperty("line2")
    public String getLine2() {
        return line2;
    }

    /**
     * line 2 of address
     * 
     */
    @JsonProperty("line2")
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * city of employee
     * (Required)
     * 
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * city of employee
     * (Required)
     * 
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * state of employee
     * (Required)
     * 
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * state of employee
     * (Required)
     * 
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     * country name
     * (Required)
     * 
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * country name
     * (Required)
     * 
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * state zip code
     * (Required)
     * 
     */
    @JsonProperty("zip_code")
    public Integer getZipCode() {
        return zipCode;
    }

    /**
     * state zip code
     * (Required)
     * 
     */
    @JsonProperty("zip_code")
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
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
        sb.append(Address.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("line1");
        sb.append('=');
        sb.append(((this.line1 == null)?"<null>":this.line1));
        sb.append(',');
        sb.append("line2");
        sb.append('=');
        sb.append(((this.line2 == null)?"<null>":this.line2));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("zipCode");
        sb.append('=');
        sb.append(((this.zipCode == null)?"<null>":this.zipCode));
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
