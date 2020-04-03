package test1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhaolei
 * Create: 2019/8/27 11:49
 * Modified By:
 * Description:
 */
public class jaxb {
    
    

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(Arrays.asList("aaa","bbb","ccc"));

        IntStream.range(0, strings.size())
                .mapToObj(i -> {
                    System.out.println(i);
                    System.out.println(strings.get(i));
                    return i;
                }).collect(Collectors.toList());
    }
    
}
