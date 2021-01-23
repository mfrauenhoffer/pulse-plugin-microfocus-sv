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

    @ChainStepConfigProperty
    public boolean blForceAction;

    @ChainStepConfigProperty
    public String strTargetDirectory;

    @ChainStepConfigProperty
    public boolean blCleanTargetDirectory;

    @ChainStepConfigProperty
    public boolean blSwitchToStandbyFirst;

    @ChainStepConfigProperty
    public String strRuntimeMode;

    @ChainStepConfigProperty
    public String strDataModelSelectionOption;

    @ChainStepConfigProperty
    public String strDataModelName;

    @ChainStepConfigProperty
    public String strPerfModelSelectionOption;

    @ChainStepConfigProperty
    public String strPerfModelName;

    @ChainStepConfigProperty
    public boolean blActionAllServices;

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

    public boolean isBlForceAction() {
        return blForceAction;
    }

    public void setBlForceAction(boolean blForceAction) {
        this.blForceAction = blForceAction;
    }

    public String getStrTargetDirectory() {
        return strTargetDirectory;
    }

    public void setStrTargetDirectory(String strTargetDirectory) {
        this.strTargetDirectory = strTargetDirectory;
    }

    public boolean isBlCleanTargetDirectory() {
        return blCleanTargetDirectory;
    }

    public void setBlCleanTargetDirectory(boolean blCleanTargetDirectory) {
        this.blCleanTargetDirectory = blCleanTargetDirectory;
    }

    public boolean isBlSwitchToStandbyFirst() {
        return blSwitchToStandbyFirst;
    }

    public void setBlSwitchToStandbyFirst(boolean blSwitchToStandbyFirst) {
        this.blSwitchToStandbyFirst = blSwitchToStandbyFirst;
    }

    public String getStrRuntimeMode() {
        return strRuntimeMode;
    }

    public void setStrRuntimeMode(String strRuntimeMode) {
        this.strRuntimeMode = strRuntimeMode;
    }

    public String getStrDataModelSelectionOption() {
        return strDataModelSelectionOption;
    }

    public void setStrDataModelSelectionOption(String strDataModelSelectionOption) {
        this.strDataModelSelectionOption = strDataModelSelectionOption;
    }

    public String getStrDataModelName() {
        return strDataModelName;
    }

    public void setStrDataModelName(String strDataModelName) {
        this.strDataModelName = strDataModelName;
    }

    public String getStrPerfModelSelectionOption() {
        return strPerfModelSelectionOption;
    }

    public void setStrPerfModelSelectionOption(String strPerfModelSelectionOption) {
        this.strPerfModelSelectionOption = strPerfModelSelectionOption;
    }

    public String getStrPerfModelName() {
        return strPerfModelName;
    }

    public void setStrPerfModelName(String strPerfModelName) {
        this.strPerfModelName = strPerfModelName;
    }

    public boolean isBlActionAllServices() {
        return blActionAllServices;
    }

    public void setBlActionAllServices(boolean blActionAllServices) {
        this.blActionAllServices = blActionAllServices;
    }
}
