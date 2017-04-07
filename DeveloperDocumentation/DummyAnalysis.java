import java.io.PrintStream;

import de.uni_hildesheim.sse.kernel_haven.SetUpException;
import de.uni_hildesheim.sse.kernel_haven.build_model.BuildModel;
import de.uni_hildesheim.sse.kernel_haven.config.Configuration;
import de.uni_hildesheim.sse.kernel_haven.util.ExtractorException;

public class DummyAnalysis extends AbstractAnalysis {

    public DummyAnalysis(Configuration config) {
        super(config);
    }

    
    public int doAnalysis(BuildModel bm) {
        return bm.getSize();
    }
    
    @Override
    public void run() {
        try {
            bmProvider.start(config.getBuildConfiguration());
            vmProvider.start(config.getVariabilityConfiguration());

            int result;
            result = doAnalysis(bmProvider.getResult());
            
            PrintStream out = createResultStream("result.txt");
            out.println("" + result);
            out.close();
        } catch (ExtractorException e) {
            LOGGER.logException("Error reading build model", e);
        } catch (SetUpException e) {
            LOGGER.logException("Error in configuration", e);
        }
        
    }

}
