package fr.mnantel.example.mapping;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public final class MappingUtilities
{
	private MappingUtilities()
	{
		assert false;
	}

	public static void configureMapper(ObjectMapper mapper)
	{
		// configure the mapper to use JAXB standard annotations (javax.xml.bind.annotation.*), in addition to Jackson specific annotations
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
		mapper.setConfig(mapper.getDeserializationConfig().withAppendedAnnotationIntrospector(introspector));
		mapper.setConfig(mapper.getSerializationConfig().withAppendedAnnotationIntrospector(introspector));

		// configure the mapper to not serialize attributes with default value (see: http://stackoverflow.com/a/11761975)
		mapper.setSerializationInclusion(Include.NON_DEFAULT);
	}
}
