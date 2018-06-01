package com.hcl.api;

import java.util.*;

import com.hcl.model.*;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.google.appengine.api.modules.ModulesService;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.api.modules.ModulesException;

import java.util.logging.Logger;

import javax.inject.Named;


@Api(
	name = "modulesAPI",
	version = "v1",
	namespace = @ApiNamespace(
		ownerDomain = "hcl.com",
		ownerName = "hcl.com",
		packagePath = ""
	)
)
public class ModulesAPI {
	
	private static final ModulesService modulesService = ModulesServiceFactory.getModulesService();
	private static final Logger log = Logger.getLogger(ModulesAPI.class.getName());
	
	
	@ApiMethod(
		name = "modules.list",
		path = "modules/list",
		httpMethod = ApiMethod.HttpMethod.GET
	)
	public List<String> getModules() {
		Set<String> modules = modulesService.getModules();
		
		Map<String, String> result = new HashMap<String, String>();
		
		for (String module : modules) {
			result.put(module + ":default", modulesService.getDefaultVersion(module));
			Set<String> versions = modulesService.getVersions(module);
			for (String version : versions) {
				int instances = 0;
				//exception thrown here
				instances = modulesService.getNumInstances(module, version);
				log.info(module + ":" + version + ":" + instances);
				result.put(module, version);
			}
		}
		
		return new ArrayList<String>(result.values());
	}
	
}
