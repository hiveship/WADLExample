package fr.mnantel.example.mapping;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import fr.mnantel.example.api.Dot;

public class DotAdapter extends XmlAdapter<String, Dot>
{
	@Override
	public Dot unmarshal(String value) throws Exception
	{
		return Dot.fromString(value);
	}

	@Override
	public String marshal(Dot value) throws Exception
	{
		return value.toString();
	}
}
