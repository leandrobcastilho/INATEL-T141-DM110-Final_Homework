package br.inatel.t141.dm110.network.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.inatel.t141.dm110.network.impl.NetworkServiceImpl;

@ApplicationPath("/api")
public class RestAplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> classes = new HashSet<>();
		classes.add(NetworkServiceImpl.class);
		return classes;
	}
}
