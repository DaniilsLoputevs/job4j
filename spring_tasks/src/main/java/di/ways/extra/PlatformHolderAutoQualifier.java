package di.ways.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlatformHolderAutoQualifier {
    @Autowired
    // auto name-qualifier by: varName == beanName
    private Platform platformDefault;

    public PlatformHolderAutoQualifier() {
    }

    public String getMainPlatformName() {
        return this.platformDefault.getSimpleName();
    }
}
