package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import java.util.List;

/**
 * Created by dolplads on 05/09/16.
 */
@Embeddable
@Data
@NoArgsConstructor
public class Address {
    private String streetName;
    private String cityCode;
    private String cityName;
    private String countryName;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> stringList;

    public Address(String streetName, String cityCode, String cityName, String countryName) {
        this.streetName = streetName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.countryName = countryName;
    }
}
