package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by dolplads on 05/09/16.
 */
@Embeddable
@Data
@NoArgsConstructor
public class Address {
    @Size(max = 100)
    private String streetName;
    @Size(max = 4)
    private String cityCode;
    @Size(max = 100)
    private String cityName;
    @Size(max = 100)
    private String countryName;

    public Address(String streetName, String cityCode, String cityName, String countryName) {
        this.streetName = streetName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.countryName = countryName;
    }
}
