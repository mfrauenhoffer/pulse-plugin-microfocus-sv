package com.microfocus.pulse.svplugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.microfocus.adm.pulse.pluginapi.chain.annotations.ChainStepConfigProperty;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.MultiValueProperty;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.ObfuscatedProperty;
import com.microfocus.adm.pulse.pluginapi.chain.annotations.TokenisedProperty;

public class PluginConfig {

    @ChainStepConfigProperty
    public String strServerName;

    @ChainStepConfigProperty
    public String strServerUrl;

    @ChainStepConfigProperty
    public String strUsername;

    @ChainStepConfigProperty
    @ObfuscatedProperty
    public String strPassword;

    @ChainStepConfigProperty
    public String strProjectPath;

    @ChainStepConfigProperty
    @MultiValueProperty(value = ",")
    @TokenisedProperty(value = false)
    public List<String> services;

    @ChainStepConfigProperty
    public String strAction;

    @ChainStepConfigProperty
    public String strSearchContext;

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

    public String getStrAction() {
        return strAction;
    }

    public void setStrAction(String strAction) {
        this.strAction = strAction;
    }

    public String getStrSearchContext() {
        return strSearchContext;
    }

    public void setStrSearchContext(String strSearchContext) {
        this.strSearchContext = strSearchContext;
    }

    public URL getServerUrlObject() throws MalformedURLException {
        return new URL(strServerUrl);
    }
}
