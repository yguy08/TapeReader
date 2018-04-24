package com.tapereader.framework;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.deploy.EPDeploymentAdmin;
import com.espertech.esper.client.deploy.Module;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.tapereader.config.ModuleName;
import com.tapereader.config.StatementName;
import com.tapereader.listener.TickEventListener;

public class DefaultEngine implements Engine {
    
    private EPServiceProvider engine;
    
    @Inject
    public DefaultEngine(@ModuleName String moduleName, @StatementName String statementName, TickEventListener listener) throws Exception {
        Configuration configuration = new Configuration();
        configuration.configure();
        engine = EPServiceProviderManager.getDefaultProvider();
        EPDeploymentAdmin deployAdmin = engine.getEPAdministrator().getDeploymentAdmin();
        Module module = deployAdmin.read(moduleName);
        deployAdmin.deploy(module, null);
        //engine.getEPAdministrator().getStatement(statementName).setSubscriber(listener, "onTick");
    }
    
    @Subscribe
    @Override
    public void sendEvent(Object event) throws Exception {
        engine.getEPRuntime().sendEvent(event);
    }

    @Override
    public void addSubscriber(String stmtName, Object object, String methodName) throws Exception {
        engine.getEPAdministrator().getStatement("Select-All").setSubscriber(object, methodName);
    }
}
