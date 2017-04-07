import de.uni_hildesheim.sse.kernel_haven.SetUpException;
import de.uni_hildesheim.sse.kernel_haven.build_model.BuildModel;
import de.uni_hildesheim.sse.kernel_haven.build_model.BuildModelProvider;
import de.uni_hildesheim.sse.kernel_haven.build_model.IBuildModelExtractor;
import de.uni_hildesheim.sse.kernel_haven.config.BuildExtractorConfiguration;
import de.uni_hildesheim.sse.kernel_haven.util.ExtractorException;

/**
 * A simple dummy build extractor.
 * 
 * This expects the build_extractor.throw_exception to decide whether to throw an exception
 * or return an empty build model.
 */
public class DummyBuildExtractor implements IBuildModelExtractor, Runnable {

    private boolean throwException;
    
    private BuildModelProvider provider;
    
    private boolean killed;
    
    public DummyBuildExtractor(BuildExtractorConfiguration config) throws SetUpException {
        String exceptionProp = config.getProperty("build_extractor.throw_exception");
        if (exceptionProp == null) {
            throw new SetUpException("No build_extractor.throw_exception setting");
        }
        
        throwException = Boolean.parseBoolean(exceptionProp);
    }
    
    @Override
    public void start() {
        Thread th = new Thread(this);
        th.setName("DummyBuildExtractor");
        th.start();
    }

    @Override
    public void stop() {
        killed = true;
    }

    @Override
    public void setProvider(BuildModelProvider provider) {
        this.provider = provider;
    }
    
    @Override
    public void run() {
        if (throwException) {
            if (!killed) { // make sure that we are not stop()ed yet
                // "throw" an exception
                provider.setException(new ExtractorException("Dummy exception specified in config"));
            }
            
            
        } else {
            if (!killed) { // make sure that we are not stop()ed yet
                
                // just return an empty build model
                provider.setResult(new BuildModel());
            }
        }
    }

}
