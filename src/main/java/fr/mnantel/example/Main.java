package fr.mnantel.example;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import fr.mnantel.example.api.Apple;
import fr.mnantel.example.api.AppleCollection;
import fr.mnantel.example.api.AppleVariety;
import fr.mnantel.example.api.BadAppleCollection;
import fr.mnantel.example.api.Location;
import fr.mnantel.example.api.Dot;
import fr.mnantel.example.api.Store;
import fr.mnantel.example.mapping.MappingUtilities;

public final class Main
{
	private Main()
	{
		assert false;
	}

	public static void main(String[] args) throws Exception
	{
		// ============== 
		// INITIALISATION
		// ==============

		// Creating two objects
		Apple golden = new Apple();
		// golden.setColour("green");
		golden.setPrice(0.99);
		golden.setVariety(AppleVariety.GOLDEN);
		golden.setUuid(UUID.randomUUID());

		Apple royalGala = new Apple();
		royalGala.setColour("yellow");
		royalGala.setPrice(1.99);
		royalGala.setVariety(AppleVariety.ROYAL_GALA);
		royalGala.setUuid(UUID.randomUUID());

        // Create a collection with the 'old' representation, using a type defined as a sequence of elements
		BadAppleCollection badAppleCollection = new BadAppleCollection();
		List<Apple> badApples = badAppleCollection.getApples();
		badApples.add(golden);
		badApples.add(royalGala);

        // Create a collection with the 'new' representation, using an empty type extending a Java collection
		AppleCollection appleCollection = new AppleCollection();
		appleCollection.add(golden);
		appleCollection.add(royalGala);

		Store store = new Store();
		store.setName("AppleStore");
		store.setGoods(appleCollection);
		Location location = new Location();
		location.setName("myStore");
		location.setDot(new Dot(25, 11));
		store.setWhere(location);

		System.out.println("\n==================== TO STRING");
		System.out.println(golden.toString());
		System.out.println(appleCollection.toString());

		// ======
		// MAPPER
		// ======

		ObjectMapper jsonMapper = new ObjectMapper();
		MappingUtilities.configureMapper(jsonMapper); // *REQUIRED* for serialization/deserialization
		jsonMapper.enable(SerializationFeature.INDENT_OUTPUT); // to be human-readable, not required for marshalling/unmarshalling
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		MappingUtilities.configureMapper(xmlMapper);

		// ===========
		// MARSHALLING
        // ===========

		System.out.println("\n==================== SERIALIZATION");

		System.out.println("\n-------------------- Apple JSON representation");
		String appleAsJSONString = jsonMapper.writeValueAsString(golden);
		System.out.println(appleAsJSONString);
		System.out.println("\n-------------------- Apple XML representation");
		String appleAsXMLString = xmlMapper.writeValueAsString(golden);
		System.out.println(appleAsXMLString);

		System.out.println("\n-------------------- 'Old' collection JSON representation");
		String badAppleCollectionAsJSONString = jsonMapper.writeValueAsString(badAppleCollection);
		System.out.println(badAppleCollectionAsJSONString);
        System.out.println("\n-------------------- 'Old' collection XML representation");
		String badAppleCollectionAsXMLString = xmlMapper.writeValueAsString(badAppleCollection);
		System.out.println(badAppleCollectionAsXMLString);

		System.out.println("\n-------------------- 'New' JSON collection representation");
		String appleCollectionAsJSONString = jsonMapper.writeValueAsString(appleCollection);
		System.out.println(appleCollectionAsJSONString);
        System.out.println("\n-------------------- 'New' XML collection representation");
		String appleCollectionAsXMLString = xmlMapper.writeValueAsString(appleCollection);
		System.out.println(appleCollectionAsXMLString);

		System.out.println("\n-------------------- Store JSON reprensentation");
		String storeAsJSONString = jsonMapper.writeValueAsString(store);
		System.out.println(storeAsJSONString);
        System.out.println("\n-------------------- Store XML reprensentation");
		String storeAsXMLString = xmlMapper.writeValueAsString(store);
		System.out.println(storeAsXMLString);

		// =============
		// UNMARSHALLING
		// =============

		System.out.println("\n==================== DESERIALIZATION");

        System.out.println("\n-------------------- Apple JSON representation");
		Apple deserializedAppleFromJSON = jsonMapper.readValue(appleAsJSONString, Apple.class);
		System.out.println(jsonMapper.writeValueAsString(deserializedAppleFromJSON));

        System.out.println("\n-------------------- Apple XML representation");
		Apple deserializedAppleFromXML = xmlMapper.readValue(appleAsXMLString, Apple.class);
		System.out.println(xmlMapper.writeValueAsString(deserializedAppleFromXML));

        System.out.println("\n-------------------- Collection using the 'old' reprensentation, deserialized with the 'old' method");
		BadAppleCollection deserializedBadAppleCollectionFromJSON = jsonMapper.readValue(badAppleCollectionAsJSONString, BadAppleCollection.class);
		System.out.println(jsonMapper.writeValueAsString(deserializedBadAppleCollectionFromJSON));

		System.out.println("\n-------------------- Collection using the 'new' reprensentation, deserialized with the 'new' method");
		AppleCollection deserializedAppleCollectionFromJSON = jsonMapper.readValue(appleCollectionAsJSONString, AppleCollection.class);
		System.out.println(jsonMapper.writeValueAsString(deserializedAppleCollectionFromJSON));

        System.out.println("\n-------------------- Store JSON representation");
		Store deserializedStoreFromJSON = jsonMapper.readValue(storeAsJSONString, Store.class);
		System.out.println(jsonMapper.writeValueAsString(deserializedStoreFromJSON));

        System.out.println("\n-------------------- Store XML representation");
		Store deserializedStoreFromXML = xmlMapper.readValue(storeAsXMLString, Store.class);
		System.out.println(xmlMapper.writeValueAsString(deserializedStoreFromXML));
	}
}
