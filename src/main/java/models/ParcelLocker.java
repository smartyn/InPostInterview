package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParcelLocker {

    private @Getter @Setter String name;

    @JsonProperty("address_details")
    private @Getter @Setter AddressDetails addressDetails;

    private @Getter @Setter Coordinates location;
}
