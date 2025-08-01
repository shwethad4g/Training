package Day10Task;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "person")
public class Person {
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "age")
    private int age;
    @JacksonXmlProperty(localName = "gender")
    private String gender;
    @JacksonXmlProperty(localName = "qualification")
    private String qualification;
}
