/**
 * Autogenerated by Thrift Compiler (0.19.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.helipy.biz.bert;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.19.0)", date = "2024-03-27")
public class TokenizerResult implements org.apache.thrift.TBase<TokenizerResult, TokenizerResult._Fields>, java.io.Serializable, Cloneable, Comparable<TokenizerResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TokenizerResult");

  private static final org.apache.thrift.protocol.TField TOKENIZER_WORD_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("tokenizer_word_list", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField TOKENIZER_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("tokenizer_id_list", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField BASE_FIELD_DESC = new org.apache.thrift.protocol.TField("base", org.apache.thrift.protocol.TType.STRUCT, (short)255);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TokenizerResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TokenizerResultTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<java.util.List<java.lang.String>> tokenizer_word_list; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.util.List<java.lang.Integer>> tokenizer_id_list; // optional
  public @org.apache.thrift.annotation.Nullable BaseResponse base; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TOKENIZER_WORD_LIST((short)1, "tokenizer_word_list"),
    TOKENIZER_ID_LIST((short)2, "tokenizer_id_list"),
    BASE((short)255, "base");

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
        case 1: // TOKENIZER_WORD_LIST
          return TOKENIZER_WORD_LIST;
        case 2: // TOKENIZER_ID_LIST
          return TOKENIZER_ID_LIST;
        case 255: // BASE
          return BASE;
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
  private static final _Fields optionals[] = {_Fields.TOKENIZER_ID_LIST,_Fields.BASE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TOKENIZER_WORD_LIST, new org.apache.thrift.meta_data.FieldMetaData("tokenizer_word_list", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)))));
    tmpMap.put(_Fields.TOKENIZER_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("tokenizer_id_list", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)))));
    tmpMap.put(_Fields.BASE, new org.apache.thrift.meta_data.FieldMetaData("base", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, BaseResponse.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TokenizerResult.class, metaDataMap);
  }

  public TokenizerResult() {
  }

  public TokenizerResult(
    java.util.List<java.util.List<java.lang.String>> tokenizer_word_list)
  {
    this();
    this.tokenizer_word_list = tokenizer_word_list;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TokenizerResult(TokenizerResult other) {
    if (other.isSetTokenizer_word_list()) {
      java.util.List<java.util.List<java.lang.String>> __this__tokenizer_word_list = new java.util.ArrayList<java.util.List<java.lang.String>>(other.tokenizer_word_list.size());
      for (java.util.List<java.lang.String> other_element : other.tokenizer_word_list) {
        java.util.List<java.lang.String> __this__tokenizer_word_list_copy = new java.util.ArrayList<java.lang.String>(other_element);
        __this__tokenizer_word_list.add(__this__tokenizer_word_list_copy);
      }
      this.tokenizer_word_list = __this__tokenizer_word_list;
    }
    if (other.isSetTokenizer_id_list()) {
      java.util.List<java.util.List<java.lang.Integer>> __this__tokenizer_id_list = new java.util.ArrayList<java.util.List<java.lang.Integer>>(other.tokenizer_id_list.size());
      for (java.util.List<java.lang.Integer> other_element : other.tokenizer_id_list) {
        java.util.List<java.lang.Integer> __this__tokenizer_id_list_copy = new java.util.ArrayList<java.lang.Integer>(other_element);
        __this__tokenizer_id_list.add(__this__tokenizer_id_list_copy);
      }
      this.tokenizer_id_list = __this__tokenizer_id_list;
    }
    if (other.isSetBase()) {
      this.base = new BaseResponse(other.base);
    }
  }

  @Override
  public TokenizerResult deepCopy() {
    return new TokenizerResult(this);
  }

  @Override
  public void clear() {
    this.tokenizer_word_list = null;
    this.tokenizer_id_list = null;
    this.base = null;
  }

  public int getTokenizer_word_listSize() {
    return (this.tokenizer_word_list == null) ? 0 : this.tokenizer_word_list.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.util.List<java.lang.String>> getTokenizer_word_listIterator() {
    return (this.tokenizer_word_list == null) ? null : this.tokenizer_word_list.iterator();
  }

  public void addToTokenizer_word_list(java.util.List<java.lang.String> elem) {
    if (this.tokenizer_word_list == null) {
      this.tokenizer_word_list = new java.util.ArrayList<java.util.List<java.lang.String>>();
    }
    this.tokenizer_word_list.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.util.List<java.lang.String>> getTokenizer_word_list() {
    return this.tokenizer_word_list;
  }

  public TokenizerResult setTokenizer_word_list(@org.apache.thrift.annotation.Nullable java.util.List<java.util.List<java.lang.String>> tokenizer_word_list) {
    this.tokenizer_word_list = tokenizer_word_list;
    return this;
  }

  public void unsetTokenizer_word_list() {
    this.tokenizer_word_list = null;
  }

  /** Returns true if field tokenizer_word_list is set (has been assigned a value) and false otherwise */
  public boolean isSetTokenizer_word_list() {
    return this.tokenizer_word_list != null;
  }

  public void setTokenizer_word_listIsSet(boolean value) {
    if (!value) {
      this.tokenizer_word_list = null;
    }
  }

  public int getTokenizer_id_listSize() {
    return (this.tokenizer_id_list == null) ? 0 : this.tokenizer_id_list.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.util.List<java.lang.Integer>> getTokenizer_id_listIterator() {
    return (this.tokenizer_id_list == null) ? null : this.tokenizer_id_list.iterator();
  }

  public void addToTokenizer_id_list(java.util.List<java.lang.Integer> elem) {
    if (this.tokenizer_id_list == null) {
      this.tokenizer_id_list = new java.util.ArrayList<java.util.List<java.lang.Integer>>();
    }
    this.tokenizer_id_list.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.util.List<java.lang.Integer>> getTokenizer_id_list() {
    return this.tokenizer_id_list;
  }

  public TokenizerResult setTokenizer_id_list(@org.apache.thrift.annotation.Nullable java.util.List<java.util.List<java.lang.Integer>> tokenizer_id_list) {
    this.tokenizer_id_list = tokenizer_id_list;
    return this;
  }

  public void unsetTokenizer_id_list() {
    this.tokenizer_id_list = null;
  }

  /** Returns true if field tokenizer_id_list is set (has been assigned a value) and false otherwise */
  public boolean isSetTokenizer_id_list() {
    return this.tokenizer_id_list != null;
  }

  public void setTokenizer_id_listIsSet(boolean value) {
    if (!value) {
      this.tokenizer_id_list = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public BaseResponse getBase() {
    return this.base;
  }

  public TokenizerResult setBase(@org.apache.thrift.annotation.Nullable BaseResponse base) {
    this.base = base;
    return this;
  }

  public void unsetBase() {
    this.base = null;
  }

  /** Returns true if field base is set (has been assigned a value) and false otherwise */
  public boolean isSetBase() {
    return this.base != null;
  }

  public void setBaseIsSet(boolean value) {
    if (!value) {
      this.base = null;
    }
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case TOKENIZER_WORD_LIST:
      if (value == null) {
        unsetTokenizer_word_list();
      } else {
        setTokenizer_word_list((java.util.List<java.util.List<java.lang.String>>)value);
      }
      break;

    case TOKENIZER_ID_LIST:
      if (value == null) {
        unsetTokenizer_id_list();
      } else {
        setTokenizer_id_list((java.util.List<java.util.List<java.lang.Integer>>)value);
      }
      break;

    case BASE:
      if (value == null) {
        unsetBase();
      } else {
        setBase((BaseResponse)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TOKENIZER_WORD_LIST:
      return getTokenizer_word_list();

    case TOKENIZER_ID_LIST:
      return getTokenizer_id_list();

    case BASE:
      return getBase();

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
    case TOKENIZER_WORD_LIST:
      return isSetTokenizer_word_list();
    case TOKENIZER_ID_LIST:
      return isSetTokenizer_id_list();
    case BASE:
      return isSetBase();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof TokenizerResult)
      return this.equals((TokenizerResult)that);
    return false;
  }

  public boolean equals(TokenizerResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_tokenizer_word_list = true && this.isSetTokenizer_word_list();
    boolean that_present_tokenizer_word_list = true && that.isSetTokenizer_word_list();
    if (this_present_tokenizer_word_list || that_present_tokenizer_word_list) {
      if (!(this_present_tokenizer_word_list && that_present_tokenizer_word_list))
        return false;
      if (!this.tokenizer_word_list.equals(that.tokenizer_word_list))
        return false;
    }

    boolean this_present_tokenizer_id_list = true && this.isSetTokenizer_id_list();
    boolean that_present_tokenizer_id_list = true && that.isSetTokenizer_id_list();
    if (this_present_tokenizer_id_list || that_present_tokenizer_id_list) {
      if (!(this_present_tokenizer_id_list && that_present_tokenizer_id_list))
        return false;
      if (!this.tokenizer_id_list.equals(that.tokenizer_id_list))
        return false;
    }

    boolean this_present_base = true && this.isSetBase();
    boolean that_present_base = true && that.isSetBase();
    if (this_present_base || that_present_base) {
      if (!(this_present_base && that_present_base))
        return false;
      if (!this.base.equals(that.base))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetTokenizer_word_list()) ? 131071 : 524287);
    if (isSetTokenizer_word_list())
      hashCode = hashCode * 8191 + tokenizer_word_list.hashCode();

    hashCode = hashCode * 8191 + ((isSetTokenizer_id_list()) ? 131071 : 524287);
    if (isSetTokenizer_id_list())
      hashCode = hashCode * 8191 + tokenizer_id_list.hashCode();

    hashCode = hashCode * 8191 + ((isSetBase()) ? 131071 : 524287);
    if (isSetBase())
      hashCode = hashCode * 8191 + base.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TokenizerResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetTokenizer_word_list(), other.isSetTokenizer_word_list());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTokenizer_word_list()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tokenizer_word_list, other.tokenizer_word_list);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetTokenizer_id_list(), other.isSetTokenizer_id_list());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTokenizer_id_list()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tokenizer_id_list, other.tokenizer_id_list);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetBase(), other.isSetBase());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBase()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.base, other.base);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TokenizerResult(");
    boolean first = true;

    sb.append("tokenizer_word_list:");
    if (this.tokenizer_word_list == null) {
      sb.append("null");
    } else {
      sb.append(this.tokenizer_word_list);
    }
    first = false;
    if (isSetTokenizer_id_list()) {
      if (!first) sb.append(", ");
      sb.append("tokenizer_id_list:");
      if (this.tokenizer_id_list == null) {
        sb.append("null");
      } else {
        sb.append(this.tokenizer_id_list);
      }
      first = false;
    }
    if (isSetBase()) {
      if (!first) sb.append(", ");
      sb.append("base:");
      if (this.base == null) {
        sb.append("null");
      } else {
        sb.append(this.base);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (tokenizer_word_list == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'tokenizer_word_list' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (base != null) {
      base.validate();
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TokenizerResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TokenizerResultStandardScheme getScheme() {
      return new TokenizerResultStandardScheme();
    }
  }

  private static class TokenizerResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<TokenizerResult> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, TokenizerResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TOKENIZER_WORD_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.tokenizer_word_list = new java.util.ArrayList<java.util.List<java.lang.String>>(_list8.size);
                @org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  {
                    org.apache.thrift.protocol.TList _list11 = iprot.readListBegin();
                    _elem9 = new java.util.ArrayList<java.lang.String>(_list11.size);
                    @org.apache.thrift.annotation.Nullable java.lang.String _elem12;
                    for (int _i13 = 0; _i13 < _list11.size; ++_i13)
                    {
                      _elem12 = iprot.readString();
                      _elem9.add(_elem12);
                    }
                    iprot.readListEnd();
                  }
                  struct.tokenizer_word_list.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setTokenizer_word_listIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOKENIZER_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list14 = iprot.readListBegin();
                struct.tokenizer_id_list = new java.util.ArrayList<java.util.List<java.lang.Integer>>(_list14.size);
                @org.apache.thrift.annotation.Nullable java.util.List<java.lang.Integer> _elem15;
                for (int _i16 = 0; _i16 < _list14.size; ++_i16)
                {
                  {
                    org.apache.thrift.protocol.TList _list17 = iprot.readListBegin();
                    _elem15 = new java.util.ArrayList<java.lang.Integer>(_list17.size);
                    int _elem18;
                    for (int _i19 = 0; _i19 < _list17.size; ++_i19)
                    {
                      _elem18 = iprot.readI32();
                      _elem15.add(_elem18);
                    }
                    iprot.readListEnd();
                  }
                  struct.tokenizer_id_list.add(_elem15);
                }
                iprot.readListEnd();
              }
              struct.setTokenizer_id_listIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 255: // BASE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.base = new BaseResponse();
              struct.base.read(iprot);
              struct.setBaseIsSet(true);
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
    public void write(org.apache.thrift.protocol.TProtocol oprot, TokenizerResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.tokenizer_word_list != null) {
        oprot.writeFieldBegin(TOKENIZER_WORD_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.LIST, struct.tokenizer_word_list.size()));
          for (java.util.List<java.lang.String> _iter20 : struct.tokenizer_word_list)
          {
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, _iter20.size()));
              for (java.lang.String _iter21 : _iter20)
              {
                oprot.writeString(_iter21);
              }
              oprot.writeListEnd();
            }
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.tokenizer_id_list != null) {
        if (struct.isSetTokenizer_id_list()) {
          oprot.writeFieldBegin(TOKENIZER_ID_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.LIST, struct.tokenizer_id_list.size()));
            for (java.util.List<java.lang.Integer> _iter22 : struct.tokenizer_id_list)
            {
              {
                oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, _iter22.size()));
                for (int _iter23 : _iter22)
                {
                  oprot.writeI32(_iter23);
                }
                oprot.writeListEnd();
              }
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.base != null) {
        if (struct.isSetBase()) {
          oprot.writeFieldBegin(BASE_FIELD_DESC);
          struct.base.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TokenizerResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TokenizerResultTupleScheme getScheme() {
      return new TokenizerResultTupleScheme();
    }
  }

  private static class TokenizerResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<TokenizerResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TokenizerResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      {
        oprot.writeI32(struct.tokenizer_word_list.size());
        for (java.util.List<java.lang.String> _iter24 : struct.tokenizer_word_list)
        {
          {
            oprot.writeI32(_iter24.size());
            for (java.lang.String _iter25 : _iter24)
            {
              oprot.writeString(_iter25);
            }
          }
        }
      }
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTokenizer_id_list()) {
        optionals.set(0);
      }
      if (struct.isSetBase()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetTokenizer_id_list()) {
        {
          oprot.writeI32(struct.tokenizer_id_list.size());
          for (java.util.List<java.lang.Integer> _iter26 : struct.tokenizer_id_list)
          {
            {
              oprot.writeI32(_iter26.size());
              for (int _iter27 : _iter26)
              {
                oprot.writeI32(_iter27);
              }
            }
          }
        }
      }
      if (struct.isSetBase()) {
        struct.base.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TokenizerResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list28 = iprot.readListBegin(org.apache.thrift.protocol.TType.LIST);
        struct.tokenizer_word_list = new java.util.ArrayList<java.util.List<java.lang.String>>(_list28.size);
        @org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> _elem29;
        for (int _i30 = 0; _i30 < _list28.size; ++_i30)
        {
          {
            org.apache.thrift.protocol.TList _list31 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRING);
            _elem29 = new java.util.ArrayList<java.lang.String>(_list31.size);
            @org.apache.thrift.annotation.Nullable java.lang.String _elem32;
            for (int _i33 = 0; _i33 < _list31.size; ++_i33)
            {
              _elem32 = iprot.readString();
              _elem29.add(_elem32);
            }
          }
          struct.tokenizer_word_list.add(_elem29);
        }
      }
      struct.setTokenizer_word_listIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list34 = iprot.readListBegin(org.apache.thrift.protocol.TType.LIST);
          struct.tokenizer_id_list = new java.util.ArrayList<java.util.List<java.lang.Integer>>(_list34.size);
          @org.apache.thrift.annotation.Nullable java.util.List<java.lang.Integer> _elem35;
          for (int _i36 = 0; _i36 < _list34.size; ++_i36)
          {
            {
              org.apache.thrift.protocol.TList _list37 = iprot.readListBegin(org.apache.thrift.protocol.TType.I32);
              _elem35 = new java.util.ArrayList<java.lang.Integer>(_list37.size);
              int _elem38;
              for (int _i39 = 0; _i39 < _list37.size; ++_i39)
              {
                _elem38 = iprot.readI32();
                _elem35.add(_elem38);
              }
            }
            struct.tokenizer_id_list.add(_elem35);
          }
        }
        struct.setTokenizer_id_listIsSet(true);
      }
      if (incoming.get(1)) {
        struct.base = new BaseResponse();
        struct.base.read(iprot);
        struct.setBaseIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
