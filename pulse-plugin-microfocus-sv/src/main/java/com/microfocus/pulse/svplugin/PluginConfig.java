package com.microfocus.pulse.svplugin;

import java.util.List;

import com.microfocus.adm.pulse.pluginapi.chain.annotations.ChainStepConfigProperty;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.MultiValueProperty;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.ObfuscatedProperty;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.TokenisedProperty;

public class PluginConfig {

    @ChainStepConfigProperty(name = "SV Server Name")
    public String strServerName;

    @ChainStepConfigProperty(name = "SV Server URL")
    public String strServerUrl;

    @ChainStepConfigProperty(name = "SV Server Username")
    public String strUsername;

    @ChainStepConfigProperty(name = "SV Server Password")
    @ObfuscatedProperty
    public String strPassword;

    @ChainStepConfigProperty(name = "SV Project Path")
    public String strProjectPath;

    @ChainStepConfigProperty(name = "SV Services to Activate")
    @MultiValueProperty(value = ",")
    @TokenisedProperty(value = false)
    public List<String> services;

    public String getStrServerName() {
        return strServerName;
    }

    public void setStrServerName(String strServerName) {
        this.strServerName = strServerName;
    }

    public String getStrServerUrl() {
        return strServerUrl;
    }

    public void setStrServerUrl(String strServerUrl) {
        this.strServerUrl = strServerUrl;
    }

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrProjectPath() {
        return strProjectPath;
    }

    public void setStrProjectPath(String strProjectPath) {
        this.strProjectPath = strProjectPath;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
