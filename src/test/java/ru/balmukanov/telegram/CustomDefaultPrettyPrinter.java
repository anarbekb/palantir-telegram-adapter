package ru.balmukanov.telegram;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.IOException;

public class CustomDefaultPrettyPrinter extends DefaultPrettyPrinter {
	private static final long serialVersionUID = 2323344887727223L;

	public CustomDefaultPrettyPrinter() {
		this._objectIndenter = new DefaultIndenter("  ", "\n");
		this._arrayIndenter = _objectIndenter;
	}

	@Override
	public DefaultPrettyPrinter createInstance() {
		return new CustomDefaultPrettyPrinter();
	}

	@Override
	public void writeObjectFieldValueSeparator(JsonGenerator jg) throws IOException {
		jg.writeRaw(": ");
	}
}