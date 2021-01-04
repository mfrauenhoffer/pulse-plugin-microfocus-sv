package com.microfocus.pulse.svplugin;

import com.microfocus.adm.pulse.pluginapi.chain.PulseChainStepConfigAware;
import com.microfocus.adm.pulse.pluginapi.chain.PulseChainStepPlugin;
import com.microfocus.adm.pulse.pluginapi.chain.PulseChainStepPluginException;
import com.microfocus.adm.pulse.pluginapi.chain.PulseConsoleLogger;
import com.microfocus.adm.pulse.pluginapi.chain.PulseConsoleLoggerAware;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.PulseChainStepPluginDefinition;
import com.microfocus.adm.pulse.pluginapi.chain.dto.PulseChainContext;
import com.microfocus.adm.pulse.pluginapi.chain.dto.PulseChainStepPluginCategory;
import com.microfocus.adm.pulse.pluginapi.chain.dto.PulseState;

@PulseChainStepPluginDefinition(groupId = "com.microfocus.pulse", pluginId = "svplugin", version = "0", displayName = "Micro Focus Service Virtualization Controller", 
    category = PulseChainStepPluginCategory.DEPLOYMENT, description = "Allows for control of a Service Virtualization Instance to deploy virtual services during a deployment.")
public class SvPlugin implements PulseChainStepPlugin, PulseConsoleLoggerAware, PulseChainStepConfigAware<PluginConfig> {

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
        
        return PulseState.COMPLETED_SUCCESS;
    }

}
