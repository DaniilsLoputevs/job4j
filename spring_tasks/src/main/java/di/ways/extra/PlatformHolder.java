package di.ways.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PlatformHolder {
    @Autowired
//    @Qualifier("platformNew")
    @Qualifier("platformDefault")
    private Platform mainPlat;

    public PlatformHolder() {
    }

    public String getMainPlatformName() {
        return this.mainPlat.getSimpleName();
    }

}
