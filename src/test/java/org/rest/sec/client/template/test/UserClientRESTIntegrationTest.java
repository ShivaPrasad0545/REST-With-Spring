package org.rest.sec.client.template.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.client.AbstractClientRESTIntegrationTest;
import org.rest.client.template.IEntityOperations;
import org.rest.sec.client.template.UserRESTTemplateImpl;
import org.rest.sec.client.template.newer.UserClientRESTTemplate;
import org.rest.sec.model.dto.User;
import org.rest.spring.client.ClientTestConfig;
import org.rest.spring.context.ContextTestConfig;
import org.rest.spring.testing.TestingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ContextTestConfig.class, TestingConfig.class, ClientTestConfig.class },loader = AnnotationConfigContextLoader.class )
public class UserClientRESTIntegrationTest extends AbstractClientRESTIntegrationTest< User >{
	
	@Autowired private UserClientRESTTemplate userClientTemplate;
	@Autowired private UserRESTTemplateImpl userEntityOps;
	
	public UserClientRESTIntegrationTest(){
		super();
	}
	
	// tests
	
	// search by name
	
	@Test
	public final void givenResourceExists_whenResourceIsSearchedByName_thenNoExceptions(){
		// Given
		final User existingResource = getTemplate().create( getEntityOps().createNewEntity() );
		
		// When
		getTemplate().findOneByName( existingResource.getName() );
	}
	
	@Test
	public final void givenResourceExists_whenResourceIsSearchedByName_thenResourceIsFound(){
		// Given
		final User existingResource = getTemplate().create( getEntityOps().createNewEntity() );
		
		// When
		final User resourceByName = getTemplate().findOneByName( existingResource.getName() );
		
		// Then
		assertNotNull( resourceByName );
	}
	
	// template method
	
	@Override
	protected final UserClientRESTTemplate getTemplate(){
		return userClientTemplate;
	}
	
	@Override
	protected final IEntityOperations< User > getEntityOps(){
		return userEntityOps;
	}
	
}