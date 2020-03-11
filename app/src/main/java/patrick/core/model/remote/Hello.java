package patrick.core.model.remote;

//Ipotizzando XML:
//<Hello>
//    <mHello>Hello World</mHello>
//</Hello>

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Hello")
public class Hello {

    @Element(name = "mHello")
    private String helloWorld;

    public Hello() {}

    public String getHelloWorld() {
        return this.helloWorld;
    }
}
