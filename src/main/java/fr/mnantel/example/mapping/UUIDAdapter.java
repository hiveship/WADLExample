package fr.mnantel.example.mapping;

import java.util.UUID;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UUIDAdapter extends XmlAdapter<String, UUID>
{
	@Override
	public UUID unmarshal(String value) throws Exception
	{
		// System.out.println("UUIDAdapter.unmarshal(...)");
		return UUID.fromString(value);
	}

	@Override
	public String marshal(UUID value) throws Exception
	{
		// System.out.println("UUIDAdapter.marshal(...)");
		return value.toString();
	}
}
