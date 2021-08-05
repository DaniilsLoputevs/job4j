package di.ways.extra;

import org.springframework.stereotype.Component;

@Component("platformNew")
public class PlatformNew implements Platform {
    @Override
    public String getSimpleName() {
        return PlatformNew.class.getSimpleName();
    }
}
