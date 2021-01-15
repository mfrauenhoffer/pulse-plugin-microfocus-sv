package com.microfocus.pulse.svplugin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microfocus.adm.pulse.pluginapi.chain.PulseChainStepConfigAware;
import com.microfocus.adm.pulse.pluginapi.chain.PulseChainStepPlugin;
import com.microfocus.adm.pulse.pluginapi.chain.PulseChainStepPluginException;
import com.microfocus.adm.pulse.pluginapi.chain.PulseConsoleLogger;
import com.microfocus.adm.pulse.pluginapi.chain.PulseConsoleLoggerAware;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.PulseChainStepPluginDefinition;
import com.microfocus.adm.pulse.pluginapi.chain.dto.PulseChainContext;
import com.microfocus.adm.pulse.pluginapi.chain.dto.PulseChainStepPluginCategory;
import com.microfocus.adm.pulse.pluginapi.chain.dto.PulseState;

import com.microfocus.pulse.svplugin.model.SvAction;
import com.microfocus.pulse.svplugin.model.SvSearchContext;
import com.microfocus.sv.svconfigurator.core.IDataModel;
import com.microfocus.sv.svconfigurator.core.IPerfModel;
import com.microfocus.sv.svconfigurator.core.IProject;
import com.microfocus.sv.svconfigurator.core.IService;
import com.microfocus.sv.svconfigurator.core.impl.exception.ProjectBuilderException;
import com.microfocus.sv.svconfigurator.core.impl.processor.Credentials;
import com.microfocus.sv.svconfigurator.processor.DeployProcessor;
import com.microfocus.sv.svconfigurator.processor.DeployProcessorInput;
import com.microfocus.sv.svconfigurator.processor.IDeployProcessor;
import com.microfocus.sv.svconfigurator.serverclient.ICommandExecutor;
import com.microfocus.sv.svconfigurator.serverclient.impl.CommandExecutorFactory;
import com.microfocus.sv.svconfigurator.build.ProjectBuilder;

@PulseChainStepPluginDefinition(groupId = "com.microfocus.pulse", pluginId = "svplugin", version = "0", displayName = "Micro Focus Service Virtualization Controller", category = PulseChainStepPluginCategory.OTHER, description = "Allows control of virtual services within Micro Focus Service Virtualization during a deployment.")
public class SvPlugin
        implements PulseChainStepPlugin, PulseConsoleLoggerAware, PulseChainStepConfigAware<PluginConfig> {

    private PulseConsoleLogger consoleLogger;
    private PluginConfig config;

    @Override
    public void setConsoleLogger(PulseConsoleLogger consoleLogger) {
        this.consoleLogger = consoleLogger;
    }

    @Override
    public void setChainStepConfig(PluginConfig config) {
        this.config = config;
    }

    @Override
    public PulseState doStep(final PulseChainContext context) throws PulseChainStepPluginException {
        Path pathToSVProject;
        consoleLogger.debug("Initiating SV Plugin Operation");
        consoleLogger.debug("SV Config - Action: " + config.getStrAction());
        consoleLogger.debug("SV Config - Search Context: " + config.getStrSearchContext());

        // Get an enum of the selected action from the config object
        SvAction actionSelected = SvAction.valueOf(config.getStrAction());
        
        // Get an enum of the SV project search context
        SvSearchContext projectSearchContext = SvSearchContext.valueOf(config.getStrSearchContext());


        switch (projectSearchContext) {
            case HARDDISK:
                pathToSVProject = Paths.get(config.getStrProjectPath());
                break;
            case WORKSPACE:
                pathToSVProject = Paths.get(context.getWorkspaceRoot(), config.getStrProjectPath());
                break;
            default:
                consoleLogger.error("Path object could not be created.");
                return PulseState.COMPLETED_ABORTED;
        }

        // Check if the sv project exists at the path.
        if (Files.notExists(pathToSVProject)) {
            consoleLogger.error("SV Project does not exist at the following path: " + pathToSVProject.toString());
            return PulseState.COMPLETED_FAILURE;
        }

        // Now that we've checked if the file exists and we have the action, let's start
        // working!
        switch (actionSelected) {
            case CHANGE_MODE:
                break;
            case DEPLOY:
                try {
                    IProject project = loadProject(pathToSVProject, config.getStrPassword());
                    consoleLogger.info("Successfully loaded SV Project");
                    printProjectContents(project);
                    deployServiceFromProject(project);
                } catch (Exception e) {
                    e.printStackTrace();
                    return PulseState.COMPLETED_ABORTED;
                }

                break;
            case EXPORT:
                break;
            case UNDEPLOY:
                break;
            default:
        }
        return PulseState.COMPLETED_SUCCESS;
    }

    private IProject loadProject(Path projectPath, String projectPassword) throws ProjectBuilderException {
        return new ProjectBuilder().buildProject(projectPath.toFile(), projectPassword);
    }

    private void printProjectContents(IProject project) {
        consoleLogger.debug("Loaded SV Project which contains: ");
        for (IService service : project.getServices()) {
            consoleLogger.debug("    Service: " + service.getName() + " [" + service.getId() +" ]");
            for (IDataModel dataModel : service.getDataModels()) {
                consoleLogger.debug("      DM: " + dataModel.getName() + " [" + dataModel.getId() + "]");
            }
            for (IPerfModel perfModel : service.getPerfModels()) {
                consoleLogger.debug("      PM: " + perfModel.getName() + " [" + perfModel.getId() + "]");
            }
        }
    }

    private void deployServiceFromProject(IProject project) throws Exception {
        IDeployProcessor processor = new DeployProcessor(null);
        ICommandExecutor commandExecutor = createCommandExecutor();

        for (IService service : project.getServices()) {
            consoleLogger.info("  Deploying service '%s' [%s] %n", service.getName(), service.getId());
            DeployProcessorInput deployInput = new DeployProcessorInput(false, false, project, service.getId(), null, false);
            deployInput.setFirstAgentFailover(false);
            processor.process(deployInput, commandExecutor);
        }
    }

    private ICommandExecutor createCommandExecutor() throws Exception {
        return new CommandExecutorFactory().createCommandExecutor(config.getServerUrlObject(), false, getCredentials());
    }

    private Credentials getCredentials() {
        return new Credentials(config.getStrUsername(), config.getStrPassword());
    }
}
