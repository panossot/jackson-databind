package com.fasterxml.jackson.databind.node;

import java.io.IOException;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * This singleton value class is used to contain explicit JSON null
 * value.
 */
public final class NullNode
    extends ValueNode
{
    private static final long serialVersionUID = 3L;

    // // Just need a fly-weight singleton

    public final static NullNode instance = new NullNode();

    protected NullNode() { }

    public static NullNode getInstance() { return instance; }

    // To support JDK serialization, recovery of Singleton instance
    protected Object readResolve() {
        return instance;
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.NULL;
    }

    @Override public JsonToken asToken() { return JsonToken.VALUE_NULL; }

    @Override public String asText(String defaultValue) { return defaultValue; }
    @Override public String asText() { return "null"; }

    // as with MissingNode, not considered number node; hence defaults are returned if provided
    
    /*
    public int asInt(int defaultValue);
    public long asLong(long defaultValue);
    public double asDouble(double defaultValue);
    public boolean asBoolean(boolean defaultValue);
    */
    
    @Override
    public final void serialize(JsonGenerator g, SerializerProvider provider)
        throws IOException
    {
        provider.defaultSerializeNullValue(g);
    }

    @Override
    public boolean equals(Object o) {
        return (o == this);
    }

    @Override
    public int hashCode() {
        return JsonNodeType.NULL.ordinal();
    }
}
