package de.martintraining.validator;



import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import javax.xml.validation.Validator;




public class TestInDateRange {
	
private static Validator validator;
    
    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void testDateRange() {
        SomeBean test = new SomeBean();
        test.date = convertToDateViaInstant(LocalDate.of(10001, 01, 01));
        
        Set<ConstraintViolation<SomeBean>> validate = validator.validate(test);
        System.out.println(validate);
        assertEquals(1, validate.size());
        assertEquals("date", validate.iterator().next().getPropertyPath().toString());
    }
    
    
    // new method - because others where outdated...
    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
          .atZone(ZoneId.systemDefault())
          .toInstant());
    }
    
    
    
    /*
    --> Mit Markus:
    
    https://sterl.org/2015/06/java-jsr-validation-for-dates/
    
    */
    

}

/* Gesucht nach: --> "convert local date to date"
 * 
 * 
 * Link: https://www.baeldung.com/java-date-to-localdate-and-localdatetime


4. Convert java.time.LocalDate to java.util.Date
Now that we have a good understanding of how to convert form the old data representation to the new one, let's have a look at converting in the other direction.

We'll discuss two possible ways of converting LocalDate to Date.

In the first one, we use a new valueOf(LocalDate date) method provided in java.sql.Date object which takes LocalDate as a parameter:

public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
    return java.sql.Date.valueOf(dateToConvert);
}
As we can see, it is effortless and intuitive. It uses local time zone for conversion (all is done under the hood, no need to worry).

In another, Java 8 example, we use an Instant object which we pass to the from(Instant instant) method of java.util.Date object:

public Date convertToDateViaInstant(LocalDate dateToConvert) {
    return java.util.Date.from(dateToConvert.atStartOfDay()
      .atZone(ZoneId.systemDefault())
      .toInstant());
}
You'll notice we make use of an Instant object here, and that we also need to care about time zones when doing this conversion.

Next, let's use a very similar solutions to convert a LocalDateTime to a Date object.
*/












