import de.uni_hildesheim.sse.kernel_haven.SetUpException;
import de.uni_hildesheim.sse.kernel_haven.build_model.IBuildExtractorFactory;
import de.uni_hildesheim.sse.kernel_haven.build_model.IBuildModelExtractor;
import de.uni_hildesheim.sse.kernel_haven.config.BuildExtractorConfiguration;

public class DummyBuildExtractorFactory implements IBuildExtractorFactory {

    @Override
    public IBuildModelExtractor create(BuildExtractorConfiguration config) throws SetUpException {
        return new DummyBuildExtractor(config);
    }

}
