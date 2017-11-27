package br.inatel.t141.dm110.network.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.t141.dm110.network.to.AddressTO;

@Path("/poller")
public interface NetworkService {

	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	List<AddressTO> listStatusAdress();
	
	@GET
	@Path("/activeAdress")
	@Produces(MediaType.APPLICATION_JSON)
	List<AddressTO> listActiveAdress();
	
	@GET
	@Path("/status/{ip}")
	@Produces(MediaType.APPLICATION_JSON)
	AddressTO getStatusAdress(@PathParam("ip") String ip);
	
	
	@POST
	@Path("/status/{ip}/{cidr}")
	@Produces(MediaType.APPLICATION_JSON)
	void checkStatusAdressNetwork(@PathParam("ip") String ip, @PathParam("cidr") String cidr);
	

}
