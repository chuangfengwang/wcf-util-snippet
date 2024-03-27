/**
 * Autogenerated by Thrift Compiler (0.19.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.helipy.biz.bert;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.19.0)", date = "2024-03-27")
public class TokenizerParam implements org.apache.thrift.TBase<TokenizerParam, TokenizerParam._Fields>, java.io.Serializable, Cloneable, Comparable<TokenizerParam> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TokenizerParam");

  private static final org.apache.thrift.protocol.TField TEXT_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("text_list", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField WRAPPER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("wrapper_id", org.apache.thrift.protocol.TType.BOOL, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TokenizerParamStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TokenizerParamTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> text_list; // required
  public boolean wrapper_id; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TEXT_LIST((short)1, "text_list"),
    WRAPPER_ID((short)2, "wrapper_id");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TEXT_LIST
          return TEXT_LIST;
        case 2: // WRAPPER_ID
          return WRAPPER_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __WRAPPER_ID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.WRAPPER_ID};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TEXT_LIST, new org.apache.thrift.meta_data.FieldMetaData("text_list", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.WRAPPER_ID, new org.apache.thrift.meta_data.FieldMetaData("wrapper_id", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TokenizerParam.class, metaDataMap);
  }

  public TokenizerParam() {
    this.wrapper_id = false;

  }

  public TokenizerParam(
    java.util.List<java.lang.String> text_list)
  {
    this();
    this.text_list = text_list;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TokenizerParam(TokenizerParam other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetText_list()) {
      java.util.List<java.lang.String> __this__text_list = new java.util.ArrayList<java.lang.String>(other.text_list);
      this.text_list = __this__text_list;
    }
    this.wrapper_id = other.wrapper_id;
  }

  @Override
  public TokenizerParam deepCopy() {
    return new TokenizerParam(this);
  }

  @Override
  public void clear() {
    this.text_list = null;
    this.wrapper_id = false;

  }

  public int getText_listSize() {
    return (this.text_list == null) ? 0 : this.text_list.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.String> getText_listIterator() {
    return (this.text_list == null) ? null : this.text_list.iterator();
  }

  public void addToText_list(java.lang.String elem) {
    if (this.text_list == null) {
      this.text_list = new java.util.ArrayList<java.lang.String>();
    }
    this.text_list.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.String> getText_list() {
    return this.text_list;
  }

  public TokenizerParam setText_list(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> text_list) {
    this.text_list = text_list;
    return this;
  }

  public void unsetText_list() {
    this.text_list = null;
  }

  /** Returns true if field text_list is set (has been assigned a value) and false otherwise */
  public boolean isSetText_list() {
    return this.text_list != null;
  }

  public void setText_listIsSet(boolean value) {
    if (!value) {
      this.text_list = null;
    }
  }

  public boolean isWrapper_id() {
    return this.wrapper_id;
  }

  public TokenizerParam setWrapper_id(boolean wrapper_id) {
    this.wrapper_id = wrapper_id;
    setWrapper_idIsSet(true);
    return this;
  }

  public void unsetWrapper_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __WRAPPER_ID_ISSET_ID);
  }

  /** Returns true if field wrapper_id is set (has been assigned a value) and false otherwise */
  public boolean isSetWrapper_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __WRAPPER_ID_ISSET_ID);
  }

  public void setWrapper_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __WRAPPER_ID_ISSET_ID, value);
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case TEXT_LIST:
      if (value == null) {
        unsetText_list();
      } else {
        setText_list((java.util.List<java.lang.String>)value);
      }
      break;

    case WRAPPER_ID:
      if (value == null) {
        unsetWrapper_id();
      } else {
        setWrapper_id((java.lang.Boolean)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TEXT_LIST:
      return getText_list();

    case WRAPPER_ID:
      return isWrapper_id();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case TEXT_LIST:
      return isSetText_list();
    case WRAPPER_ID:
      return isSetWrapper_id();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof TokenizerParam)
      return this.equals((TokenizerParam)that);
    return false;
  }

  public boolean equals(TokenizerParam that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_text_list = true && this.isSetText_list();
    boolean that_present_text_list = true && that.isSetText_list();
    if (this_present_text_list || that_present_text_list) {
      if (!(this_present_text_list && that_present_text_list))
        return false;
      if (!this.text_list.equals(that.text_list))
        return false;
    }

    boolean this_present_wrapper_id = true && this.isSetWrapper_id();
    boolean that_present_wrapper_id = true && that.isSetWrapper_id();
    if (this_present_wrapper_id || that_present_wrapper_id) {
      if (!(this_present_wrapper_id && that_present_wrapper_id))
        return false;
      if (this.wrapper_id != that.wrapper_id)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetText_list()) ? 131071 : 524287);
    if (isSetText_list())
      hashCode = hashCode * 8191 + text_list.hashCode();

    hashCode = hashCode * 8191 + ((isSetWrapper_id()) ? 131071 : 524287);
    if (isSetWrapper_id())
      hashCode = hashCode * 8191 + ((wrapper_id) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(TokenizerParam other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetText_list(), other.isSetText_list());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetText_list()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.text_list, other.text_list);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetWrapper_id(), other.isSetWrapper_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWrapper_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.wrapper_id, other.wrapper_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TokenizerParam(");
    boolean first = true;

    sb.append("text_list:");
    if (this.text_list == null) {
      sb.append("null");
    } else {
      sb.append(this.text_list);
    }
    first = false;
    if (isSetWrapper_id()) {
      if (!first) sb.append(", ");
      sb.append("wrapper_id:");
      sb.append(this.wrapper_id);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (text_list == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'text_list' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TokenizerParamStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TokenizerParamStandardScheme getScheme() {
      return new TokenizerParamStandardScheme();
    }
  }

  private static class TokenizerParamStandardScheme extends org.apache.thrift.scheme.StandardScheme<TokenizerParam> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, TokenizerParam struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TEXT_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.text_list = new java.util.ArrayList<java.lang.String>(_list0.size);
                @org.apache.thrift.annotation.Nullable java.lang.String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.text_list.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setText_listIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WRAPPER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.wrapper_id = iprot.readBool();
              struct.setWrapper_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, TokenizerParam struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.text_list != null) {
        oprot.writeFieldBegin(TEXT_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.text_list.size()));
          for (java.lang.String _iter3 : struct.text_list)
          {
            oprot.writeString(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.isSetWrapper_id()) {
        oprot.writeFieldBegin(WRAPPER_ID_FIELD_DESC);
        oprot.writeBool(struct.wrapper_id);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TokenizerParamTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TokenizerParamTupleScheme getScheme() {
      return new TokenizerParamTupleScheme();
    }
  }

  private static class TokenizerParamTupleScheme extends org.apache.thrift.scheme.TupleScheme<TokenizerParam> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TokenizerParam struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      {
        oprot.writeI32(struct.text_list.size());
        for (java.lang.String _iter4 : struct.text_list)
        {
          oprot.writeString(_iter4);
        }
      }
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetWrapper_id()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetWrapper_id()) {
        oprot.writeBool(struct.wrapper_id);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TokenizerParam struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list5 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRING);
        struct.text_list = new java.util.ArrayList<java.lang.String>(_list5.size);
        @org.apache.thrift.annotation.Nullable java.lang.String _elem6;
        for (int _i7 = 0; _i7 < _list5.size; ++_i7)
        {
          _elem6 = iprot.readString();
          struct.text_list.add(_elem6);
        }
      }
      struct.setText_listIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.wrapper_id = iprot.readBool();
        struct.setWrapper_idIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

