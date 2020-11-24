package di.ways.extra;

import org.springframework.stereotype.Component;

@Component("platformDefault")
public class PlatformDefault implements Platform {
    @Override
    public String getSimpleName() {
        return PlatformDefault.class.getSimpleName();
    }
}
